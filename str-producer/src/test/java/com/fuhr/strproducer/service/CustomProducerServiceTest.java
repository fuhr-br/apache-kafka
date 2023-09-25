package com.fuhr.strproducer.service;

import com.fuhr.strproducer.help.HelpConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EmbeddedKafka(
    topics = "${topico}",
    partitions = 1,
    bootstrapServersProperty = "spring.kafka.bootstrap-servers"
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomProducerServiceTest extends HelpConsumer {

  @Autowired
  CustomProducerService customProducerService;

  @Value("${topico}")
  String topico;

  @Test
  void enviarMensagem() {

    Consumer<String, String> consumer = criarUmConsumidorKafka();
    super.broker.consumeFromEmbeddedTopics(consumer, topico);
    String mensagemEnviada = "Mensagem teste";

    customProducerService.enviarMensagem(mensagemEnviada);

    ConsumerRecords<String, String> records = KafkaTestUtils.getRecords(consumer, Duration.ofSeconds(5));
    records.forEach(record -> {
      assertTrue(record.value().equals(mensagemEnviada));
      assertTrue(record.key().equals("viaJava"));
      assertTrue(record.topic().equals(topico));

    });

  }

}