package com.example.restapp.GestorFinanciero.controller;

import com.example.restapp.GestorFinanciero.models.Meta;
import com.example.restapp.GestorFinanciero.models.Rol;
import com.example.restapp.GestorFinanciero.service.IMetaService;
import com.example.restapp.GestorFinanciero.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/meta")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MetaController {
    private final IMetaService service;

    @GetMapping
    public ResponseEntity<List<Meta>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Meta> save(@RequestBody Meta meta) throws Exception {
        Meta obj = service.save(meta);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdMeta()).toUri();
        return ResponseEntity.created(location).body(obj);
    }
}
