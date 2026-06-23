CREATE TABLE pacientes (
                           id_paciente         BIGINT          NOT NULL AUTO_INCREMENT,
                           id_usuario          BIGINT          NOT NULL,
                           numero_documento    VARCHAR(20)     NOT NULL UNIQUE,
                           nombre_completo     VARCHAR(100)    NOT NULL,
                           fecha_nacimiento    DATE            NOT NULL,
                           genero              VARCHAR(20)     NOT NULL,
                           estado_activo       BOOLEAN         NOT NULL,
                           fecha_registro      DATETIME        NOT NULL,
                           PRIMARY KEY (id_paciente)
);

CREATE TABLE contacto_paciente (
                                   id_contacto                     BIGINT      NOT NULL AUTO_INCREMENT,
                                   id_paciente                     BIGINT      NOT NULL UNIQUE,
                                   telefono                        VARCHAR(20) NOT NULL,
                                   direccion_paciente              VARCHAR(200) NOT NULL,
                                   nombre_contacto_emergencia      VARCHAR(100) NOT NULL,
                                   telefono_contacto_emergencia    VARCHAR(20) NOT NULL,
                                   PRIMARY KEY (id_contacto),
                                   CONSTRAINT fk_contacto_paciente FOREIGN KEY (id_paciente) REFERENCES pacientes(id_paciente)
);

CREATE TABLE perfil_pacientes (
                                  id_perfil               BIGINT      NOT NULL AUTO_INCREMENT,
                                  id_paciente             BIGINT      NOT NULL UNIQUE,
                                  id_contacto             BIGINT      NOT NULL UNIQUE,
                                  antecedentes_medicos    TEXT,
                                  alergias                TEXT,
                                  medicamentos_actuales   TEXT,
                                  informacion_relevante   TEXT,
                                  ultima_actualizacion    DATETIME    NOT NULL,
                                  PRIMARY KEY (id_perfil),
                                  CONSTRAINT fk_perfil_paciente FOREIGN KEY (id_paciente) REFERENCES pacientes(id_paciente),
                                  CONSTRAINT fk_perfil_contacto FOREIGN KEY (id_contacto) REFERENCES contacto_paciente(id_contacto)
);