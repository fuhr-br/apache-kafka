package com.fuhr.strconsumer.listeners;

import com.fuhr.strconsumer.custom.ConsumerCustomListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ConsumerListener {

  @ConsumerCustomListener
  public void ouvirParticaoZero(String mensagem) {

    log.info("LEITOR PARTICAO ZERO POCESSOU MENSAGEM DO GRUPO 1: {}", mensagem);
  }

  @ConsumerCustomListener
  public void ouvirParticaoUm(String mensagem) {


    log.info("LEITOR PARTICAO UM POCESSOU MENSAGEM DO GRUPO 1: {}", mensagem);
  }

  @ConsumerCustomListener(grupoId = "grupo-2")
  public void ouvirDuasParticoesComOutroGrupo(String mensagem, @Header("kafka_receivedMessageKey") String key) {

    if (key.equals("viaSpring")) {
      log.info("LEITOR GRUPO 2 PROCESSOU key: {}", key);

    }

    log.info("LEITOR GRUPO 2 PROCESSOU MENSAGEM: {}", mensagem);
  }

}
