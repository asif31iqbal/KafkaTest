package com.asif.kafkatest;

public class TestClient {
	public static void main(String[] args){
		TestProducer p = new TestProducer();
		p.produce();
	}
}
