INSERT INTO CLIENTE(nome, cpf, email, telefone)
VALUES('Gabriel Motta', '12345678900', 'gabriel@email.com', '43988776655');

INSERT INTO ENDERECO(estado, cidade, logradouro, numero, complemento, cep, bairro, referencia, fk_cliente)
VALUES('PR', 'Londrina', 'Rua ABCD', 60, null, '88000000', 'Centro', null, 1);

INSERT INTO PRODUTO(nome, preco) VALUES ('Smart TV', '2000');
INSERT INTO PRODUTO(nome, preco) VALUES ('Smartphone', '2500');
INSERT INTO PRODUTO(nome, preco) VALUES ('Headset wireless', '700');
INSERT INTO PRODUTO(nome, preco) VALUES ('Teclado mecânico', '500');
INSERT INTO PRODUTO(nome, preco) VALUES ('Air Fryer', '400');
INSERT INTO PRODUTO(nome, preco) VALUES ('Webcam 1080p', '650');
INSERT INTO PRODUTO(nome, preco) VALUES ('Fender Stratocaster', '2500');
