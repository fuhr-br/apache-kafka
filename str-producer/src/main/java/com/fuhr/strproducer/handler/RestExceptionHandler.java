package com.fuhr.strproducer.handler;

import com.fuhr.strproducer.exception.BadRequestKafkaException;
import com.fuhr.strproducer.exception.dto.BadRequestKafkaExceptionDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Log4j2
@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(BadRequestKafkaException.class)
  public ResponseEntity<BadRequestKafkaExceptionDto> handlerNotFoundException(BadRequestKafkaException exception) {
    return new ResponseEntity<>(
        BadRequestKafkaExceptionDto.builder()
            .dataHora(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .titulo("Erro ao enviar para o kafka")
            .detalhe(exception.getMessage())
            .mensagem("Cheque a documentação da API")
            .build(), HttpStatus.BAD_REQUEST);
  }

}
