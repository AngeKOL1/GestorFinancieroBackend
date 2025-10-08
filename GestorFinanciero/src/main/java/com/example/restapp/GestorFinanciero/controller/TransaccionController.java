package com.example.restapp.GestorFinanciero.controller;

import com.example.restapp.GestorFinanciero.models.Transaccion;
import com.example.restapp.GestorFinanciero.service.ITransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transacciones")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TransaccionController {

    private final ITransaccionService service;

    @GetMapping
    public ResponseEntity<List<Transaccion>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Transaccion> save(@RequestBody Transaccion transaccion) throws Exception {
        Transaccion obj = service.save(transaccion);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdTransaccion()).toUri();
        return ResponseEntity.created(location).body(obj);
    }
}
