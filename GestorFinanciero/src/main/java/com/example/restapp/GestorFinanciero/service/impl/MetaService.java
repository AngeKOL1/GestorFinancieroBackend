package com.example.restapp.GestorFinanciero.service.impl;

import com.example.restapp.GestorFinanciero.models.Meta;
import com.example.restapp.GestorFinanciero.repo.IGenericRepo;
import com.example.restapp.GestorFinanciero.repo.MetaRepo;
import com.example.restapp.GestorFinanciero.service.IMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetaService extends GenericService<Meta, Integer> implements IMetaService {
    private final MetaRepo repo;
    @Override
    protected IGenericRepo<Meta, Integer> getRepo(){
        return repo;
    }
}
