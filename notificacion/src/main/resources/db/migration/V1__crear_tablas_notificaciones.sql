CREATE TABLE notificaciones (
            id_notificacion BIGINT(20) NOT NULL AUTO_INCREMENT,
            id_paciente BIGINT(20) NOT NULL,
            id_profesional BIGINT(20) NOT NULL,
            id_cita BIGINT(20) NOT NULL,
            tipo_notificacion VARCHAR(50) NOT NULL,
            mensaje VARCHAR(1000) NOT NULL,
            fecha_envio DATETIME NOT NULL,
            estado_notificacion VARCHAR(50) NOT NULL,

            PRIMARY KEY (id_notificacion)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;