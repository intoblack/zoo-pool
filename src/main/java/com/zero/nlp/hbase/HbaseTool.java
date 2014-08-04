package com.zero.nlp.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.yecht.Data.Str;

import com.zero.nlp.exception.ZooPoolException;
import com.zero.nlp.utils.ZooPoolUtil;
/**
 * 类名称：HbaseTool
 * 类功能：提供 Hbase 操作的基本方法
 * 
 * 
 * @author lixuze
 *
 */
public class HbaseTool {
	private static final Logger logger = Logger.getLogger(HbaseTool.class);
	public final static String TABLE_NAME = "wordgraph";
	private static final byte[] family = Bytes.toBytes("cg");
	private static final byte[] type = Bytes.toBytes("v");
	private static Configuration conf = null;

	static {
		conf = HBaseConfiguration.create();
	}

	/**
	 * 
	 * 功能:向hbase数据库 表wordgraph插入数据
	 * 
	 * 
	 * 附加信息: 表的主键信息 每个词作为主键
	 * 
	 * @param fileDataMap
	 *            文件数据信息 <类别,数据List>
	 * @throws IOException
	 */
	public static void insert(Map<String, String> fileDataMap)
			throws IOException {
		logger.info("链接habse,准备插入数据!");
		HTable table = new HTable(conf, TABLE_NAME);
		logger.info("链接habse成功");
		List<Put> puts = new ArrayList<Put>();
		Set<String> keySet = fileDataMap.keySet();
		for (String key : keySet) {
			Put put = new Put(Bytes.toBytes(key));
			put.add(family, type, Bytes.toBytes(fileDataMap.get(key)));
			puts.add(put);
		}
		table.put(puts);
		table.close();
	}

	/**
	 * 方法名： public static void createTable(String tableName, String... familys)
			throws ZooPoolException
	 * 方法功能： 创建Hbase表
	 * 方法警告：
	 *          会删除同名表，重新建立
	 *       
	 * @param tableName
	 * @param familys
	 * @throws ZooPoolException
	 */
	public static void createTable(String tableName, String... familys)
			throws ZooPoolException {
		HBaseAdmin hBaseAdmin = null;
		try {
			hBaseAdmin = new HBaseAdmin(conf);
			if (hBaseAdmin.tableExists(tableName)) {// 如果存在要创建的表，那么先删除，再创建
				hBaseAdmin.disableTable(tableName);
				hBaseAdmin.deleteTable(tableName);
			}
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
			for (String family : familys) {
				tableDescriptor.addFamily(new HColumnDescriptor(family));
			}
			hBaseAdmin.createTable(tableDescriptor);
		} catch (Exception e) {
			throw new ZooPoolException("CREATE_HBASE_TABLE_FAILED", e);
		} finally {
			if (hBaseAdmin != null) {
				try {
					hBaseAdmin.close();
				} catch (IOException e) {
					throw new ZooPoolException("CREATE_HBASE_TABLE_FAILED", e);
				}
			}
		}
	}

	public static Map<String, String> parseData(List<String> fileDataList) {
		Map<String, String> dataMap = new HashMap<String, String>();
		for (String data : fileDataList) {
			String[] dataArry = data.split("\t");
			String key = "";
			StringBuffer value = null;
			try {
				key = dataArry[0];
				value = new StringBuffer();
				for (int i = 1; i < dataArry.length; i++) {
					if (i == 1) {
						value.append(dataArry[i]);
					} else {
						value.append("#" + dataArry[i]);
					}
				}
			} catch (Exception e) {
				continue;
			}
			if (dataMap.containsKey(key)) {
				String category = dataMap.get(key) + "#" + value.toString();
				dataMap.put(key, category);
			} else {
				dataMap.put(key, value.toString());
			}
		}
		return dataMap;
	}

	/**
	 * 函数名： public static List<String> getTableNameList() throws IOException；
	 * 函数功能： 获得所有Hbase表名 函数思想： 无
	 * 
	 * @return
	 * @throws IOException
	 */
	public static List<String> getTableNameList() {
		List<String> tableNameList = new ArrayList<String>();
		HBaseAdmin ha = null;
		try {
			ha = new HBaseAdmin(conf);
			for (HTableDescriptor h : ha.listTables()) {
				tableNameList.add(h.getNameAsString());
			}
		} catch (Exception e) {
			logger.info("get table name failed", e);
		} finally {
			if (ha != null) {
				try {
					ha.close();
				} catch (IOException e) {
				}
			}
		}
		return tableNameList;
	}

	/**
	 * 函数功能：通过前缀滤过表名
	 * 函数思想：
	 *        正则匹配表名
	 * 
	 * @param prexArry
	 * @return
	 */
	public static List<String> getTableNameListByReg(String... prexArry) {
		List<String> tableNameList = new ArrayList<String>();
		for (String tablename : getTableNameList()) {
			for (String prex : prexArry) {
				if (tablename.matches(prex)) {
					tableNameList.add(tablename);
					continue;
				}
			}
		}
		return tableNameList;
	}

	public static void main(String args[]) {
		// try {
		// createTable("wordgraph" , "cg");
		// } catch (ZooPoolException e) {
		// System.out.println(e);
		// }
		// System.out.println("我的家\t".split("\t").length);
		// for (String str : "我的家\t".split("\t")) {
		// System.out.println(str);
		// }

//		try {
//			List<String> fileData = ZooPoolUtil
//					.readFileData("/home/lixuze/google_filter_result_0702");
//			try {
//				insert(parseData(fileData));
//			} catch (IOException e) {
//				System.out.println(e);
//			}
//		} catch (ZooPoolException e) {
//			System.out.println(e);
//		}

		for(String table:getTableNameListByReg("statuses_2013-([0]{0,1}[6-9]{1}|1[0-2]+)-[0-9]{2}"))
		{
			System.out.println(table);
		}
		
	}
}
