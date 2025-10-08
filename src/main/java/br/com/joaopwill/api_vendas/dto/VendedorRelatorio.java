package br.com.joaopwill.api_vendas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data //Anotação do Lombok para gerar getters e setters automáticos
@AllArgsConstructor //Recebe todos os parametros em um unico construtor, evitando escrever codigo repetitivo

public class VendedorRelatorio {
    private String nomeVendedor;
    private Long totalVendas;
    private BigDecimal mediaVendas; //Média de vendas por DIA
}
