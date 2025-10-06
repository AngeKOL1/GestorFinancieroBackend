package com.example.restapp.GestorFinanciero.service;

import com.example.restapp.GestorFinanciero.DTO.CrearMetaDTO;
import com.example.restapp.GestorFinanciero.models.Meta;

public interface IMetaService extends IGenericService<Meta, Integer> {
    Meta crearMetaDTO(CrearMetaDTO dto) throws Exception;
}
