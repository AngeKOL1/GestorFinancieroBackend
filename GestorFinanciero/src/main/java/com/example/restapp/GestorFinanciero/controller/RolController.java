package com.example.restapp.GestorFinanciero.controller;

import com.example.restapp.GestorFinanciero.models.Rol;
import com.example.restapp.GestorFinanciero.models.Usuario;
import com.example.restapp.GestorFinanciero.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RolController {
    private IRolService service;
    @GetMapping
    public ResponseEntity<List<Rol>> findAll() throws Exception{
        List<Rol> list = service.findAll();
        return ResponseEntity.ok(list);
    }

}
