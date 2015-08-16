package com.asif.kafkatest;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import com.google.gson.JsonSyntaxException;


public class TestDataSerializer implements Serializer<TestData>, Deserializer<TestData> {

	public TestDataSerializer() {}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String topic, TestData message) {
		try {
			String json = TestData.toJson(message);
			return json.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

	@Override
	public TestData deserialize(String topic, byte[] jsonByte) {
		TestData message = new TestData();
		try { 
			TestData.fromJson(new String(jsonByte), message);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return message;
	}

}
