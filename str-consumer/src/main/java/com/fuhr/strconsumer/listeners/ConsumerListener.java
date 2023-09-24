package com.fuhr.strconsumer.listeners;

import com.fuhr.strconsumer.custom.ConsumerCustomListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Esta classe atua como um componente Spring que contém métodos para ouvir mensagens Kafka de várias partições e grupos.
 * A classe utiliza a anotação @ConsumerCustomListener para definir métodos ouvintes personalizados.
 */
@Log4j2
@Component
public class ConsumerListener {

  /**
   * Método que atua como um ouvinte personalizado de mensagens Kafka para a partição zero, sem especificar um grupo.
   * Este método simula um erro ao ler mensagens Kafka recebidas com o grupo e tópico default setados no properties
   * Irá ler da partição 0 ou 1
   *
   * @param mensagem A mensagem Kafka recebida pelo ouvinte.
   */
  @ConsumerCustomListener
  public void ouvirParticaoZeroComException(String mensagem) {

    log.info("LEITOR 1 TENTOU POCESSAR A MENSAGEM DO GRUPO 1: {}", mensagem);
    throw new RuntimeException("Houve um erro inesperado");
  }

  /**
   * Método que atua como um ouvinte personalizado de mensagens Kafka para a partição um, sem especificar um grupo.
   * Este método processa mensagens Kafka recebidas com o grupo e tópico default setados no properties
   * Irá ler da partição 0 ou 1
   *
   * @param mensagem A mensagem Kafka recebida pelo ouvinte.
   */
  @ConsumerCustomListener
  public void ouvirParticao(String mensagem) {


    log.info("LEITOR 2 POCESSOU MENSAGEM DO GRUPO 1: {}", mensagem);
  }

  /**
   * Método que atua como um ouvinte personalizado de mensagens Kafka para o grupo "grupo-2".
   * Este método processa mensagens Kafka recebidas, incluindo a chave (key) quando disponível.
   * Irá ler dos tópicos 0 e 1
   *
   * @param mensagem A mensagem Kafka recebida pelo ouvinte.
   * @param key      A chave (key) associada à mensagem Kafka, se disponível.
   */
  @ConsumerCustomListener(grupoId = "grupo-2")
  public void ouvirDuasParticoesComOutroGrupo(String mensagem, @Header("kafka_receivedMessageKey") String key) {

    if (key.equals("viaSpring")) {
      log.info("LEITOR GRUPO 2 PROCESSOU key: {}", key);
    }
    log.info("LEITOR GRUPO 2 PROCESSOU MENSAGEM SEM DEFINIR KEY ESPECIFICA: {}", mensagem);
  }
}