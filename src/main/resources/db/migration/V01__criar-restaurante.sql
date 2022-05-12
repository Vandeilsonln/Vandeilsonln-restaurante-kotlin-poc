create table restaurantes (
id serial primary key,
name varchar(50) not null,
tipo varchar(12) not null,
frete float(2) not null,
entrega smallint not null,
distancia numeric(4, 2) not null,
constraint tipo_check check (tipo in ('SUPERMERCADO', 'SOBREMESAS', 'FASTFOOD'))
);