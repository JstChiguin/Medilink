INSERT INTO `citas` (
    `estado`,
    `fecha_citacion`,
    `fecha_creacion`,
    `hora_citacion`,
    `id_agenda`,
    `id_paciente`,
    `id_profesional`,
    `modalidad_atencion`,
    `motivo`,
    `observaciones`
) VALUES
      ('PENDIENTE', '2026-05-23', '2026-05-22', '09:00:00', 1, 101, 201, 'PRESENCIAL', 'Control general', 'Paciente confirma asistencia'),
      ('CONFIRMADA', '2026-05-23', '2026-05-22', '10:00:00', 2, 102, 202, 'REMOTA', 'Dolor abdominal', 'Videollamada programada'),
      ('PENDIENTE', '2026-05-24', '2026-05-22', '11:00:00', 3, 103, 203, 'PRESENCIAL', 'Chequeo anual', 'Sin observaciones'),
      ('CANCELADA', '2026-05-25', '2026-05-22', '12:00:00', 4, 104, 204, 'REMOTA', 'Control diabetes', 'Paciente canceló cita'),
      ('REALIZADA', '2026-05-25', '2026-05-22', '13:00:00', 5, 105, 205, 'PRESENCIAL', 'Evaluación cardiológica', 'Atención realizada correctamente'),
      ('CONFIRMADA', '2026-05-26', '2026-05-22', '14:00:00', 6, 106, 206, 'REMOTA', 'Consulta respiratoria', 'Paciente conectado'),
      ('PENDIENTE', '2026-05-26', '2026-05-22', '15:00:00', 7, 107, 207, 'PRESENCIAL', 'Control presión arterial', 'Primera consulta'),
      ('REALIZADA', '2026-05-27', '2026-05-22', '16:00:00', 8, 108, 208, 'REMOTA', 'Seguimiento tratamiento', 'Paciente estable'),
      ('CONFIRMADA', '2026-05-27', '2026-05-22', '17:00:00', 9, 109, 209, 'PRESENCIAL', 'Dolor lumbar', 'Debe traer exámenes'),
      ('PENDIENTE', '2026-05-28', '2026-05-22', '08:00:00', 10, 110, 210, 'REMOTA', 'Consulta psicológica', 'Atención virtual');