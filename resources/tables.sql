# CREATE DATABASE trescerotres;
# use trescerotres;
# CREATE USER 'empleado'@'localhost' IDENTIFIED BY 'password';
# GRANT ALL PRIVILEGES ON * . * TO 'empleado'@'localhost';


CREATE TABLE cliente(
	id_cliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	fecha_registro DATE NOT NULL,
	activo BOOLEAN NOT NULL DEFAULT 0
);

CREATE TABLE producto(
	id_producto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	cantidad INT NOT NULL DEFAULT 0,
	precio FLOAT NOT NULL,
	activo BOOLEAN NOT NULL DEFAULT 0
);

CREATE TABLE producto_calzado (
	id_producto INT NOT NULL PRIMARY KEY,
	numero INT NOT NULL,
	FOREIGN KEY (id_producto) REFERENCES producto (id_producto) ON DELETE CASCADE
);

CREATE TABLE producto_textil (
	id_producto INT NOT NULL PRIMARY KEY,
	tejido VARCHAR(20) NOT NULL,
	FOREIGN KEY (id_producto) REFERENCES producto (id_producto) ON DELETE CASCADE
);

CREATE TABLE factura(
	id_factura INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cliente INT NOT NULL,
	fecha DATE NOT NULL,
	abierta BOOLEAN NOT NULL DEFAULT 0,
	precio FLOAT NOT NULL,
	FOREIGN KEY (cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE
);

CREATE TABLE linea(
	factura INT NOT NULL,
	producto INT NOT NULL,
	cantidad INT NOT NULL,
	PRIMARY KEY (factura, producto),
	FOREIGN KEY (factura) REFERENCES factura (id_factura) ON DELETE CASCADE,
	FOREIGN KEY (producto) REFERENCES producto (id_producto) ON DELETE CASCADE
);

