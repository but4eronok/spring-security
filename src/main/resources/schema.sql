create table user(
                      id int auto_increment primary key,
                      login varchar not null,
                      password varchar not null,
                      role varchar not null
);

insert into user (login, password, role) values
                                                   ('admin', '12345', 'ADMIN'),
                                                   ('api' , '54321', 'API' )