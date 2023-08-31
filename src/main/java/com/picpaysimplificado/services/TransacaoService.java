package com.picpaysimplificado.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.dtos.TransacaoDTO;
import com.picpaysimplificado.models.Transacao;
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
        Usuario recebedor = this.usuarioServices.findUsuarioById(transacao.recebedorId());

        usuarioServices.validarTransacao(remetente, transacao.valor());

        boolean seAutorizado = this.autorizacaoDeTransacao(remetente, transacao.valor());
        if (seAutorizado) {
            throw new Exception("Transação não Autorizada");
        }
    
        Transacao novTransacao = new Transacao();
        novTransacao.setValor(novTransacao.getValor());
        novTransacao.setRemetente(remetente);
        novTransacao.setRecebedor(recebedor);
        novTransacao.setHoraDaTransacao(LocalDateTime.now());

        remetente.setSaldo(remetente.getSaldo().subtract(transacao.valor()));
        recebedor.setSaldo(recebedor.getSaldo().add(transacao.valor()));

        this.repositorio.save(novTransacao);
        this.usuarioServices.salvarUsuario(recebedor);
        this.usuarioServices.salvarUsuario(recebedor);
    }
    public boolean autorizacaoDeTransacao (Usuario remetente, BigDecimal valor){
        ResponseEntity<Map> autoririzacaoResposta = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",Map.class);
        
        if (autoririzacaoResposta.getStatusCode() == HttpStatus.OK ) {
            String mensagem = (String) autoririzacaoResposta.getBody().get("mensagem");
            return "Autorizado".equalsIgnoreCase(mensagem);
        } else return false;
    }

}
