package com.fuhr.strconsumer.custom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Uma anotação personalizada para configurar consumidores de mensagens Kafka.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@KafkaListener
public @interface ConsumerCustomListener {

  /**
   * Tópicos aos quais se inscrever para a consumição de mensagens Kafka.
   * O valor padrão deve ser definida em kafka.topic na configuração da aplicação e pode ser substituída.
   *
   * @return Um array de nomes de tópicos.
   */
  @AliasFor(annotation = KafkaListener.class, attribute = "topics")
  @Value("${kafka.topico}")
  String[] topico() default "${kafka.topic}";

  /**
   * O nome da fábrica de contêiner Kafka a ser usada para a consumição de mensagens.
   * O valor padrão é "getConcurrentKafkaListenerContainerFactory".
   *
   * @return O nome da fábrica de contêiner.
   */
  @AliasFor(annotation = KafkaListener.class, attribute = "containerFactory")
  String containerFactory() default "getConcurrentKafkaListenerContainerFactory";


  /**
   * O ID do grupo Kafka para o consumidor de mensagens.
   * O valor padrão deve ser definida em kafka.groupId na configuração da aplicação e pode ser substituída.
   *
   * @return O ID do grupo Kafka do consumidor.
   */
  @AliasFor(annotation = KafkaListener.class, attribute = "groupId")
  @Value("${kafka.groupId}")
  String grupoId() default "${kafka.groupId}";

}
