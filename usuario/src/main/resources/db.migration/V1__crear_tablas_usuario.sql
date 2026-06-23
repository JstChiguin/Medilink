CREATE TABLE usuarios (
                          id_usuario          BIGINT          NOT NULL AUTO_INCREMENT,
                          nombre_usuario      VARCHAR(150)    NOT NULL,
                          correo_usuario      VARCHAR(150)    NOT NULL UNIQUE,
                          contrasenna_usuario VARCHAR(50)     NOT NULL,
                          rol_usuario         VARCHAR(30)     NOT NULL,
                          estado_usuario      VARCHAR(30)     NOT NULL,
                          fecha_registro      DATE            NOT NULL,
                          PRIMARY KEY (id_usuario)
);