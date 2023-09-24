package com.fuhr.strproducer.service;

import com.fuhr.strproducer.exception.BadRequestKafkaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Esta classe representa um serviço para enviar mensagens para um tópico no Apache Kafka usando o Spring Kafka.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class StringProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  @Value("${topico}")
  private String topico;

  /**
   * Envia uma mensagem para o tópico Kafka usando o Spring Kafka com as cinfigurações definidas na aplicação
   *
   * @param mensagem A mensagem a ser enviada.
   * @throws BadRequestKafkaException Se ocorrer um erro durante o envio da mensagem.
   */
  public void enviarMensagemSpring(String mensagem) {

    var chaveMensagem = "viaSpring";

    kafkaTemplate.send(topico, chaveMensagem, mensagem)
        .whenComplete((success, error) -> {
          if (error == null) {
            log.info("Mensagem enviada com sucesso para o tópico: [" + topico + "] mensagem [{}]", mensagem);
            log.info("Partition: [{}], Offset: [{}]",
                success.getRecordMetadata().partition(),
                success.getRecordMetadata().offset());
          } else {
            log.info("Nao foi possivel enviar a mensagem=[" + mensagem + "] devido a : " + error.getMessage());
            throw new BadRequestKafkaException(error.getMessage());
          }
        });
  }
}