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

POST: /vendas\
GET: /relatorio-vendedores

