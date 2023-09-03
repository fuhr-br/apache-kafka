package com.fuhr.strproducer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

/**
 * Configuração personalizada para o KafkaAdmin.
 */
@Configuration
@RequiredArgsConstructor
public class KafkaAdminConfig {

  /**
   * Propriedades de configuração do Kafka.
   */
  private final KafkaProperties kafkaProperties;

  /**
   * Configura e cria um bean KafkaAdmin para gerenciar a administração do Kafka.
   *
   * @return Uma instância de KafkaAdmin configurada.
   */
  @Bean
  public KafkaAdmin kafkaAdmin() {

    var configs = new HashMap<String, Object>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    return new KafkaAdmin(configs);
  }

  /**
   * Define e cria novos tópicos Kafka usando o KafkaAdmin.
   *
   * @return Um objeto NewTopics que define tópicos Kafka personalizados.
   */
  @Bean
  public KafkaAdmin.NewTopics topics() {

    return new KafkaAdmin.NewTopics(
        TopicBuilder.name("str-topic").partitions(2).replicas(1).build()
    );
  }
}
