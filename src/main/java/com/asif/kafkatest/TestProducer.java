package com.asif.kafkatest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;


public class TestProducer {
	
	public void produce(){
		Properties props = new Properties();
		
		try(InputStream config = Thread.currentThread().getContextClassLoader().getResourceAsStream("producer.properties")){
            props.load(config);
            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        } 
        catch (IOException e) {
        	e.printStackTrace();
        	System.exit(0);
        } 
		
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, TestData> producer = new KafkaProducer<String, TestData>(props, new StringSerializer(), new TestDataSerializer());
		
		String topic = "testTopic";
		
		for(int i = 0; i < 10; i++){
			TestData data = new TestData(i, "Test" + i);
			ProducerRecord<String, TestData> producerRecord = new ProducerRecord<String, TestData>(topic, null, data);
			
			try{
				producer.send(producerRecord,  new Callback() {
				                    public void onCompletion(RecordMetadata metadata, Exception e) {
				                        if(e != null){
				                            e.printStackTrace();
				                        }
				                        System.out.println("The offset of the record just sent is: " + metadata.offset());
				                    }
								});
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		producer.close();
	}

}
