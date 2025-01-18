package br.com.automotivo.service;

import br.com.automotivo.model.Marca;
import br.com.automotivo.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public Marca cadastrar(Marca marca) {
        return marcaRepository.save(marca);
    }

    public List<Marca> listarTodas() {
        return marcaRepository.findAll();
    }

    public Marca buscarPorId(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
    }

    public Marca atualizar(Long id, Marca marcaAtualizada) {
        Marca marca = buscarPorId(id); // Lança exceção se a marca não for encontrada
        marca.setNome(marcaAtualizada.getNome());
        return marcaRepository.save(marca);
    }

    public void remover(Long id) {
        Marca marca = buscarPorId(id); // Lança exceção se a marca não for encontrada
        marcaRepository.delete(marca);
    }
}

