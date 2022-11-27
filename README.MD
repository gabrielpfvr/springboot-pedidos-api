# springboot-pedidos-api
API REST desenvolvida em Java com Spring Boot para controle de pedidos de produtos, com dados de cliente, endereço, produtos e pedidos.\
É possível enviar requisições do tipo GET, POST, PUT e DELETE para Pedidos, que devem conter um cliente, endereço e produtos cadastrados na base de dados.\
O banco utilizado foi banco em memória H2.\
Ao gerar um novo pedido, a API envia o mesmo para uma fila de mensagens do RabbitMQ e também conta com um consumidor responsável por ler a mensagem e exibir no console.

Modelo de requisição:
```
{
   "produtosIds": [1, 2, 3],
   "clienteId": 1,
   "enderecoEntregaId": 1
}
```
A documentação completa pode ser encontrada acessando o Swagger da aplicação:\
http://localhost:8080/swagger-ui/index.html


Próximos passos (TODO):
- Implementar testes unitários;
- ~~Criação de endpoints para cadastro de clientes e produtos~~;
- Implementar MySQL como banco de dados e tratar exceções;
- Adicionar ~~Swagger~~, Actuator e autenticação com Spring Security;
- Criação de um microsserviço para receber os pedidos enviados pelo RabbitMQ e salvar em uma tabela de enregas.