CREATE SCHEMA IF NOT EXISTS smartocupation;
USE smartocupation;

CREATE TABLE IF NOT EXISTS Viviendas (
    cod_referencia VARCHAR(20) PRIMARY KEY,
    ubicacion VARCHAR(100) NOT NULL,
    metros INT,
    num_habitaciones INT,
    num_banos INT,
    precio_mensual DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS Clientes (
    id_cliente VARCHAR(15) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    datos_personales TEXT
);

CREATE TABLE IF NOT EXISTS Facturas(
	id_factura VARCHAR (15) PRIMARY KEY,
    nombre_emisor VARCHAR(100) NOT NULL,
    direccion_emisor VARCHAR(100) NOT NULL,
    nif_emisor VARCHAR(9) NOT NULL,
    total DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS Alquileres (
    num_expediente VARCHAR(20) PRIMARY KEY,
    fecha_entrada DATE NOT NULL,
    tiempo_estimado INT (10),
    estado_cobro VARCHAR(30),
    id_cliente VARCHAR(15),
    ref_vivienda VARCHAR(20),
	datos_facturacion VARCHAR (15),

    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (ref_vivienda) REFERENCES Viviendas(cod_referencia),
    FOREIGN KEY (datos_facturacion) REFERENCES Facturas (id_factura)
);
