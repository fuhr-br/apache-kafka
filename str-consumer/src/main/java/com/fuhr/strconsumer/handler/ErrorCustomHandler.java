package com.fuhr.strconsumer.handler;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Um componente Spring que implementa a interface KafkaListenerErrorHandler.
 * Esta classe lida com erros que ocorrem durante a execução de ouvintes de Kafka.
 * Ela fornece uma maneira personalizada de tratar erros que podem ocorrer ao consumir mensagens de um tópico Kafka.
 */
@Log4j2
@Component
public class ErrorCustomHandler implements KafkaListenerErrorHandler {


  /**
   * Lida com erros que ocorrem durante a execução de ouvintes de Kafka.
   *
   * @param message   A mensagem Kafka que causou o erro.
   * @param exception A exceção que foi lançada durante o processamento da mensagem.
   * @return Retorna null para indicar que o erro foi tratado, e a mensagem não deve ser reenviada.
   */
  @Override
  public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {

    log.error("EXCEPTION_HANDLER ::: Capturei um erro");
    log.error("Payload ::: {}", message.getPayload());
    log.error("Headers ::: {}", message.getHeaders());
    log.error("Offset ::: {}", message.getHeaders().get("kafka_offset"));
    log.error("Message exception ::: {}", exception.getMessage());
    return null;
  }

  /*
   * Lida com erros que ocorrem durante a execução de ouvintes de Kafka, e tenta reenviar a mensagem ao offset original.
   *
   * @param message   A mensagem Kafka que causou o erro.
   * @param exception A exceção que foi lançada durante o processamento da mensagem.
   * @param consumer  O consumidor Kafka associado ao ouvinte.
   * @return Retorna null para indicar que o erro foi tratado e a mensagem foi reenviada ao offset original.
   */
//  Método esta comentado pois iria reinviar ao tópico quando lançada a exeption e a aplicação entraria em loop, mas é um exemplo de como pode
//  podemos implementar um método que captura o consumer e pode reinviar ao tópico e off set a mensagem recebida que pode ser útil em alguns cenários
//  @Override
//  public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
//
//    String topico = message.getHeaders().get("kafka_receivedTopic").toString();
//    int particao = Integer.valueOf(message.getHeaders().get("kafka_receivedPartitionId").toString());
//    long offset = Long.valueOf(message.getHeaders().get("kafka_offset").toString());
//
//    log.error("EXCEPTION_HANDLER ::: Capturei um erro que sera reinviado ao tópico");
//    log.error("Payload ::: {}", message.getPayload());
//    log.error("Tópico ::: {}", topico);
//    log.error("Offset ::: {}", offset);
//    log.error("Particao ::: {}", particao);
//
//    consumer.seek(new TopicPartition(topico, particao), offset);
//
//    log.info("Mensagem reenviada a partição {} do offset {} no tópico {}", particao, offset, topico);
//    return null;
//  }
}