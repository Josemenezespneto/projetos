package com.picpaysimplificado.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaysimplificado.models.Usuario;

public interface UsuarioRepositorio extends JpaRepository <Usuario, Long>{
    Optional<Usuario> findUsuarioByCpf (String cpf);

    
}
