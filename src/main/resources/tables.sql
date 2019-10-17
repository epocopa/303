# CREATE DATABASE trescerotres;
# use trescerotres;
# CREATE USER 'empleado'@'localhost' IDENTIFIED BY 'password';
# GRANT ALL PRIVILEGES ON * . * TO 'empleado'@'localhost';


CREATE TABLE cliente(
	id_cliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL UNIQUE,
	fecha_registro DATE NOT NULL,
	activo BOOLEAN NOT NULL DEFAULT 0
);

