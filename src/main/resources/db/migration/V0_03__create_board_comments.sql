create table board_comments
(
    id         serial primary key,
    username   varchar(64) not null,
    text       text,
    created_at timestamp   not null
);

insert into board_comments (username, text, created_at) values('system', 'Hello world!', current_timestamp);