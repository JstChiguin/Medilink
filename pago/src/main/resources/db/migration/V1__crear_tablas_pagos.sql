CREATE TABLE pagos (
    id_pago BIGINT(20) NOT NULL AUTO_INCREMENT,
    id_paciente BIGINT(20) NOT NULL,
    id_profesional BIGINT(20) NOT NULL,
    id_cita BIGINT(20) NOT NULL,
    fecha_pago DATE NOT NULL,
    monto DOUBLE NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    estado_pago VARCHAR(50) NOT NULL,

    PRIMARY KEY (id_pago)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;