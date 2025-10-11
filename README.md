API de Vendas e Relatórios de Desempenho

Esta API como solitado por e-mail, foi desenvolvida em Java com Spring Boot para gerenciar o registro de vendas e fornecer relatórios de desempenho de vendedores por período. Optei por utiliza banco de dados H2 em memória.

O projeto segue a arquitetura MVC utilizando apenas Controller, Service e Repository visto que não era necessário possuir frontend.

Linguagem: Java 17+\
Framework: Spring Boot 3\
Persistência: Spring Data JPA\
Banco de Dados: H2 Database\
Ferramenta de Build: Maven\
Desenvolvimento: Lombok (pela geração automática de getters, setters e construtores)

A entidade Venda é a base do sistema, contendo o registro de uma única transação

⚙️ Pré-requisitos e Execução\
Para testar a aplicação, você precisará de\
JDK (Java Development Kit): Versão 17 ou superior.\
Maven: Para compilar e rodar o projeto.\
Cliente HTTP: Postman ou Insomnia (necessário para enviar o corpo JSON nos testes POST).

POST: /vendas \
GET: /relatorio-vendedores \
UPDATE: /vendas/relatorio-vendedores \
DELETE: /vendas

Exemplos para testar o CRUD:\

CREATE:\
http://localhost:8080/vendas \
json { \
"dataVenda": "2025-10-15",\
"valor": 120.00, \
"vendedorId": 101, \
"vendedorNome": "Carlos Lima" \
}

READ:\
http://localhost:8080/vendas/relatorio-vendedores?dataInicio=2025-10-10&dataFim=2025-10-16 \
Resultado: Retorna o resumo para Carlos Lima

http://localhost:8080/vendas/relatorio-vendedores?dataInicio=2025-10-10&dataFim=2025-10-15 \
Resultado: Retorna 1 venda

UPDATE:\
http://localhost:8080/vendas/1 \
json { "id": 1, \
"dataVenda": "2025-10-15", \
"valor": 500.00, "vendedorId": 101, \
"vendedorNome": "Carlos Lima" \
} \
Resultado: O campo valor da venda 1 é alterado para 500.00.

DELETE:\
http://localhost:8080/vendas/1 \
Resultado: Status: 204 No Content ou 200 OK. (Confirmação de que o recurso foi removido).
