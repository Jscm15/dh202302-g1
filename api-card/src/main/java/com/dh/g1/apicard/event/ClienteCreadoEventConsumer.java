package com.dh.g1.apicard.event;

import com.dh.g1.apicard.config.RabbitMQConfig;
import com.dh.g1.apicard.exception.CardException;
import com.dh.g1.apicard.model.TarjetaCredito;
import com.dh.g1.apicard.service.TarjetaCreditoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.lang.Math.abs;

@Component
public class ClienteCreadoEventConsumer {

    private final TarjetaCreditoService tarjetaCreditoService;

    public ClienteCreadoEventConsumer(TarjetaCreditoService tarjetaCreditoService) {
        this.tarjetaCreditoService = tarjetaCreditoService;
    }


    //suscripción a cola de mensajes
    @RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENTE_CREADO)
    public void listenClienteCreadoEvent(Data message) throws CardException {
        System.out.println("Tipo Documento :"+ message.getTipoDocumento());
        System.out.println("Numero Documento :"+ message.getNumeroDocumento());

        long numero = abs((long)(Math.random()*999999999+1));
        TarjetaCredito tarjetaCredito = TarjetaCredito.builder()
                .numeroTarjeta(new String(String.valueOf(numero)))
                .tipoDocumento(message.getTipoDocumento())
                .numeroDocumento(message.getNumeroDocumento())
                .limiteCalificado(new BigDecimal(0))
                .limiteConsumido(new BigDecimal(0))
                .limiteDisponible(new BigDecimal(0))
                .build();
        tarjetaCreditoService.crear(tarjetaCredito);
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
