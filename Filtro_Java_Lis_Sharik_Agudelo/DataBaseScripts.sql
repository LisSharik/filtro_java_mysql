CREATE TABLE tienda(
	id_tienda INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    ubucacion VARCHAR(255) NOT NULL
);

INSERT INTO tienda (nombre, ubucacion) VALUES ("KOAJ", "De moda aulet");
INSERT INTO tienda (nombre, ubucacion) VALUES ("MIMOS", "De moda aulet");
INSERT INTO tienda (nombre, ubucacion) VALUES ("Branchos", "De moda aulet");

SELECT * FROM tienda ORDER BY tienda.id_tienda;



CREATE TABLE producto(
	id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    id_tienda INT,
    FOREIGN KEY (id_tienda) REFERENCES tienda(id_tienda) ON UPDATE CASCADE ON DELETE CASCADE    
);

ALTER TABLE producto ADD COLUMN stock INT;

SELECT * FROM producto;
DELETE FROM producto WHERE producto.id_producto = 1;
INSERT INTO producto(nombre,precio,id_tienda,stock) VALUES ("Zapatos", "200000",  1, 40);

SELECT * FROM producto
INNER JOIN tienda on tienda.id_tienda = producto.id_tienda
ORDER BY producto.id_producto ASC;

SELECT * FROM producto
INNER JOIN tienda on tienda.id_tienda = producto.id_tienda
WHERE tienda.nombre LIKE '%mi%' 
ORDER BY producto.id_producto ASC;


UPDATE producto SET nombre = "Chanclas", precio = 40000, id_tienda = 3,stock = 5 WHERE id_producto = 2;


CREATE TABLE cliente(
	id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email varchar(255) NOT NULL
);

SELECT * FROM cliente ORDER BY cliente.id_cliente;

SELECT * FROM cliente
WHERE cliente.nombre LIKE '%ju%'
ORDER BY cliente.id_cliente;

INSERT INTO cliente (nombre, apellido,email) VALUES ("Cliente", "#1", "cliente1@email.com");
DELETE FROM cliente WHERE cliente.id_cliente = 3;
UPDATE cliente SET nombre = "Luis", apellido = "Sanchez", email = "luis@email.com" WHERE id_cliente = 4;


CREATE TABLE compra(
	id_compra INT AUTO_INCREMENT PRIMARY KEY,
    fecha_compra TIMESTAMP NOT NULL,
    cantidad INT NOT NULL,
    id_cliente INT,
    id_producto INT,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON UPDATE CASCADE ON DELETE CASCADE
);

ALTER TABLE compra
MODIFY COLUMN fecha_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

SELECT * FROM compra;
INSERT INTO compra (cantidad, id_cliente, id_producto) VALUES (2,1, 3);

SELECT * FROM compra
INNER JOIN cliente on cliente.id_cliente = compra.id_cliente
INNER JOIN producto on producto.id_producto = compra.id_producto
INNER JOIN tienda on tienda.id_tienda = producto.id_tienda
WHERE producto.nombre LIKE '%z%'
ORDER BY compra.id_compra ASC;

SELECT * FROM compra
INNER JOIN cliente on cliente.id_cliente = compra.id_cliente
INNER JOIN producto on producto.id_producto = compra.id_producto
INNER JOIN tienda on tienda.id_tienda = producto.id_tienda
ORDER BY compra.id_compra ASC;



UPDATE compra SET cantidad = 4, id_cliente = 1, id_producto = 4 WHERE id_compra = 2;
DELETE FROM compra WHERE compra.id_compra = 4;






