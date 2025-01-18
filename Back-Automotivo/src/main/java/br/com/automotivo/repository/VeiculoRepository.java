package br.com.automotivo.repository;

import br.com.automotivo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByDisponivel(Boolean disponivel);
    List<Veiculo> findByPrecoBetween(Double minPreco, Double maxPreco);
    List<Veiculo> findByAno(Integer ano);
}

