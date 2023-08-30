package com.picpaysimplificado.models;

import java.math.BigDecimal;

import com.picpaysimplificado.constants.TipoDeUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "usuarios")
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String nome;

private String sobrenome;

@Column(unique = true)
private String cpf;

@Column(unique = true)
private String email;

private String senha;

private BigDecimal saldo;

@Enumerated(EnumType.STRING)
private TipoDeUsuario tipoDeUsuario;

}
