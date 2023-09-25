package com.fuhr.strproducer.help;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

public class HelpConsumer {

  @Autowired
  protected EmbeddedKafkaBroker broker;

  protected <V> Consumer<String, V> criarUmConsumidorKafka() {

    Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(TOPIC, "true", broker);
    consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    //Sugerido pela documentação do Spring Kafka Test, setar earliest para consumir todos os offset em testes
    consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

    DefaultKafkaConsumerFactory consumerFactory = new DefaultKafkaConsumerFactory(consumerProps);

    return consumerFactory.createConsumer();
  }

}
