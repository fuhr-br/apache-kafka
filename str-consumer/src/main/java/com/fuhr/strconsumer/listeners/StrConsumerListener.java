package com.fuhr.strconsumer.listeners;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {

  @KafkaListener(groupId = "group-1", topics = "str-topic", containerFactory = "getConcurrentKafkaListenerContainerFactory")
  public void ouvirPrimeiraParticao(String mensagem) {

    log.info("METODO UM POCESSOU MENSAGEM DO GRUPO 1: {}", mensagem);
  }

  @KafkaListener(groupId = "group-1", topics = "str-topic", containerFactory = "getConcurrentKafkaListenerContainerFactory")
  public void ouvirSegundaParticao(String mensagem) {

    log.info("METODO DOIS POCESSOU MENSAGEM DO GRUPO 1: {}", mensagem);
  }

  @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "getConcurrentKafkaListenerContainerFactory")
  public void ouvirDuasParticoesComOutroGrupo(String mensagem) {

    log.info("METODO TRES POCESSOU MENSAGEM DO GRUPO 2: {}", mensagem);
  }

}
