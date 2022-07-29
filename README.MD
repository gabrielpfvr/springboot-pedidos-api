# springboot-rest-api
API REST desenvolvida em Java com Spring Boot para controle de pedidos de produtos, com dados de cliente, endereço, produtos e pedidos.\
É possível enviar requisições CRUD do tipo GET, POST, PUT e DELETE para Pedidos, que devem conter um cliente, endereço e produtos cadastrados na base de dados.\
O banco utilizado foi banco em memória H2.\
Ao gerar um novo pedido, a API envia o mesmo para uma fila de mensagens do RabbitMQ e também conta com um consumidor responsável por ler a mensagem e exibir no console.
