package br.com.automotivo.controller;

import br.com.automotivo.model.Marca;
import br.com.automotivo.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    // Endpoint para cadastrar uma nova marca
    @PostMapping
    public ResponseEntity<Marca> cadastrar(@RequestBody Marca marca) {
        Marca marcaCadastrada = marcaService.cadastrar(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaCadastrada);
    }

    // Endpoint para listar todas as marcas
    @GetMapping
    public ResponseEntity<List<Marca>> listarTodas() {
        List<Marca> marcas = marcaService.listarTodas();
        return ResponseEntity.ok(marcas);
    }

    // Endpoint para buscar uma marca pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Long id) {
        Marca marca = marcaService.buscarPorId(id);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    // Endpoint para atualizar uma marca
    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizar(@PathVariable Long id, @RequestBody Marca marcaAtualizada) {
        Marca marca = marcaService.atualizar(id, marcaAtualizada);
        return ResponseEntity.ok(marca);
    }

    // Endpoint para remover uma marca pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        marcaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
