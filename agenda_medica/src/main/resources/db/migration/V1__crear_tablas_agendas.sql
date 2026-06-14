CREATE TABLE agendas (
                id_agenda BIGINT AUTO_INCREMENT PRIMARY KEY,
                id_profesional BIGINT NOT NULL,
                fecha DATE NOT NULL,
                hora_inicio TIME NOT NULL,
                hora_fin TIME NOT NULL,
                estado VARCHAR(50) NOT NULL,
                observacion VARCHAR(200),

                CONSTRAINT uk_agenda_profesional_fecha_hora
                    UNIQUE (id_profesional, fecha, hora_inicio)
);