package com.circus.liran.zoopool.enumcode;

public interface DPCode {
	public String getValue();
	public String getChinese();

	public enum FOOD implements DPCode {
		CHUAN_CAI("g102","川菜"), CAI_GUAN("g134","菜馆"), YUE_CAI("g103","粤菜"), QING_ZHEN("g108","清真"), HUO_GUO(
				"g110","火锅"), SIFANG("g1338","私房菜"), SHAO_KAO("g508","烧烤"), HAI_XIAN("g251","海鲜"), CHUAN_CHUAN(
				"g3017","串串香"), XIN_JIANG("g3243","新疆菜"), XIAO_CHI("g112","小吃/快餐"), HAN_LIAO("g114","韩国料理"), RI_LIAO(
				"g224","日本料理"), DONG_NAN_YA("g115","东南亚菜"), XI_CAN("g116","西餐"), ZI_ZHU("g111","自助"), TIAN_DIAN(
				"g117","面包/甜点"), OTHER("g118","其他");

		private String Code = "";
		private String Chinese = "";
		private FOOD(String code , String chinese) {
			this.Code = code;
			this.Chinese = chinese ;
		}

		@Override
		public String getValue() {
			return this.Code;
		}

		@Override
		public String getChinese() {
			return Chinese;
		}

	}

	public enum SHAOPING_TYPE implements DPCode {
		ZONG_HE("g119","综合"), SHIPIN_YANJIU("g184","食品/烟酒"), FUSHI_XIEBAO("g120","服饰/鞋包"), ZHUBAO_SHIPIN(
				"g122","珠宝/首饰"), HUA_DIAN("g26085","花店"), HUA_ZHUANG("g123","化妆"), YUNDONG_HUWAI(
				"g121","运动/户外"), MUYING_ERTONG("g125","母婴/儿童"), SHUMA_JIADIAN("g124","数码/家电"), JIAJU(
				"g126","家具家居"), SHU_DIAN("g127","书店"), YAN_JING("g128","眼镜"), YAO_DIAN("g235","药店"), CHAO_SHI(
				"g187","超市"), GENG_DUO("g131","更多");
		private String Code = "";
		private String Chinese = "";

		private SHAOPING_TYPE(String code , String Chinese) {
			this.Code = code;
			this.Chinese = Chinese;
		}

		@Override
		public String getValue() {
			return this.Code;
		}

		@Override
		public String getChinese() {
			return this.Chinese;
		}
	}

	public enum XIUXIAN_TYPE implements DPCode {
		CHA_GUAN("g134","茶馆"), QI_PAI("g32732","棋牌"), MI_SHI("g2754","密室"), KA_FEI("g132","咖啡"), JIU_BA(
				"g133","酒吧"), KTV("g135","KTV"), DIANYING("g136","电影院"), WENHUA_YISHU("g142","文化/艺术"), LV_YOU(
				"g139","景点/旅游"), GONGYUAN("g138","公园"), ZULIAO("g141","足疗"), XIYU("g140","洗浴"), YOULE(
				"g137","娱乐"), TAI_QIU("g156","台球"), ZHUO_MIAN("g6694","桌面游戏"), GENG_DUO("g26490","更多");

		private String Code = "";
		private String Chinese = "";
		private XIUXIAN_TYPE(String code , String chinese) {
			this.Code = code;
			this.Chinese = chinese ;
		}

		@Override
		public String getValue() {
			return Code;
		}
		
		
		public String getChinese()
		{
			return this.Chinese;
		}
	}

	public enum BEAUTY_TYPE implements DPCode {
		MEI_FA("g157", "美发"), MEI_RONG("g158", "美容/SPA"), HUAZHUANG("g123",
				"化妆品"), SHOU_SHEN("g159", "瘦身纤体"), MEIJIA("g160", "美甲"), YU_JIA(
				"g148", "瑜伽"), WU_DAO("g149", "舞蹈"), YI_SHU("g6700", "艺术写真"), ZHENG_XING(
				"g183", "整形"), CHI_KE("g182", "齿科");

		private String Code;
		private String Chinese;

		private BEAUTY_TYPE(String code, String chinese) {
			this.Code = code;
			this.Chinese = chinese;
		}

		@Override
		public String getValue() {
			return this.Code;
		}

	

		@Override
		public String getChinese() {
			return this.Chinese;
		}

	}
}
