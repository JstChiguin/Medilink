INSERT INTO videollamadas (
    id_cita,
    id_paciente,
    id_profesional,
    fecha_programada,
    hora_inicio,
    hora_termino,
    enlace_sesion,
    estado_videollamada
)
VALUES

    (1, 101, 201, '2026-06-01', '09:00:00', '09:30:00',
     'https://meet.medilink.cl/sesion/001', 'FINALIZADA'),

    (2, 102, 202, '2026-06-01', '10:00:00', '10:30:00',
     'https://meet.medilink.cl/sesion/002', 'FINALIZADA'),

    (3, 103, 203, '2026-06-02', '11:00:00', '11:30:00',
     'https://meet.medilink.cl/sesion/003', 'FINALIZADA'),

    (4, 104, 204, '2026-06-03', '12:00:00', NULL,
     'https://meet.medilink.cl/sesion/004', 'PROGRAMADA'),

    (5, 105, 205, '2026-06-03', '13:00:00', NULL,
     'https://meet.medilink.cl/sesion/005', 'PROGRAMADA'),

    (6, 106, 206, '2026-06-04', '14:00:00', NULL,
     'https://meet.medilink.cl/sesion/006', 'PROGRAMADA'),

    (7, 107, 207, '2026-06-05', '15:00:00', NULL,
     'https://meet.medilink.cl/sesion/007', 'EN_CURSO'),

    (8, 108, 208, '2026-06-05', '16:00:00', NULL,
     'https://meet.medilink.cl/sesion/008', 'PROGRAMADA'),

    (9, 109, 209, '2026-06-06', '17:00:00', NULL,
     'https://meet.medilink.cl/sesion/009', 'CANCELADA'),

    (10, 110, 210, '2026-06-07', '18:00:00', NULL,
     'https://meet.medilink.cl/sesion/010', 'PROGRAMADA');