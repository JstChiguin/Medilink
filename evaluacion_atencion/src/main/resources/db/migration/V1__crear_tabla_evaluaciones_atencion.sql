CREATE TABLE evaluaciones_atencion (
                    id_evaluacion BIGINT(20) NOT NULL AUTO_INCREMENT,
                    id_paciente BIGINT(20) NOT NULL,
                    id_profesional BIGINT(20) NOT NULL,
                    id_cita BIGINT(20) NOT NULL,
                    fecha_evaluacion DATE NOT NULL,
                    calificacion INT NOT NULL,
                    comentario VARCHAR(1000) DEFAULT NULL,

                    PRIMARY KEY (id_evaluacion)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;