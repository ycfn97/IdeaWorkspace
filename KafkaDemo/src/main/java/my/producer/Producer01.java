package my.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop01:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,5);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG,0);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> stringStringKafkaProducer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 10; i++) {
//            默认粘性分区
//            stringStringKafkaProducer.send(new ProducerRecord<>("first","message^^^^"+i));

//            指定分区
//            stringStringKafkaProducer.send(new ProducerRecord<>("first",0,null,"message-->"+i));

//            hashcode分区
            stringStringKafkaProducer.send(new ProducerRecord<String, String>("atguigu","asdg"+i,"message**"+i),
            new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception!=null){
                        System.out.println(exception.getMessage());
                    }else {
                        System.out.println(metadata.topic()+"--"+metadata.partition()+"--"+metadata.offset());
                    }
                }
            }).get();
            System.out.println("==========send output===========");
        }

//        生产者必须关闭
        stringStringKafkaProducer.close();
    }
}
