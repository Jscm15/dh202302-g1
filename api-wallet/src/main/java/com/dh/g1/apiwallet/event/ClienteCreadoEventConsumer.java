package com.dh.g1.apiwallet.event;

import com.dh.g1.apiwallet.config.RabbitMQConfig;
import com.dh.g1.apiwallet.exception.WalletException;
import com.dh.g1.apiwallet.model.Moneda;
import com.dh.g1.apiwallet.model.Wallet;
import com.dh.g1.apiwallet.repository.MonedaRepository;
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

    private  final MonedaRepository monedaRepository;

    public ClienteCreadoEventConsumer(WalletService walletService, MonedaRepository monedaRepository) {
        this.walletService = walletService;
        this.monedaRepository = monedaRepository;
    }

    //suscripción a cola de mensajes
    @RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENTE_CREADO)
    public void listenClienteCreadoEvent(Data message) throws WalletException {
        System.out.println("Tipo Documento :"+ message.getTipoDocumento());
        System.out.println("Numero Documento :"+ message.getNumeroDocumento());

        if(monedaRepository.findById("ARS").isEmpty()){
            Moneda moneda = Moneda.builder()
                    .codigo("ARS")
                    .descripcion("Pesos")
                    .build();
            monedaRepository.save(moneda);
        }


        Wallet wallet = Wallet.builder()
                .tipoDocumento(message.getTipoDocumento())
                .nroDocumento(message.getNumeroDocumento())
                .moneda(Moneda.builder().codigo("ARS").build())
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
