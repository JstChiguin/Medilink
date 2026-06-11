INSERT INTO pagos (
    id_paciente,
    id_profesional,
    id_cita,
    fecha_pago,
    monto,
    metodo_pago,
    estado_pago
)
VALUES
    (101, 201, 1, '2026-05-20', 25000, 'EFECTIVO', 'PAGADO'),
    (102, 202, 2, '2026-05-21', 30000, 'TARJETA', 'PAGADO'),
    (103, 203, 3, '2026-05-22', 45000, 'TRANSFERENCIA', 'PAGADO'),
    (104, 204, 4, '2026-05-23', 18000, 'EFECTIVO', 'PENDIENTE'),
    (105, 205, 5, '2026-05-24', 55000, 'TARJETA', 'PAGADO'),
    (106, 206, 6, '2026-05-25', 22000, 'TRANSFERENCIA', 'PAGADO'),
    (107, 207, 7, '2026-05-26', 28000, 'EFECTIVO', 'RECHAZADO'),
    (108, 208, 8, '2026-05-27', 35000, 'TARJETA', 'PAGADO'),
    (109, 209, 9, '2026-05-28', 40000, 'TRANSFERENCIA', 'PENDIENTE'),
    (110, 210, 10, '2026-05-29', 50000, 'TARJETA', 'PAGADO');