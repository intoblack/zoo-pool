package com.circus.liran.zoopool.enumcode;


public class DPSearchCoder {

	
	private final static String BASE_URL = "http://www.dianping.com/search/category/";

	public enum CITY_CODE {
		SHANGHAI(1), BEIJING(2), HANGZHOU(3), GUANGZHOU(4), NANJING(5), SUZHOU(
				6), SHENZHEN(7), CHENGDU(8), CHONGQING(9), TIANJIN(10);

		private final int value;

		private CITY_CODE(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public enum CATEGORY {
		ALL(0), FOOD(10), SHOPPING(20), XIUXIAN(30), SPORT(45), BEAUTY(50), MARRY(
				55), HOTEL(60), LIFESERVICE(80);

		private final int value;

		private CATEGORY(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}
	
	

	private DPSearchCoder() {

	}

	public static String getDPSearchUrl(CITY_CODE city, CATEGORY cate) {
		return BASE_URL + city.getValue() + "/" + cate.getValue();
	}

	public static String getDPSearchUrl(CITY_CODE city, CATEGORY cate, int page) {
		if (page < 0 || page > 50) {
			throw new IllegalArgumentException("page:" + page);
		}
		return getDPSearchUrl(city, cate) + "/p" + page;
	}
	
	
	public static String getDPSearchUrl(CITY_CODE city , CATEGORY cate , DPCode code)
	{
		return getDPSearchUrl(city, cate) + "/" + code.getValue();
	}

	
	public static String getDPSearchUrl(CITY_CODE city , CATEGORY cate , DPCode code , int page)
	{
		if (page < 0 || page > 50) {
			throw new IllegalArgumentException("page(1=< page <= 50):" + page);
		}
		return getDPSearchUrl(city, cate, code) + "p" + page;
	}
	
	
	public static void main(String[] args) {
		System.out
				.println(getDPSearchUrl(CITY_CODE.NANJING, CATEGORY.FOOD,DPCode.FOOD.CAI_GUAN));
	}


}
