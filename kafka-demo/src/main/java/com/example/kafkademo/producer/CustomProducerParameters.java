package com.example.kafkademo.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.record.CompressionType;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 生产者提高消息吞吐量参数配置
 */
public class CustomProducerParameters {
    public static void main(String[] args) {
        //1、创建kafka生产者的配置对象
        Properties properties = new Properties();
        //2、给配置对象设置kafka集群地址连接
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "linux01:9092,linux02:9092,linux03:9092");
        //3、指定数据的key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // batch.size：批次大小，默认16K；
        // 表示消息缓存队列中的一批数据达16k后sender线程开始拉取数据
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // linger.ms：等待时间，默认0ms；
        // 表示如消息缓存队列中批次数据未达到16k后sender线程多久开始拉取数据，0ms表示有数据就拉取发送
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // RecordAccumulator：缓冲区大小，默认32M：buffer.memory
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // compression.type：数据压缩，默认 none，可配置值 gzip、snappy、 lz4和zstd
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, CompressionType.SNAPPY.name);

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
            });
        }

        //6、关闭资源
        kafkaProducer.close();
    }
}
