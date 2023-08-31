package com.picpaysimplificado.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.constants.TipoDeUsuario;
import com.picpaysimplificado.dtos.UsuarioDTO;
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

    public Usuario criaUsuario(UsuarioDTO data){
        Usuario novoUsuario = new Usuario(data);
        this.salvarUsuario(novoUsuario);
        return novoUsuario;
    }

    public List<Usuario> getTodosUsuarios(){
       return this.repositorio.findAll();
    }

    public void salvarUsuario(Usuario usuario){
        this.repositorio.save(usuario);
    }


}
