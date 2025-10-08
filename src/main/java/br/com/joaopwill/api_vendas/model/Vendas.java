package br.com.joaopwill.api_vendas.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity //Definindo como entidade JPA
@Data //Anotação do Lombok para gerar getters e setters automáticos
@NoArgsConstructor //Anotação do Lombok para permitir um construtor sem argumentos
public class Vendas {
    @Id //Definindo Vendas como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDate dataVenda;
    private BigDecimal valor;
    private long vendedorId;
    private String vendedorNome;

    //Construtor para novos objetos Vendas, passando os atributos obrigatoriamente
    public Vendas(LocalDate dataVenda, BigDecimal valor, long vendedorId, String vendedorNome){
        this.dataVenda = dataVenda;
        this.valor = valor;
        this.vendedorId = vendedorId;
        this.vendedorNome = vendedorNome;
    }
}
