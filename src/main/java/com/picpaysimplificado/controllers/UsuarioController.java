package com.picpaysimplificado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.dtos.UsuarioDTO;
import com.picpaysimplificado.models.Usuario;
import com.picpaysimplificado.services.UsuarioServices;

@RestController()
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioServices usuarioServices;


    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO usuario){
        Usuario novoUsuario = usuarioServices.criaUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);

    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getTodosUsuarios(){
        List<Usuario> usuarios = this.usuarioServices.getTodosUsuarios();
        return  new ResponseEntity<>(usuarios ,HttpStatus.OK );
    }
}
