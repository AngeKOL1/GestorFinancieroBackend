package com.example.restapp.GestorFinanciero.service.impl;

import com.example.restapp.GestorFinanciero.models.Transaccion;
import com.example.restapp.GestorFinanciero.repo.IGenericRepo;
import com.example.restapp.GestorFinanciero.repo.TransaccionRepo;
import com.example.restapp.GestorFinanciero.service.ITransaccionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransaccionService extends GenericService<Transaccion, Integer> implements ITransaccionService {
    private final TransaccionRepo repo;
    @Override
    protected IGenericRepo<Transaccion, Integer> getRepo() {
        return repo;
    }
}
