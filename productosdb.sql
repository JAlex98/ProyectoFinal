create database ProductosDb;

use ProductosDb;

create table producto(
	id int PRIMARY KEY AUTO_INCREMENT,
	nombre varchar(255),
	tipo varchar(255),
	precio double
);