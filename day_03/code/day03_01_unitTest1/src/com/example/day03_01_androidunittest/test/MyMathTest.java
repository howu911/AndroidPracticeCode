package com.example.day03_01_androidunittest.test;

public class MyMathTest {
	public void addTest() {
		MyMath myMath = new MyMath();
		int result = myMath.add(3, 5);
		assertEquals(8,result);
	}

}
