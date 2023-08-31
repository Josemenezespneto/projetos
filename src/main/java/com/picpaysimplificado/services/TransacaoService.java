package com.picpaysimplificado.services;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.dtos.TransacaoDTO;
import com.picpaysimplificado.models.Usuario;
import com.picpaysimplificado.repositories.TransacaoRepositorio;

@Service
public class TransacaoService {
    
    @Autowired
    public UsuarioServices usuarioServices;

    @Autowired
    public TransacaoRepositorio repositorio;


    @Autowired
    public RestTemplate restTemplate;

    public void criarTransacao(TransacaoDTO transacao ) throws Exception{
        Usuario remetente = this.usuarioServices.findUsuarioById(transacao.remetenteId());
        Usuario rerebedor = this.usuarioServices.findUsuarioById(transacao.recebedorId());

        usuarioServices.validarTransacao(remetente, transacao.valor());

        if (this.autorizacaoDeTransacao(remetente, transacao.valor())) {
            throw new Exception("Transação não Autorizada")
        }
    
    }
    public boolean autorizacaoDeTransacao (Usuario remetente, BigDecimal valor){
        ResponseEntity<Map> autoririzacaoResposta = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",Map.class)
        
        if (autoririzacaoResposta.getStatusCode() == HttpStatus.OK ) {
            String mensagem = (String) autoririzacaoResposta.getBody().get("mensagem")
            return "Autorizado".equalsIgnoreCase(mensagem);
        } else return false;
    }

}
