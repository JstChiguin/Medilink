INSERT INTO agendas
(id_profesional, fecha, hora_inicio, hora_fin, estado, observacion)
VALUES
    (1, '2026-06-20', '08:00:00', '08:30:00', 'DISPONIBLE', 'Control medicina interna'),
    (1, '2026-06-20', '08:30:00', '09:00:00', 'DISPONIBLE', 'Atencion pacientes cronicos'),
    (1, '2026-06-20', '09:00:00', '09:30:00', 'RESERVADA', 'Hora reservada'),
    (2, '2026-06-21', '10:00:00', '10:30:00', 'DISPONIBLE', 'Consulta general'),
    (2, '2026-06-21', '10:30:00', '11:00:00', 'BLOQUEADA', 'Reunion clinica'),
    (2, '2026-06-21', '11:00:00', '11:30:00', 'DISPONIBLE', 'Atencion ambulatoria'),
    (3, '2026-06-22', '08:00:00', '08:30:00', 'RESERVADA', 'Paciente confirmado'),
    (3, '2026-06-22', '08:30:00', '09:00:00', 'DISPONIBLE', 'Bloque libre'),
    (4, '2026-06-23', '14:00:00', '14:30:00', 'DISPONIBLE', 'Control postoperatorio'),
    (4, '2026-06-23', '14:30:00', '15:00:00', 'BLOQUEADA', 'Capacitacion profesional');