package com.asif.kafkatest;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;


public class TestData {
	
	private static Gson gson;
	static {
		GsonBuilder builder = new GsonBuilder();
		gson = builder.create();
	}
	
	@Expose
	public int id;
	
	@Expose
	public String name;
	
	public TestData(){}
		
	public TestData(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public static String toJson(TestData message) {
    	return gson.toJson(message);
    }
	
	public static void fromJson(String jsonStr, TestData message) throws JsonSyntaxException {
		try {
			JSONObject json = new JSONObject(jsonStr);
			
			message.setId(json.getInt("id"));
			message.setName(json.getString("name"));
		} catch (Exception e) {
			throw new JsonSyntaxException("Invalid json", e);
		}
    }
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
