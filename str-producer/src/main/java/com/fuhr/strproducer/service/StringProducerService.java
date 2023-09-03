package com.fuhr.strproducer.service;

import com.fuhr.strproducer.exception.BadRequestKafkaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class StringProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  @Value("${topico}")
  private String topico;

  public void enviarMensagem(String mensagem) {

    kafkaTemplate.send(topico, mensagem).whenComplete((success, error) -> {
      if (error == null) {
        log.info("Mensagem enviada com sucesso: [{}]", mensagem);
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