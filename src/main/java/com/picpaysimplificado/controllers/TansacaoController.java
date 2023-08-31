package com.picpaysimplificado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.dtos.TransacaoDTO;
import com.picpaysimplificado.models.Transacao;
import com.picpaysimplificado.services.TransacaoService;

@RestController()
@RequestMapping("/transacoes")

public class TansacaoController {
    
@Autowired
private TransacaoService transacaoService;

@PostMapping
public ResponseEntity<Transacao> criarNovaTransacao(@RequestBody TransacaoDTO transacao)throws Exception{
    Transacao novaTransacao = this.transacaoService.criarTransacao(transacao);
    return new ResponseEntity<>(novaTransacao, HttpStatus.OK);
    
}

}
