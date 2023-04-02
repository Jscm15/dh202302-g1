package com.dh.g1.apiwallet.event;

import com.dh.g1.apiwallet.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteCreadoEventConsumer {
    //suscripción a cola de mensajes
    @RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENTE_CREADO)
    public void listenClienteCreadoEvent(Data message){
        System.out.println("Tipo Documento :"+ message.getTipoDocumento());
        System.out.println("Numero Documento :"+ message.getNumeroDocumento());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{
        private String tipoDocumento;
        private String numeroDocumento;
    }
}
