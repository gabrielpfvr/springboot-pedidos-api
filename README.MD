# springboot-pedidos-api
API REST desenvolvida em Java com Spring Boot para controle de pedidos de produtos, com dados de cliente, endereço, produtos e pedidos.\
É possível enviar requisições do tipo GET, POST, PUT e DELETE para Pedidos, que devem conter um cliente, endereço e produtos cadastrados na base de dados.\
O banco utilizado foi banco em memória H2.\
Ao gerar um novo pedido, a API envia o mesmo para uma fila de mensagens do RabbitMQ e também conta com um consumidor responsável por ler a mensagem e exibir no console.

Para compilar o projeto:
```
mvn clean install -DskipTests
```
Para compilar o projeto e rodar os testes:
```
mvn clean install
```
Para iniciar a aplicação:
```
mvn spring-boot:run
```

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

Os testes unitários e o teste de integração foram escritos para os pacotes controller, dto, model, repository e service aleatoriamente entre os módulos do projeto, 
apenas para fins de demonstração do padrão de escrita de testes, sendo assim não estão cobertos 100% dos códigos para simplificar o repositório.


Próximos passos (TODO):
- Corrigir envio de mensagens para fila do RabbitMQ;
- ~~Implementar testes unitários~~;
- ~~Criação de endpoints para cadastro de clientes e produtos~~;
- Implementar MySQL como banco de dados ~~e tratar exceçõe~~s;
- Adicionar ~~Swagger~~, Actuator e autenticação com Spring Security;
- Criação de um microsserviço para receber os pedidos enviados pelo RabbitMQ e salvar em uma tabela de enregas.
