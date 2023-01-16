INSERT INTO CLIENTE(id, nome, cpf, email, telefone)
VALUES(1, 'Gabriel Motta', '12345678900', 'gabriel@email.com', '43988776655');

INSERT INTO ENDERECO(id, estado, cidade, logradouro, numero, complemento, cep, bairro, referencia, fk_cliente)
VALUES(1, 'PR', 'Londrina', 'Rua ABCD', 60, null, '88000000', 'Centro', null, 1);

INSERT INTO PRODUTO(id, nome, preco) VALUES (1, 'Smart TV', '2000');
INSERT INTO PRODUTO(id, nome, preco) VALUES (2, 'Smartphone', '2500');
INSERT INTO PRODUTO(id, nome, preco) VALUES (3, 'Headset wireless', '700');
INSERT INTO PRODUTO(id, nome, preco) VALUES (4, 'Teclado mecanico', '500');
INSERT INTO PRODUTO(id, nome, preco) VALUES (5, 'Air Fryer', '400');
INSERT INTO PRODUTO(id, nome, preco) VALUES (6, 'Webcam 1080p', '650');
INSERT INTO PRODUTO(id, nome, preco) VALUES (7, 'Fender Stratocaster', '2500');
