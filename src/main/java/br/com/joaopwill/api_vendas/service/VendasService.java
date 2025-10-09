package br.com.joaopwill.api_vendas.service;

import br.com.joaopwill.api_vendas.dto.VendedorRelatorio;
import br.com.joaopwill.api_vendas.model.Vendas;
import br.com.joaopwill.api_vendas.repository.VendasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service //Spring entende que dece tratar essa classe como componente de serviço
public class VendasService {
    @Autowired
    private VendasRepository vendasRepository; //Armazena a instancia do Vendasrepository para realizar as operações do banco de dados

    //Cria um nova venda
    public Vendas criarVenda(Vendas venda){

        if (venda.getDataVenda() == null){ //Verifica se a propriedade que armazena a data no objeto está vazia
            venda.setDataVenda(LocalDate.now()); //Pega a data de hoje caso não seja passada uma
        }
        return vendasRepository.save(venda); //Metodo para salvar a venda no banco de dados
    }

    //Retorna o relatorio de vendas do vendedor
    public List<VendedorRelatorio>CalcularRelatorioVendas(LocalDate dataInicio, LocalDate dataFim){
        if(dataInicio.isBefore(dataFim)){
            throw new IllegalArgumentException("A data do inicio não pode ser posterior a data do fim");
        }

        long qtdDias = ChronoUnit.DAYS.between(dataInicio, dataFim) + 1; //O metodo between retorna a diferença entre os dias

        List <Vendas> qtdVendas = vendasRepository.findbyDataVendaBetween(dataInicio, dataFim); //Chama o metodo do repository para obter todas as vendas realizadas na data informada

        Map<String, List<Vendas>> vendasPorVendedor = qtdVendas.stream() //Transforma a lista das vendas buscadas em uma stream para mexer com os dados dela sem modificar a lista original
                .collect(Collectors.groupingBy(Vendas::getVendedorNome)); //Define o criterio de agrupamento pegando o nome do vendedor de cada objeto Vendas

        return vendasPorVendedor.entrySet().stream()//cada entry contem a chave (nome do vendedor) e o valor é a lista de vendas dele
                .map(entry -> {
                    String nomeVendedor = entry.getKey();
                    List<Vendas> vendasDoVendedor = entry.getValue();

                    //Quantidade de vendas em números de vendas em não em valor
                    Long totalVendas = (long) vendasDoVendedor.size();

                    BigDecimal totalVendasDecimal = new BigDecimal(totalVendas);
                    BigDecimal dias = new BigDecimal(qtdDias);

                    //Cálculo da média: Total de Vendas / Total de Dias no Período
                    BigDecimal mediaDiaria = totalVendasDecimal.divide(dias, 2, RoundingMode.HALF_UP);

                    return new VendedorRelatorio(nomeVendedor, totalVendas, mediaDiaria); //Retorna o Relatorio
                })
                .collect(Collectors.toList());
    }
}
