package com.zero.nlp.modle;

/**
 * 类功能： 分类属性值存储 字段:
 * 
 * String status; int weibocount; int flowers; boolean isV; int tags; boolean
 * isTopic; int ats; int urls; int statuslen; 类方法：
 * 
 * 
 * 
 * @author lixuze
 * 
 */
public class ClassfierElement {

	private String status; // 微博文本
	private int weibocount; // 微博用户发微博数目
	private int followers; // 粉丝数目
	private int isV; // 是否加V
	private int tags; // tag数目
	private int topics; // 是否含有主题
	private int ats; // @ 数目
	private int urls; // url数目
	private int statuslen; // 文本长度
	private int emtions;// 表情数目
	public int getEmtions() {
		return emtions;
	}

	public void setEmtions(int emtions) {
		this.emtions = emtions;
	}

	private long uid; // 用户id

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getWeibocount() {
		return weibocount;
	}

	public void setWeibocount(int weibocount) {
		this.weibocount = weibocount;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}



	public int getIsV() {
		return isV;
	}

	public void setIsV(int isV) {
		this.isV = isV;
	}

	public int getTags() {
		return tags;
	}

	public void setTags(int tags) {
		this.tags = tags;
	}

	

	public int getTopics() {
		return topics;
	}

	public void setTopics(int topics) {
		this.topics = topics;
	}

	public int getAts() {
		return ats;
	}

	public void setAts(int ats) {
		this.ats = ats;
	}

	public int getUrls() {
		return urls;
	}

	public void setUrls(int urls) {
		this.urls = urls;
	}

	public int getStatuslen() {
		return statuslen;
	}

	public void setStatuslen(int statuslen) {
		this.statuslen = statuslen;
	}

	@Override
	public String toString() {
		return status+ "/#"  + weibocount+ "/#" + followers+ "/#" + tags + "/#" + topics + "/#" + ats +"/#" + urls + "/#"+ statuslen + "/#" + isV + "/#" + emtions ; 
	}

}
