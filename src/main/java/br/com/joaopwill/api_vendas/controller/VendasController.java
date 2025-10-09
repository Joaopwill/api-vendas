package br.com.joaopwill.api_vendas.controller;

import br.com.joaopwill.api_vendas.dto.VendedorRelatorio;
import br.com.joaopwill.api_vendas.model.Vendas;
import br.com.joaopwill.api_vendas.service.VendasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController //Define a classe como controller
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendasService vendasService;

    //Cria uma nova venda
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Retorna 201 Created (padrão para criação de novos recursos)
    public Vendas criarVenda(@RequestBody Vendas venda) { //Transforma o JSON em um objeto Java
        return vendasService.criarVenda(venda); //Chama o metodo na service e retorna o valor convertido em JSON
    }

    //Retorna a lista de vendedores com o resumo de vendas
    @GetMapping("/relatorio-vendedores")
    public ResponseEntity<List<VendedorRelatorio>> getRelatorioVendasPorVendedor(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        try {
            /*Chama o metodo CalcularRelatorioVendas na service passando o periodo de vendas e
            resulta em uma lista do relatorio que é armazenado na variável resumo*/
            List<VendedorRelatorio> resumo = vendasService.CalcularRelatorioVendas(dataInicio, dataFim);
            return ResponseEntity.ok(resumo);
        } catch (IllegalArgumentException e) {
            //Em caso de data de início > data de fim, retorna Bad Request
            return ResponseEntity.badRequest().build();
        }
    }
}

