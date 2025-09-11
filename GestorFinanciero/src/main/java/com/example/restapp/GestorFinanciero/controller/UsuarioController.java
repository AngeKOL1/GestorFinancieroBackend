package com.example.restapp.GestorFinanciero.controller;

import com.example.restapp.GestorFinanciero.models.Usuario;
import com.example.restapp.GestorFinanciero.service.IUsuarioService;
import com.example.restapp.GestorFinanciero.service.impl.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {
    private IUsuarioService service;
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() throws Exception{
        List<Usuario> list = service.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id") Integer id) throws Exception{
        Usuario obj =  service.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario publisher) throws Exception{
        Usuario obj =  service.save(publisher);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
