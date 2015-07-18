--liquibase formatted sql
--changeset fabio.barbosa:01.01
CREATE TABLE user
(
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255),
  PRIMARY KEY (id)
);
--rollback drop table user

--changeset fabio.barbosa:01.02
INSERT INTO user (name) VALUES ('Usuário 1');
INSERT INTO user (name) VALUES ('Usuário 2');
INSERT INTO user (name) VALUES ('Usuário 3');
INSERT INTO user (name) VALUES ('Usuário 4');
INSERT INTO user (name) VALUES ('Usuário 5');
--rollback delete from user;