package com.zero.nlp.simhash;

import java.util.Iterator;

public class Bytes implements Iterable<Byte> {

	private byte arrys[];
	private int length = 0;

	public Bytes(long id) {
		arrys = new byte[64];
		length = 64;
		for (int i = 0; i < 64; i++) {
			arrys[i] = (byte) (id & 1);
			id = id >> 1;

		}
	}
	
	
	public Bytes(char value)
	{
		arrys = new byte[8] ;
		length = 8; 
		for(int i = 0 ; i < 8 ; i++)
		{
			arrys[i] = (byte) (value & 1) ; 
			value = (char) (value >> 1) ;
		}
	}
	
	
	public Bytes(int value)
	{
		arrys = new byte[32] ;
		length = 32; 
		for(int i = 0 ; i < 32 ; i++)
		{
			arrys[i] = (byte) (value & 1) ; 
			value = (char) (value >> 1) ;
		}
	}
	
	
	public Bytes(String value)
	{
		if(value == null || value.length() == 0)
		{
			throw new IllegalArgumentException() ;
		}
	}

	public ByteIter iter() {
		return new ByteIter();
	}

	private class ByteIter implements Iterator<Byte> {

		private int next = 0;

		public boolean hasNext() {
			return this.next != length;
		}

		public Byte next() {
			return arrys[next++];
		}

		public void remove() {
		}
	}

	public byte getByte(int index) {
		if (index < 0 || index > length) {
			throw new IllegalArgumentException();
		}
		return arrys[index];
	}

	public boolean isEmpty() {
		return arrys == null || arrys.length == 0;
	}

	public static void main(String[] args) {
		Bytes bytes = new Bytes(100);
		for (Byte i : bytes) {
			System.out.println(i);
		}
	}

	public Iterator<Byte> iterator() {
		return new ByteIter();
	}

}
