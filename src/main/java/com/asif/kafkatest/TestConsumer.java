package com.asif.kafkatest;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
 
public class TestConsumer implements Callable {
    private KafkaStream m_stream;
    private int m_threadNumber;
 
    public TestConsumer(KafkaStream a_stream, int a_threadNumber) {
        m_threadNumber = a_threadNumber;
        m_stream = a_stream;
    }
 
    public Object call() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext()){
        	byte[] message = it.next().message();
        	String msg = new String(message, StandardCharsets.UTF_8);
        	TestData data = new TestData();
        	TestData.fromJson(msg, data);
            System.out.println("Thread " + m_threadNumber + ": " + "id = " + data.id + " name = " + data.name);
        }
        System.out.println("Shutting down Thread: " + m_threadNumber);
        
        return null;
    }
}
