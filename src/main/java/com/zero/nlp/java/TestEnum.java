package com.zero.nlp.java;

public enum TestEnum {

	F(2),G(3),H(3);
	private final int value;
	
	private TestEnum(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	

	
}
