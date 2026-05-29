CREATE TABLE historial_clinico (
                id_historial BIGINT(20) NOT NULL AUTO_INCREMENT,
                id_paciente BIGINT(20) NOT NULL,
                id_profesional BIGINT(20) NOT NULL,
                id_cita BIGINT(20) NOT NULL,
                fecha_atencion DATE NOT NULL,
                diagnostico VARCHAR(500) NOT NULL,
                observaciones VARCHAR(1000) DEFAULT NULL,
                recomendaciones VARCHAR(1000) DEFAULT NULL,

                PRIMARY KEY (id_historial)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;