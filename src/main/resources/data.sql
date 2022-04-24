INSERT INTO ENDERECO(bairro, cep, cidade, estado, logradouro, numero) VALUES ('Lourenco Bacarin', '86200000', 'Ibiporã', 'Paraná', 'Rua Limoeiro', '36');
INSERT INTO ENDERECO(bairro, cep, cidade, estado, logradouro, numero) VALUES ('Vila Ipiranga', '86010020', 'Londrina', 'Paraná', 'Av. Bandeirantes', '1151');

INSERT INTO CLIENTE(nome, email, senha) VALUES('Gabriel', 'gabriel@email.com', '123456');
INSERT INTO CLIENTE(nome, email, senha) VALUES('João', 'joao@email.com', '123456');


INSERT INTO PRODUTO(nome, preco) VALUES ('TV Smart', '2000');
INSERT INTO PRODUTO(nome, preco) VALUES ('Smartphone', '2500');
INSERT INTO PRODUTO(nome, preco) VALUES ('Microondas', '700');

INSERT INTO PEDIDO(cliente_id, data_criacao, endereco_id) VALUES (1, '2020-04-04 19:00:00', 1);
INSERT INTO PEDIDO(cliente_id, data_criacao, endereco_id) VALUES (2, '2020-04-04 19:00:00', 2);


INSERT INTO PEDIDO_PRODUTOS(pedido_id, produtos_id) VALUES (1, 1);
INSERT INTO PEDIDO_PRODUTOS(pedido_id, produtos_id) VALUES (1, 2);
INSERT INTO PEDIDO_PRODUTOS(pedido_id, produtos_id) VALUES (2, 2);
INSERT INTO PEDIDO_PRODUTOS(pedido_id, produtos_id) VALUES (2, 3);