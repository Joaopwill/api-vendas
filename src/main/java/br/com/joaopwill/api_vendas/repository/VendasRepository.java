package br.com.joaopwill.api_vendas.repository;

import br.com.joaopwill.api_vendas.model.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository //Anotação do Spring para marcar como persistencia

public interface VendasRepository extends JpaRepository<Vendas, Long> {
    List<Vendas> findByDataVendaBetween(LocalDate dataInicio, LocalDate dataFim); //Buscar a Entidade Venda pelo filtro dataVenda. O BETWEEN busca entre dois parâmetros (dataInicio e dataFim).
}