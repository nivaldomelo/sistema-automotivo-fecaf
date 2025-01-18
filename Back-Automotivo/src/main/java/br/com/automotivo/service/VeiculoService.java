package br.com.automotivo.service;

import br.com.automotivo.model.Veiculo;
import br.com.automotivo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo cadastrar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public List<Veiculo> buscarPorFiltros(Boolean disponivel, Double minPreco, Double maxPreco, Integer ano) {
        // Adicionar lógica para combinar filtros
        return veiculoRepository.findByDisponivel(disponivel);
    }

    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        veiculo.setModelo(veiculoAtualizado.getModelo());
        veiculo.setPreco(veiculoAtualizado.getPreco());
        veiculo.setQuilometragem(veiculoAtualizado.getQuilometragem());
        veiculo.setDisponivel(veiculoAtualizado.getDisponivel());
        return veiculoRepository.save(veiculo);
    }

    public void remover(Long id) {
        veiculoRepository.deleteById(id);
    }
}

