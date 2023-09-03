package com.fuhr.strproducer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

/**
 * Configuração personalizada para criar uma fábrica de produtores Kafka e um KafkaTemplate.
 */
@Configuration
@RequiredArgsConstructor
public class StringProducerFactoryConfig {

  /**
   * Propriedades de configuração do Kafka.
   */
  private final KafkaProperties kafkaProperties;

  /**
   * Configura e cria uma fábrica de produtores Kafka personalizada.
   *
   * @return Uma instância de ProducerFactory configurada para produzir mensagens String.
   */
  @Bean
  public ProducerFactory<String, String> producerFactory() {
    var configs = new HashMap<String, Object>();
    configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return new DefaultKafkaProducerFactory<>(configs);
  }

  /**
   * Cria e configura um KafkaTemplate personalizado para produzir mensagens String.
   *
   * @param producerFactory Uma fábrica de produtores Kafka configurada que foi criada
   *                        pelo metodo producerFactory desta classe.
   * @return Uma instância de KafkaTemplate configurada para produzir mensagens String.
   */
  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }
}
