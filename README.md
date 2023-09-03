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

Antes de começar, você precisará ter o Docker instalado em sua máquina.<br>

## Como Rodar

É necessário que você execute o seguinte comando dentro da pasta "APIs" onde se encontra o arquivo docker-compose.yml com as configurações do servidor kafka
`````
docker-compose up -d
`````
Isso irá subir os serviços necessários para rodar o Kafka<br>

O serviço do Kafdrop ficara disponivel em:

# [Kafdrop](http://localhost:19000)
`````
http://localhost:19000
`````

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


