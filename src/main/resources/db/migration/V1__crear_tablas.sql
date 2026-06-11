CREATE TABLE `citas` (
        `id_cita` BIGINT(20) NOT NULL AUTO_INCREMENT,
        `estado` VARCHAR(50) NOT NULL,
        `fecha_citacion` DATE NOT NULL,
        `fecha_creacion` DATE NOT NULL,
        `hora_citacion` TIME NOT NULL,
        `id_agenda` BIGINT(20) NOT NULL,
        `id_paciente` BIGINT(20) NOT NULL,
        `id_profesional` BIGINT(20) NOT NULL,
        `modalidad_atencion` VARCHAR(50) NOT NULL,
        `motivo` VARCHAR(100) NOT NULL,
        `observaciones` VARCHAR(200) DEFAULT NULL,
        PRIMARY KEY (`id_cita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;