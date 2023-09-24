package com.fuhr.strproducer.service;

import com.fuhr.strproducer.exception.BadRequestKafkaException;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Esta classe representa um serviço para enviar mensagens para um tópico de forma customizada
 */
@Log4j2
@Service
public class CustomProducerService {

  @Value("${topico}")
  private String topico;

  /**
   * Envia uma mensagem para o tópico Kafka configurado.
   *
   * @param mensagem A mensagem a ser enviada.
   * @throws BadRequestKafkaException Se ocorrer um erro durante o envio da mensagem.
   */
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

  /**
   * Configura as propriedades do produtor Kafka de forma customizada, não sendo necessário usar as default da aplicação
   *
   * @return Um objeto Properties contendo as configurações do produtor Kafka.
   */
  public static Properties propertiesKafkaConfig() {

    Properties propriedade = new Properties();
    propriedade.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    propriedade.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    propriedade.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    return propriedade;
  }

}
