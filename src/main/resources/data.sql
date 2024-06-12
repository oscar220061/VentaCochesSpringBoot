CREATE TABLE IF NOT EXISTS coches (
    year NUMERIC,
    color VARCHAR(20),
    marca VARCHAR(50),
    matricula VARCHAR(15) unique,
    modelo VARCHAR(50),
    precio DECIMAL(10,2)
);

INSERT INTO coches (year, color, marca, matricula, modelo, precio) VALUES
(2020, 'Rojo', 'Toyota', '7433CVN', 'Corolla', 20000),
(2019, 'Azul', 'Honda', '8743KJD', 'Civic', 22000);

INSERT INTO USUARIOS(apellidos, dni, email, nombre, telefono) VALUES
('Garcia Lopez', '12345678A', 'garcia.lopez@example.com', 'Juan', '600123456'),
('Martinez Perez', '23456789B', 'martinez.perez@example.com', 'Ana', '600234567'),
('Rodriguez Gomez', '34567890C', 'rodriguez.gomez@example.com', 'Luis', '600345678');

INSERT INTO VENTAS(fecha, id_cliente, matricula, total)VALUES
('2024-06-10', 1, '7433CVN', 20000.00);

