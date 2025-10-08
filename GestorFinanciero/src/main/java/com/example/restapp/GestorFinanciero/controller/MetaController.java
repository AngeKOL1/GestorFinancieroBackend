package com.example.restapp.GestorFinanciero.controller;

import com.example.restapp.GestorFinanciero.DTO.CrearMetaDTO;
import com.example.restapp.GestorFinanciero.models.Meta;
import com.example.restapp.GestorFinanciero.service.IMetaService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/metas")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MetaController {
    private final IMetaService service;

    @GetMapping
    public ResponseEntity<List<Meta>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Meta> crearMeta(@RequestBody CrearMetaDTO dto,
                                            HttpServletRequest request) throws Exception {
            Integer authenticatedUserId = (Integer) request.getAttribute("authenticatedUserId");
            dto.setIdUsuario(authenticatedUserId);

            Meta meta = service.crearMetaDTO(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(meta);
        }

}
