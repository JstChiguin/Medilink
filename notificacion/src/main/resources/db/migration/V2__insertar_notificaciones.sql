INSERT INTO notificaciones (
    id_paciente,
    id_profesional,
    id_cita,
    tipo_notificacion,
    mensaje,
    fecha_envio,
    estado_notificacion
)
VALUES
    (101, 201, 1, 'EMAIL', 'Recordatorio de cita médica programada', '2026-05-20 08:00:00', 'ENVIADA'),
    (102, 202, 2, 'SMS', 'Su cita remota comenzará pronto', '2026-05-21 08:30:00', 'ENVIADA'),
    (103, 203, 3, 'WHATSAPP', 'Confirmación de cita presencial', '2026-05-22 09:00:00', 'ENVIADA'),
    (104, 204, 4, 'EMAIL', 'Pago pendiente asociado a su atención', '2026-05-23 09:30:00', 'PENDIENTE'),
    (105, 205, 5, 'SMS', 'Receta médica disponible para revisión', '2026-05-24 10:00:00', 'ENVIADA'),
    (106, 206, 6, 'EMAIL', 'Videollamada médica programada', '2026-05-25 10:30:00', 'PENDIENTE'),
    (107, 207, 7, 'WHATSAPP', 'No fue posible enviar la notificación', '2026-05-26 11:00:00', 'FALLIDA'),
    (108, 208, 8, 'EMAIL', 'Evaluación de atención disponible', '2026-05-27 11:30:00', 'ENVIADA'),
    (109, 209, 9, 'SMS', 'Recordatorio de pago pendiente', '2026-05-28 12:00:00', 'PENDIENTE'),
    (110, 210, 10, 'WHATSAPP', 'Su atención ha sido finalizada', '2026-05-29 12:30:00', 'ENVIADA');