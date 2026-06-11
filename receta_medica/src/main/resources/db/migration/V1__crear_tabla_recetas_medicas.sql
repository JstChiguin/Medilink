CREATE TABLE recetas_medicas (
                id_receta BIGINT(20) NOT NULL AUTO_INCREMENT,
                id_paciente BIGINT(20) NOT NULL,
                id_profesional BIGINT(20) NOT NULL,
                id_cita BIGINT(20) NOT NULL,
                fecha_emision DATE NOT NULL,
                medicamento VARCHAR(150) NOT NULL,
                dosis VARCHAR(150) NOT NULL,
                frecuencia VARCHAR(150) NOT NULL,
                duracion VARCHAR(150) NOT NULL,
                indicaciones VARCHAR(1000) DEFAULT NULL,

                PRIMARY KEY (id_receta)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;