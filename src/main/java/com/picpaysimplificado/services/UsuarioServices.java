package com.picpaysimplificado.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.constants.TipoDeUsuario;
import com.picpaysimplificado.models.Usuario;
import com.picpaysimplificado.repositories.UsuarioRepositorio;

@Service
public class UsuarioServices {
    
    @Autowired
    private UsuarioRepositorio repositorio;


    public void validarTransacao(Usuario remetente, BigDecimal valor) throws Exception{
        if (remetente.getTipoDeUsuario() == TipoDeUsuario.EMPRESA){
            throw new Exception("Usuário do tipo Empresa não está autorizado a realizar transação.");
        }

        if(remetente.getSaldo().compareTo(valor) < 0){
            throw new Exception("Saldo insuficiente.");
        }

        

    }
    public Usuario findUsuarioById(Long id) throws Exception {
        return this.repositorio.findUsuarioById(id).orElseThrow(()-> new Exception("Usuário não encontrado"));
    }

    public void salvarUsuario(Usuario usuario){
        this.repositorio.save(usuario);
    }


}
