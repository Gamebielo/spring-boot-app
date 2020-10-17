drop user 'user'@'localhost';
drop schema anotacao;

/*Crio o banco*/
create schema anotacao;
/*Entro nele*/
use anotacao;
/*Crio o usuario 'user' com o password 'pass123'*/
create user 'user'@'localhost' identified by 'pass123';
/*Dou as permissões desejadas para esse usuário*/
grant select, insert, delete, update on anotacao.* to user@'localhost';

create table usr_usuario (
    usr_id bigint unsigned not null auto_increment,
    usr_nome varchar(20) not null,
    usr_senha varchar(100) not null, /*100 para o bcrypt que o spring gera*/
    primary key (usr_id),
    unique key uni_usuario_nome (usr_nome)
);

create table aut_autorizacao (
    aut_id bigint unsigned not null auto_increment,
    aut_nome varchar(20) not null,
    primary key (aut_id),
    unique key uni_aut_nome (aut_nome)
);
/*Tabela de ligação    N x N */
create table uau_usuario_autorizacao (
    usr_id bigint unsigned not null,
    aut_id bigint unsigned not null,
    primary key (usr_id, aut_id),
    foreign key aut_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
    foreign key aut_autorizacao_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

/*Populando com 1 registro para cada tabela*/
insert into usr_usuario(usr_nome, usr_senha) values("admin", "$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C");
insert into aut_autorizacao(aut_nome) values("ROLE_ADMIN");
insert into uau_usuario_autorizacao values(1, 1);