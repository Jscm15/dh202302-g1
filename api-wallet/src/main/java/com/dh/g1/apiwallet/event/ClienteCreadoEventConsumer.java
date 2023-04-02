package com.dh.g1.apiwallet.event;

import com.dh.g1.apiwallet.config.RabbitMQConfig;
import com.dh.g1.apiwallet.exception.WalletException;
import com.dh.g1.apiwallet.model.Moneda;
import com.dh.g1.apiwallet.model.Wallet;
import com.dh.g1.apiwallet.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteCreadoEventConsumer {

    private final WalletService walletService;

    public ClienteCreadoEventConsumer(WalletService walletService) {
        this.walletService = walletService;
    }

    //suscripción a cola de mensajes
    @RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENTE_CREADO)
    public void listenClienteCreadoEvent(Data message) throws WalletException {
        System.out.println("Tipo Documento :"+ message.getTipoDocumento());
        System.out.println("Numero Documento :"+ message.getNumeroDocumento());

        Moneda moneda = Moneda.builder()
                .codigo("ARS")
                .descripcion("Pesos")
                .build();
        Wallet wallet = Wallet.builder()
                .tipoDocumento(message.getTipoDocumento())
                .nroDocumento(message.getNumeroDocumento())
                .moneda(moneda)
                .balance(0.0)
                .build();
        walletService.create(wallet);
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
