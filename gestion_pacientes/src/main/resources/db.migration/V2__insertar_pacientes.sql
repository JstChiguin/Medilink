-- Pacientes (id_usuario referencia al microservicio usuario)
INSERT INTO pacientes (id_usuario, numero_documento, nombre_completo, fecha_nacimiento, genero, estado_activo, fecha_registro) VALUES
                                                                                                                                   (4, '12345678A', 'Juan García',  '1990-05-20', 'Masculino', true,  '2025-02-10 08:00:00'),
                                                                                                                                   (5, '87654321B', 'María López',  '1985-11-03', 'Femenino',  true,  '2025-02-15 09:30:00'),
                                                                                                                                   (6, '11223344C', 'Pedro Soto',   '2000-07-14', 'Masculino', false, '2025-03-01 10:00:00');

-- Contactos
INSERT INTO contacto_paciente (id_paciente, telefono, direccion_paciente, nombre_contacto_emergencia, telefono_contacto_emergencia) VALUES
                                                                                                                                        (1, '+56912345678', 'Av. Providencia 123, Santiago', 'Laura García',  '+56998765432'),
                                                                                                                                        (2, '+56987654321', 'Calle Las Flores 456, Viña',    'Carlos López',  '+56911223344'),
                                                                                                                                        (3, '+56955443322', 'Pasaje Sol 789, Concepción',    'Rosa Soto',     '+56944556677');

-- Perfiles
INSERT INTO perfil_pacientes (id_paciente, id_contacto, antecedentes_medicos, alergias, medicamentos_actuales, informacion_relevante, ultima_actualizacion) VALUES
                                                                                                                                                                (1, 1, 'Hipertensión arterial diagnosticada 2020', 'Penicilina',  'Losartán 50mg diario',  NULL,                        '2025-02-10 08:00:00'),
                                                                                                                                                                (2, 2, 'Sin antecedentes relevantes',              'Ninguna',     'Ninguno',               'Paciente embarazada',       '2025-02-15 09:30:00'),
                                                                                                                                                                (3, 3, 'Asma bronquial desde la infancia',         'Polen, polvo','Salbutamol inhalador',  'Evitar ejercicio extremo',  '2025-03-01 10:00:00');