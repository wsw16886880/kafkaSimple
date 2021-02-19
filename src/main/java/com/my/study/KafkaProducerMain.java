package com.my.study;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author: xq
 * @Date: 2021/02/19 14:04
 */
public class KafkaProducerMain {
    public static void main(String[] args) {
        Properties props = new Properties();
        // kafka服务器
        props.put("bootstrap.servers", "192.168.122.129:9092");
        // 这个配置意味着leader会等待所有的follower同步完成。这个确保消息不会丢失，除非kafka集群中所有机器挂掉。这是最强的可用性保证
        props.put("acks", "all");
        // 配置为大于0的值的话，客户端会在消息发送失败时重新发送
        props.put("retries", 0);
        props.put("linger.ms", 1);
        // 配置为大于0的值的话，客户端会在消息发送失败时重新发送。
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 值序列化，默认org.apache.kafka.common.serialization.StringDeserializer
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
            System.out.println("发送成功！" + i);
        }
        producer.close();
    }
}
