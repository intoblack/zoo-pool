package com.weidou.mota.zoopool.mapreduce;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;
import org.hsqldb.lib.StringUtil;

/**
 * 提取用户信息，分析标记用户群体
 * 
 * @author 李旭泽
 * 
 */
public class KeyWordSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Configuration config = HBaseConfiguration.create();
		Job job = new Job(config, "KeyWordSearch");
		job.setJarByClass(KeyWordSearch.class);
		Scan scan = new Scan();
		long nowTime = new Date().getTime() / (60l * 60 * 1000)
				* (60l * 60 * 1000);
		long caBeginTime = nowTime - (60l * 24 * 60 * 60 * 1000);
		SingleColumnValueFilter caTimeBegin = new SingleColumnValueFilter(
				Bytes.toBytes("a"), Bytes.toBytes("ca"),
				CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(caBeginTime));
		SingleColumnValueFilter caTimeEnd = new SingleColumnValueFilter(
				Bytes.toBytes("a"), Bytes.toBytes("ca"),
				CompareOp.LESS_OR_EQUAL, Bytes.toBytes(nowTime));

		FilterList filter = new FilterList();
		filter.addFilter(caTimeBegin);
		filter.addFilter(caTimeEnd);
		scan.setFilter(filter);

		scan.setCacheBlocks(false);

		TableMapReduceUtil.initTableMapperJob("statuses", scan,
				MotaTableMapper.class, LongWritable.class, Text.class, job);
		job.setNumReduceTasks(10);
		job.setReducerClass(WarnReducer.class);
		FileOutputFormat.setOutputPath(job, new Path("/xx/kadilake1"));
		boolean b = job.waitForCompletion(true);
		if (!b) {
			throw new IOException("error with job!");
		}
	}

	/**
	 * 用BytesWritable，不要用LongWritable，虽然userid是个long
	 * 
	 * @author peng
	 * 
	 */
	public static class MotaTableMapper extends TableMapper<LongWritable, Text> {

		private static final byte[] FAMILY = Bytes.toBytes("a");
		private static final byte[] QUALIFIER_WS = Bytes.toBytes("ws"); // 分词结果
		private static final byte[] QUALIFIER_TEXT = Bytes.toBytes("text");
		private static final byte[] QUALIFIER_SENTIMENT = Bytes.toBytes("sent");

		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
			super.setup(context);
		}

		@Override
		public void map(ImmutableBytesWritable row, Result value,
				Context context) throws IOException, InterruptedException {
			String wordSegment = "";
			String text = "";
			int sentiment = 0;
			try {
				wordSegment = Bytes.toString(value.getValue(FAMILY,
						QUALIFIER_WS));
				text = Bytes.toString(value.getValue(FAMILY, QUALIFIER_TEXT));
				sentiment = Bytes.toInt(value.getValue(FAMILY,
						QUALIFIER_SENTIMENT));
			} catch (Exception e) {
				return;
			}
			if (!StringUtil.isEmpty(wordSegment)) {
				if (!text.contains("凯迪拉克")) {
					return;
				}
			} else {
				return;
			}
			if (sentiment == 0) {
				return;
			}
			context.write(new LongWritable(sentiment), new Text(text + "##"
					+ wordSegment));
		}
	}

	public static class WarnReducer extends
			Reducer<LongWritable, Text, NullWritable, Text> {

		private static final Logger logger = Logger
				.getLogger(WarnReducer.class);

		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
			super.setup(context);

		}

		public void reduce(LongWritable key, Iterable<Text> values,
				Context context) throws IOException, InterruptedException {
			logger.info("reduce执行!");
			Iterator<Text> it = values.iterator();
			StringBuffer writeString = new StringBuffer();
			while (it.hasNext()) {
				writeString.append(it.next() + "\n");
			}
			context.write(NullWritable.get(), new Text(writeString.toString()));
		}
	}

}