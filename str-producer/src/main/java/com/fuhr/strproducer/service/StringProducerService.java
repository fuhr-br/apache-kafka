package com.fuhr.strproducer.service;

import com.fuhr.strproducer.exception.BadRequestKafkaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Log4j2
@Service
@RequiredArgsConstructor
public class StringProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  @Value("${topico}")
  private String topico;

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

  //Este metodo é um exempplo de implementação sem o uso do spring
  public void enviarMensagem(String mensagem) {

    var produtor = new KafkaProducer<String, String>(propertiesKafkaConfig());
    var chaveMensagem = "viaJava";

    //Aqui poderia enviar headers e outras coisas além da mensagem
    var record = new ProducerRecord<>(topico, chaveMensagem, mensagem);

    produtor.send(record, (success, error) -> {
      if (error == null) {
        log.info("Mensagem enviada com sucesso para o tópico: [" + topico + "] mensagem [{}]", mensagem);
        log.info("Partition: [{}], Offset: [{}]",
            success.partition(),
            success.offset());
      } else {
        log.info("Nao foi possivel enviar a mensagem=[" + mensagem + "] devido a : " + error.getMessage());
        throw new BadRequestKafkaException(error.getMessage());
      }
    });

  }

  public static Properties propertiesKafkaConfig() {

    Properties propriedade = new Properties();
    propriedade.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    propriedade.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    propriedade.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    return propriedade;
  }
}