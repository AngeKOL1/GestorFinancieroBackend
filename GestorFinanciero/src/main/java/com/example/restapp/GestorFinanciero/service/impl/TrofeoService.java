package com.example.restapp.GestorFinanciero.service.impl;

import com.example.restapp.GestorFinanciero.models.Trofeos;
import com.example.restapp.GestorFinanciero.repo.IGenericRepo;
import com.example.restapp.GestorFinanciero.repo.TrofeoRepo;
import com.example.restapp.GestorFinanciero.service.ITrofeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrofeoService extends GenericService<Trofeos, Integer> implements ITrofeoService {
    private final TrofeoRepo repo;
    @Override
    protected IGenericRepo<Trofeos, Integer> getRepo() {
        return repo;
    }
}
