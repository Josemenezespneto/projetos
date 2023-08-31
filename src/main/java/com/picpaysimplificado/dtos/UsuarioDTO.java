package com.picpaysimplificado.dtos;

import java.math.BigDecimal;

import com.picpaysimplificado.constants.TipoDeUsuario;

public record UsuarioDTO (String nome, String sobrenome,String email, String cpf, BigDecimal saldo, String senha ,TipoDeUsuario tipoDeUsuario  ){
    
}
