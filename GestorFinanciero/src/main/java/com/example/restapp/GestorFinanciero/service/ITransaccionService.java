package com.example.restapp.GestorFinanciero.service;

import com.example.restapp.GestorFinanciero.models.Transaccion;
import com.example.restapp.GestorFinanciero.DTO.TransaccionDTO;

public interface ITransaccionService extends IGenericService<Transaccion, Integer>{
    Transaccion CrearTransaccionDTO(TransaccionDTO dto) throws Exception;
}
