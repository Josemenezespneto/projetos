package com.picpaysimplificado.dtos;

import java.math.BigDecimal;

public record TransacaoDTO(BigDecimal valor, Long remetenteId, Long recebedorId  ) {
    
}
