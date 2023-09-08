<p align="center">
  <h1 align="center">kafka com Spring Boot Java</h1>
</p>

<p align="center">

[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
<img src="https://img.shields.io/badge/Version-1.0.0-brightgreen.svg"/>
<img src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg"/>
</p>

## Tabela de Conteúdo

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Pré-Requisitos](#pré-requisitos)
- [Como Rodar](#como-rodar)
- [Rodando as APIs](#rodando-as-apis)


## Sobre o Projeto

Esse projeto tem como objetivo implementar um exemplo de um serviço de mensageria utilizando Kafka.
Primeiro você irá precisar instalar o docker no seu computador para rodar um container com os serviços do kafka.
este projeto tem 2 APIs de exemplo com documentação, uma que é uma produtora e uma consumidora. Siga os passos abaixo para rodar o projeto.

## Funcionalidades

O sistema permite:

- Enviar dados
- Consumir dados

## Tecnologias

O sistema foi desenvolvido utilizando as seguintes tecnologias:

| Objetivo | Tecnologia |
| ------ | ------ |
| Linguagem de programação | Java 17 |
| Framework | SpringBoot  |
| Documentação | Swagger  |
| Interface para manipulação do kafka | Kafdrop  |
| Gerenciador do cluster kafka | ZooKeeper  |
| Gerenciador de dependências | Maven  |
| Gerenciador de contêineres | Docker  |


## Pré-Requisitos
Você pode instalar as dependências do kafka manualmente, mas aconselho a ter o Docker instalado em sua máquina.<br>

## Como Rodar

### Se for subir o serviço do kafka com docker:

É necessário que você execute o seguinte comando na raiz do projeto onde se encontra o arquivo docker-compose.yml com as configurações do servidor kafka
`````
docker-compose up -d
`````
Isso irá subir os serviços necessários para rodar o Kafka<br>

O serviço do Kafdrop ficara disponivel em:

# [Kafdrop](http://localhost:19000)
`````
http://localhost:19000
`````

### Se for subir o serviço do kafka sem o uso do docker:

Primeiro será necessário baixar o kafka manualmente no site oficial do Apache kafka em: 

[KAFKA 2.13-3.5.1](https://downloads.apache.org/kafka/3.5.1/kafka_2.13-3.5.1.tgz)
```
https://downloads.apache.org/kafka/3.5.1/kafka_2.13-3.5.1.tgz
```

Você pode verificar se existe uma versão mais atual indo diretamente na página de dowload:

[APACHE KAFKA DOWLOAD](https://kafka.apache.org/downloads)
```
https://kafka.apache.org/downloads
```


#### Após será necessário descompactar o arquivo baixado, e dentro da pasta descompactada abra um prompt da sua preferência e siga os passos seguintes.
OBS: Para subir os serviços, o endereço da pasta onde você descompactar os arquivos não pode ter espaços vazios como por exemplo: 

```
C:\Users\anderson.souza\Documents\Apache Kafka\Kafka
```

Troque por exemplo para:

```
C:\Users\anderson.souza\Documents\Apache-Kafka\Kafka
```

OBS: No windows pode ocorrer do endereço do path ser muito longo, se houver erro ao executar, tente descompactar diretamente no path:  `C:\`

#### Agora primeiro será necessário subir a instância do zoolkeeper

#### Iniciar o serviço do zoolkeeper
#### No Windows
```
bin/windows/zookeeper-server-start.bat config/zookeeper.properties
```
#### Iniciar o serviço do zoolkeeper 
#### No Linux
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

#### Se tudo deu certo, vamos subir o serviço do kafka:

#### No Windows
```
bin/windows/kafka-server-start.bat config/server.properties
```
#### No Linux
```
bin/kafka-server-start.sh config/server.properties
```

Pronto você já tem um servidor kafka habilitado e pronto para uso!

### Criar e manipular o kafka via prompt
OBS: Repare que a diferença para rodar no windows ou Linux está no endereço e na extensão do arquivo que será executado, então se for usuário linux basta trocar a extensão `.bat` para `.sh` e o path `bin/windows/` para `bin/`
#### Criar um tópico
```
bin/windows/kafka-topics.bat --create --topic NOME_TOPICO --bootstrap-server localhost:9092
```

#### Descrever um tópico
```
bin/windows/kafka-topics.bat --describe --topic NOME_TOPICO --bootstrap-server localhost:9092
```

#### Criar um produtor para um tópico
Execute o comando abaixo para criar um serviço que irá enviar mensagens a um tópico, escreva a mensagem e  precione ENTER para enviar. Para encerrar o serviviço basta precionar `CTRL+C`
```
bin/windows/kafka-console-producer.bat --topic NOME_TOPICO --bootstrap-server localhost:9092
```


#### Criar um consumidor para um tópico
Se quiser simular um serviço rapidamente que recebe uma mensagem enviada a um tópico basta rodar o comando abaixo. Para encerrar o serviviço basta precionar `CTRL+C`
```
bin/windows/kafka-console-consumer.bat --topic NOME_TOPICO --from-beginning --bootstrap-server localhost:9092
```


## Rodando as APIs
Primeiro vamos rodar a API responsável pelo serviço de produção das mensagens enviadas ao kafka.
Ela esta na pasta `str-producer/` e ira rodar o serviço em:
```
http://localhost:8000
```
Neste endereço você pode acessar o swagger em:
```
http://localhost:8000/swagger-ui/index.html
```
Agora vamos rodar a API responsável pelo serviço de consumo das mensagens enviadas ao kafka.

Ela esta na pasta `str-consumer/` e ira rodar o serviço em:
```
http://localhost:8100
```
Agora basta subir as duas aplicações e você podera ver as mensagens de envio e recebimento nos LOGs das aplicações.


