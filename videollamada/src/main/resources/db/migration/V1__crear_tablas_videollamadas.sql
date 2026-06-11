CREATE TABLE videollamadas (
            id_videollamada BIGINT(20) NOT NULL AUTO_INCREMENT,
            id_cita BIGINT(20) NOT NULL,
            id_paciente BIGINT(20) NOT NULL,
            id_profesional BIGINT(20) NOT NULL,
            fecha_programada DATE NOT NULL,
            hora_inicio TIME NOT NULL,
            hora_termino TIME DEFAULT NULL,
            enlace_sesion VARCHAR(500) NOT NULL,
            estado_videollamada VARCHAR(50) NOT NULL,

            PRIMARY KEY (id_videollamada)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;