package com.zero.nlp.java;

public enum EnumString {
	S("xx"),V("vv");
	private final String value ;
	
	private EnumString(String value)
	{
		this.value = value ;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public static void main(String[] args) {
		System.out.println(V.getValue());
	}

}
