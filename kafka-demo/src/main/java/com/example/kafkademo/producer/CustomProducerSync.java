package com.example.kafkademo.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 同步发送
 */
public class CustomProducerSync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1、创建kafka生产者的配置对象
        Properties properties = new Properties();
        //2、给配置对象设置kafka集群地址连接
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "linux01:9092,linux02:9092,linux03:9092");
        //3、指定数据的key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //4、创建kafka生产者对象
        KafkaProducer<Object, Object> kafkaProducer = new KafkaProducer<>(properties);

        //5、发送数据
        for (int i = 0; i < 6; i++) {
            kafkaProducer.send(new ProducerRecord<>("first", "hello" + i), new Callback() {
                //编写回调内容
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(e == null){
                        System.out.println("主题：" + recordMetadata.topic() + " 分区：" + recordMetadata.partition());
                    }
                }
            }).get();
        }

        //6、关闭资源
        kafkaProducer.close();
    }
}
