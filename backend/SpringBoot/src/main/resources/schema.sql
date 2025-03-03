CREATE TABLE cliente ( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE game (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL
);

CREATE TABLE prestamo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_juego VARCHAR(255) NOT NULL,
    nombre_cliente VARCHAR(255) NOT NULL,
    fecha_creacion DATE NOT NULL,
    fecha_devolucion DATE
);