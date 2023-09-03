package com.fuhr.strconsumer.custom;

import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@KafkaListener
public @interface ConsumerCustomListener {

  @AliasFor(annotation = KafkaListener.class, attribute = "topics")
  String[] topics() default "${topico}";

  @AliasFor(annotation = KafkaListener.class, attribute = "containerFactory")
  String containerFactory() default "getConcurrentKafkaListenerContainerFactory";

  @AliasFor(annotation = KafkaListener.class, attribute = "groupId")
  String groupId() default "${grupoDefault}";

}
