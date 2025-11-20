package com.example.restapp.GestorFinanciero.Service;

import com.example.restapp.GestorFinanciero.DTO.TransaccionDTO;
import com.example.restapp.GestorFinanciero.models.*;
import com.example.restapp.GestorFinanciero.repo.*;
import com.example.restapp.GestorFinanciero.service.impl.TransaccionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransaccionServiceTest {

    @Mock
    private UsuarioRepo usuarioRepo;
    @Mock
    private TipoTransaccionRepo tipoTransaccionRepo;
    @Mock
    private MetaRepo metaRepo;
    @Mock
    private TransaccionRepo repo;

    @InjectMocks
    private TransaccionService transaccionService;

    private TransaccionDTO dto;
    private Usuario usuario;
    private TipoTransaccion tipoTransaccion;
    private Meta meta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1);

        tipoTransaccion = new TipoTransaccion();
        tipoTransaccion.setIdTipoTransaccion(2);

        meta = new Meta();
        meta.setIdMeta(3);
        meta.setUsuarioMetas(usuario);

        dto = new TransaccionDTO();
        dto.setMonto(100f);
        dto.setDescripcion("Compra");
        dto.setIdUsuario(1);
        dto.setTipoTransaccionId(2);
        dto.setIdMeta(3);
    }

    @Test
    void testCrearTransaccionDTO_Success() throws Exception {
        when(usuarioRepo.findById(1)).thenReturn(Optional.of(usuario));
        when(tipoTransaccionRepo.findById(2)).thenReturn(Optional.of(tipoTransaccion));
        when(metaRepo.findById(3)).thenReturn(Optional.of(meta));
        when(repo.save(any(Transaccion.class))).thenAnswer(i -> i.getArgument(0));

        Transaccion resultado = transaccionService.CrearTransaccionDTO(dto);

        assertNotNull(resultado);
        assertEquals(dto.getMonto(), resultado.getMonto());
        assertEquals(dto.getDescripcion(), resultado.getDescripcion());
        assertEquals(usuario, resultado.getUsuarioTransacciones());
        assertEquals(tipoTransaccion, resultado.getTipoTransaccion());
        assertEquals(LocalDate.now(), resultado.getFechaTransaccion());
        verify(repo, times(1)).save(any(Transaccion.class));
    }

    @Test
    void testCrearTransaccionDTO_UsuarioNoEncontrado() {
        when(usuarioRepo.findById(1)).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () -> transaccionService.CrearTransaccionDTO(dto));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void testCrearTransaccionDTO_TipoTransaccionNoEncontrado() {
        when(usuarioRepo.findById(1)).thenReturn(Optional.of(usuario));
        when(tipoTransaccionRepo.findById(2)).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () -> transaccionService.CrearTransaccionDTO(dto));
        assertEquals("Tipo de transacción no encontrado", ex.getMessage());
    }

    @Test
    void testCrearTransaccionDTO_UsuarioNoAutorizado() {
        Usuario otroUsuario = new Usuario();
        otroUsuario.setId(99);
        meta.setUsuarioMetas(otroUsuario);

        when(usuarioRepo.findById(1)).thenReturn(Optional.of(usuario));
        when(tipoTransaccionRepo.findById(2)).thenReturn(Optional.of(tipoTransaccion));
        when(metaRepo.findById(3)).thenReturn(Optional.of(meta));

        Exception ex = assertThrows(Exception.class, () -> transaccionService.CrearTransaccionDTO(dto));
        assertEquals("Usuario no autorizado para usar esta meta", ex.getMessage());
    }

    @Test
    void testCrearTransaccionDTO_MontoInvalido() {
        dto.setMonto(0f);

        when(usuarioRepo.findById(1)).thenReturn(Optional.of(usuario));
        when(tipoTransaccionRepo.findById(2)).thenReturn(Optional.of(tipoTransaccion));
        when(metaRepo.findById(3)).thenReturn(Optional.of(meta));

        Exception ex = assertThrows(Exception.class, () -> transaccionService.CrearTransaccionDTO(dto));
        assertEquals("El monto debe ser mayor que cero", ex.getMessage());
    }
}
