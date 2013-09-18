package com.circus.liran.zoopool.Hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;
import org.hsqldb.lib.StringUtil;

import com.circus.liran.zoopool.moudle.ClassfierElement;
import com.weidou.mota.event.dao.DaoException;
import com.weidou.mota.event.domain.Status;

public class StatusHbase {

	private static Configuration conf = null;
	private final static String TABLE_REGX = "statuses_2013-([0]{0,1}[6-9]{1}|1[0-2]+)-[0-9]{2}";
	private static final byte[] family = Bytes.toBytes("a");
	private static final byte[] type = Bytes.toBytes("rs");
	private static final byte[] text = Bytes.toBytes("text");
	private static final byte[] uid = Bytes.toBytes("uid");

	static {
		conf = HBaseConfiguration.create();
	}

	public static List<ClassfierElement> scan(String tableName, int count)
			throws IOException {
		HTable table = new HTable(conf, tableName);
		List<ClassfierElement> resultList = new ArrayList<ClassfierElement>();
		ResultScanner rs;
		long ncount = 0;
		try {
			Scan scan = new Scan();
			scan.setCaching(1000);
			rs = table.getScanner(scan);
			Iterator<Result> it = rs.iterator();
			while (it.hasNext()) {
				Result result = it.next();
				ClassfierElement element = new ClassfierElement();
				String textStatus = "";
				long userid = 0;
				byte[] rsbytes = result.getValue(family, type);
				if (rsbytes == null || rsbytes.length == 0
						|| Bytes.toLong(rsbytes) == 0) {
					if (result.containsColumn(family, text)) {
						textStatus = Bytes.toString(result.getValue(family,
								text));
					}
					if (result.containsColumn(family, uid)) {
						userid = Bytes.toLong(result.getValue(family, uid));
					}
				}
				if (!StringUtil.isEmpty(textStatus) && userid != 0) {
					element.setStatus(textStatus);
					element.setUid(userid);
					resultList.add(element);
					ncount = ncount + 1;
					if (ncount >= count) {
						break;
					}
				}
				System.out.println(ncount);
			}
		} catch (IOException e) {
		} finally {
			if (table != null) {
				table.close();
			}
		}
		return resultList;
	}
	
	
//	public static ClassfierElement getStatusByID(long id)
//	{
//		HTable table = new HTable(conf, tableName);
//		ClassfierElement elemt = new ClassfierElement();
//	}
}
