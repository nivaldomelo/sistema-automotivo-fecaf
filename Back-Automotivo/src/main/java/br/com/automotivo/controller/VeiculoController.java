package br.com.automotivo.controller;


import br.com.automotivo.model.Marca;
import br.com.automotivo.model.Veiculo;
import br.com.automotivo.model.VeiculoRequest;
import br.com.automotivo.service.MarcaService;
import br.com.automotivo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private MarcaService marcaService;

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody VeiculoRequest veiculoRequest) {
        // Buscar a marca pelo ID fornecido na requisição
        Marca marca = marcaService.buscarPorId(veiculoRequest.getMarcaId());

        // Criar o objeto Veiculo e associar as propriedades
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(veiculoRequest.getModelo());
        veiculo.setCor(veiculoRequest.getCor());
        veiculo.setAno(veiculoRequest.getAno());
        veiculo.setPreco(veiculoRequest.getPreco());
        veiculo.setQuilometragem(veiculoRequest.getQuilometragem());
        veiculo.setDisponivel(veiculoRequest.isDisponivel());
        veiculo.setMarca(marca); // Associar a marca ao veículo

        // Chamar o serviço para salvar o veículo
        Veiculo veiculoCadastrado = veiculoService.cadastrar(veiculo);

        // Retornar a resposta com o veículo cadastrado e status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listar() {
        List<Veiculo> veiculos = veiculoService.listarTodos();
        List<Map<String, Object>> response = veiculos.stream().map(veiculo -> {
            Map<String, Object> veiculoMap = new HashMap<>();
            veiculoMap.put("id", veiculo.getId());
            veiculoMap.put("modelo", veiculo.getModelo());
            veiculoMap.put("cor", veiculo.getCor());
            veiculoMap.put("ano", veiculo.getAno());
            veiculoMap.put("preco", veiculo.getPreco());
            veiculoMap.put("quilometragem", veiculo.getQuilometragem());
            veiculoMap.put("disponivel", veiculo.getDisponivel());
            veiculoMap.put("marca", Map.of(
                    "id", veiculo.getMarca().getId(),
                    "nome", veiculo.getMarca().getNome()
            ));
            return veiculoMap;
        }).toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        return ResponseEntity.ok(veiculoService.atualizar(id, veiculo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        veiculoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}

