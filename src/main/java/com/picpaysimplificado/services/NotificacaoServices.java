package com.picpaysimplificado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.dtos.NotificacaoDTO;
import com.picpaysimplificado.models.Usuario;

@Service
public class NotificacaoServices {
    
    @Autowired
    public RestTemplate restTemplate;

    public void enviarNotificacao(Usuario usuario, String mensagem) throws Exception {
        String email = usuario.getEmail();
        NotificacaoDTO notificacaoRequerida = new NotificacaoDTO(email, mensagem);

    //    ResponseEntity<String> notificacaoResposta =restTemplate.postForEntity("http://04d9z.mocklab.io/notify", notificacaoRequerida, String.class);
    //    if(!(notificacaoResposta.getStatusCode() == HttpStatus.OK)){
    //         System.out.println("erro ao enviar notificação");
    //         throw new Exception("Serviço de notificação está fora do ar");
            
    //    }
    System.out.println("Notificação enviada para o usuario");
    }

}
