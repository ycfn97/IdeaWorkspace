package my.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer01 {
    public static void main(String[] args) {
        Properties properties=new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop01:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"ss");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");

        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

         KafkaConsumer<String,String> kafkaConsumer=new KafkaConsumer(properties);
        List<String> topics = new ArrayList<>();
        topics.add("atuguigu");
        topics.add("first");
         kafkaConsumer.subscribe(topics);

         while (true){
             ConsumerRecords<String,String> records=kafkaConsumer.poll(Duration.ofSeconds(1));
             for (ConsumerRecord<String, String> record : records) {
                 System.out.println("consume to: " + record.topic() +" -- " + record.partition() +" -- "+ record.offset() +" -- "+ record.key() +" -- " + record.value());
             }

             kafkaConsumer.commitSync();
             System.out.println("============= commitSync =============");
         }
    }
}
