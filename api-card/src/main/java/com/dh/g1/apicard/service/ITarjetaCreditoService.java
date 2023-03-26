package com.dh.g1.apicard.service;

import com.dh.g1.apicard.controller.TarjetaCreditoController;
import com.dh.g1.apicard.exception.CardException;
import com.dh.g1.apicard.model.MovimientosTarjetaCredito;
import com.dh.g1.apicard.model.TarjetaCredito;

import java.util.Optional;

public interface ITarjetaCreditoService {
    public Optional<TarjetaCredito> findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento) throws CardException;
    public String crear(TarjetaCredito tarjetaCredito) throws CardException;
    public void debitar(MovimientosTarjetaCredito movimiento) throws CardException;
    public void pagar(TarjetaCreditoController.PagoTarjetaCreditoDTO pagoTarjetaCreditoDTO) throws CardException;
}
