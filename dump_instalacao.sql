/* Grupos */

insert into t_grupo (id_grupo, descricao, status) values(1,'admin',1);
insert into t_grupo (id_grupo, descricao, status) values(2,'user',1);

/* Usuarios */
insert into t_usuario(id_usuario, email, login, senha, status, url_foto, idgrupo) values(1,'administrador@kameleon.com.br','sistema','123456',1,'https://secure.gravatar.com/avatar/82dac0d6f9fa610aed12eb13be7db318.jpg?s=25&r=pg&d=monsterid&',1);

/* Versão */
insert into t_versao(id_versao, data, descricao) values(1,now(),'Sem Versão');

/* Prioridade */
insert into t_prioridade(id_prioridade, descricao, version) values (1,'Alta',0);
insert into t_prioridade(id_prioridade, descricao, version) values (2,'Média',0);
insert into t_prioridade(id_prioridade, descricao, version) values (3,'Baixa',0);

/* Categoria */
insert into t_categoria(id_categoria, descricao, version) values (1,'Novo Requisito',0);
insert into t_categoria(id_categoria, descricao, version) values (2,'Defeito',0);
insert into t_categoria(id_categoria, descricao, version) values (3,'Melhoria',0);

/* Situacao */
insert into t_situacao(id_situacao, descricao, version) values (1,'Aberta',0);
insert into t_situacao(id_situacao, descricao, version) values (2,'Andamento',0);
insert into t_situacao(id_situacao, descricao, version) values (3,'Fechada',0);

/* Cliente *//
insert into t_cliente(id_cliente, nome, version) values (1,'Todos',0);
