package com.fuhr.strconsumer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class StringConsumerFactoryConfig {

  private final KafkaProperties kafkaProperties;

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {

    var configs = new HashMap<String, Object>();
    // Configuração para os servidores Kafka a serem conectados (lista de servidores)
    configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());

    // Configuração para a classe usada para deserializar as chaves (key) das mensagens Kafka
    configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    // Configuração para não permitir que a aplicação gere tópicos
    configs.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, "false");

    // Configuração para a classe usada para deserializar os valores (value) das mensagens Kafka
    configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    // Configuração para o número máximo de registros que serão buscados a cada chamada do poll()
    configs.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
    //    Configurar auto.offset.reset como "earliest" faz
    //    com que o consumidor inicie a leitura do tópico a partir da mensagem mais antiga disponível, garantindo que
    //    ele não perca nenhuma mensagem do tópico, mesmo se não houver um offset inicial válido.

    //    Outra opção comum para auto.offset.reset é "latest", que faz com que o consumidor comece a ler a
    //    partir do offset mais recente disponível, ignorando mensagens antigas. A escolha entre "earliest" e "latest" depende
    //    dos requisitos do seu aplicativo e de como você deseja lidar com offsets ausentes ou inválidos.
    configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

    // Configuração para auto-confirmar o deslocamento (offset) após o processamento de uma mensagem
    configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

    // Intervalo de tempo em que o consumidor irá enviar os offsets confirmados de volta ao Kafka (em milissegundos)
    configs.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

    // Configuração para controlar o tempo limite de conexão do consumidor com o grupo
    configs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

    // Configuração para controlar o tempo limite máximo de processamento de uma única chamada ao poll
    configs.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "300000");

    // Configuração para controlar o isolamento do consumidor (leitura apenas de partições exclusivas)
    configs.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");

    // Configuração para definir o tamanho do cache de registros (em bytes) antes de iniciar o processamento
    configs.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "1");

    // Configuração para definir o tempo limite de espera máximo para os registros serem buscados pelo consumidor
    configs.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, "500");

    // como temos mais de um metodo que simula um consumidor, o id deveria ser setado para cada um
    // configs.put(ConsumerConfig.CLIENT_ID_CONFIG, "NOME DO SERVICO CONSUMIDOR:"+ UUID.randomUUID().toString());

    // Outras configurações do consumidor Kafka podem ser adicionadas conforme necessário
    return new DefaultKafkaConsumerFactory<>(configs);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> getConcurrentKafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {

    var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    factory.setConsumerFactory(consumerFactory);

    return factory;
  }
}
