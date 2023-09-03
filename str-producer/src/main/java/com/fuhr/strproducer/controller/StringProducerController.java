package com.fuhr.strproducer.controller;

import com.fuhr.strproducer.service.StringProducerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "String Producer Controller", description = "Envia mensagens ao kafka")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/mensagem")
public class StringProducerController {

  private final StringProducerService producerService;

  @PostMapping(value = "/enviar")
  public ResponseEntity<Void> enviarMensagem(@RequestBody String mensagem) {

    producerService.enviarMensagem(mensagem);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}