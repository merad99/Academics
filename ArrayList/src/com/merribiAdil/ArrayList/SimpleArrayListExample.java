package com.merribiAdil.ArrayList;

public class SimpleArrayListExample {
	
	
    public static void main(String[] args) {
    	
    	MyArrayList MyArrayListExample = new MyArrayList();
		
		
		
		MyArrayListExample.clear();
		MyArrayListExample.add("FIVE");
		MyArrayListExample.add("ONE");
		MyArrayListExample.add("TWO");
		MyArrayListExample.add("THREE");
		
		
		System.out.println(MyArrayListExample.isEmpty());
		System.out.println(MyArrayListExample);
		System.out.println(MyArrayListExample.size());
		
		
		

		MyArrayListExample.add(1);
		MyArrayListExample.add(2);
		MyArrayListExample.add(3);
		
		System.out.println(MyArrayListExample);
		
		
	}

}
