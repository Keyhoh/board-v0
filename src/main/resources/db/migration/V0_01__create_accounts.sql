create table accounts
(
    username varchar(64) not null primary key,
    password varchar(64) not null,
    enabled  boolean     not null
);

insert into accounts values ('system',crypt('password', gen_salt('bf',10)),true);