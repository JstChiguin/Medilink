package medilink.cita.service;

// =========================
// IMPORTS DEL PROYECTO
// =========================

import medilink.cita.client.AgendaClient;
import medilink.cita.dto.request.CitaRequest;
import medilink.cita.dto.response.AgendaResponse;
import medilink.cita.dto.response.CitaResponse;
import medilink.cita.mapper.CitaMapper;
import medilink.cita.model.entity.Cita;
import medilink.cita.model.enums.EstadoCita;
import medilink.cita.model.enums.ModalidadCita;
import medilink.cita.repository.CitaRepository;

// =========================
// IMPORTS JUNIT Y MOCKITO
// =========================

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// =========================
// HABILITA MOCKITO
// =========================

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    // =========================
    // DEPENDENCIAS SIMULADAS
    // =========================

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private CitaMapper citaMapper;

    @Mock
    private AgendaClient agendaClient;

    // =========================
    // SERVICIO A PROBAR
    // =========================

    @InjectMocks
    private CitaService citaService;

    // =========================
    // OBJETOS DE APOYO
    // =========================

    private Cita cita;
    private CitaRequest citaRequest;
    private CitaResponse citaResponse;
    private AgendaResponse agendaResponse;

    // =========================
    // DATOS DE PRUEBA
    // =========================

    @BeforeEach
    void setUp() {

        // Entidad simulada

        cita = new Cita();
        cita.setIdCita(1L);
        cita.setIdPaciente(101L);
        cita.setIdProfesional(201L);
        cita.setIdAgenda(1L);
        cita.setFechaCita(LocalDate.of(2026, 6, 20));
        cita.setHoraCita(LocalTime.of(10, 30));
        cita.setModalidadAtencionCita(ModalidadCita.PRESENCIAL);
        cita.setEstadoCita(EstadoCita.CONFIRMADA);
        cita.setMotivoCita("Control médico");
        cita.setObservacionesCita("Paciente estable");
        cita.setFechaCreacionCita(LocalDate.now());

        // Request simulada

        citaRequest = new CitaRequest();
        citaRequest.setIdPaciente(101L);
        citaRequest.setIdProfesional(201L);
        citaRequest.setIdAgenda(1L);
        citaRequest.setFechaCita(LocalDate.of(2026, 6, 20));
        citaRequest.setHoraCita(LocalTime.of(10, 30));
        citaRequest.setModalidadAtencionCita(ModalidadCita.PRESENCIAL);
        citaRequest.setEstadoCita(EstadoCita.CONFIRMADA);
        citaRequest.setMotivoCita("Control médico");
        citaRequest.setObservacionesCita("Paciente estable");
        citaRequest.setFechaCreacionCita(LocalDate.now());

        // Response simulada

        citaResponse = new CitaResponse();
        citaResponse.setIdCita(1L);
        citaResponse.setIdPaciente(101L);
        citaResponse.setIdProfesional(201L);
        citaResponse.setIdAgenda(1L);
        citaResponse.setFechaCita(LocalDate.of(2026, 6, 20));
        citaResponse.setHoraCita(LocalTime.of(10, 30));
        citaResponse.setModalidadAtencionCita(ModalidadCita.PRESENCIAL);
        citaResponse.setEstadoCita(EstadoCita.CONFIRMADA);
        citaResponse.setMotivoCita("Control médico");
        citaResponse.setObservacionesCita("Paciente estable");
        citaResponse.setFechaCreacionCita(LocalDate.now());

        // Agenda simulada

        agendaResponse = new AgendaResponse();
        agendaResponse.setIdAgenda(1L);
    }

    // ==========================================================
    // TEST 1
    // DEBE OBTENER TODAS LAS CITAS
    // ==========================================================

    @Test
    void obtenerTodasLasCitas_debeRetornarListaDeCitas() {

        when(citaRepository.findAll())
                .thenReturn(List.of(cita));

        when(citaMapper.toResponseList(List.of(cita)))
                .thenReturn(List.of(citaResponse));

        List<CitaResponse> resultado =
                citaService.obtenerTodasLasCitas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdCita());

        verify(citaRepository, times(1)).findAll();
    }

    // ==========================================================
    // TEST 2
    // DEBE CREAR UNA CITA CORRECTAMENTE
    // ==========================================================

    @Test
    void crearCita_debeGuardarYRetornarCita() {

        when(agendaClient.obtenerAgendaPorId(1L))
                .thenReturn(agendaResponse);

        when(citaRepository.existsByFechaCitaAndHoraCita(
                citaRequest.getFechaCita(),
                citaRequest.getHoraCita()))
                .thenReturn(false);

        when(citaMapper.toEntity(citaRequest))
                .thenReturn(cita);

        when(citaRepository.save(cita))
                .thenReturn(cita);

        when(citaMapper.toResponse(cita))
                .thenReturn(citaResponse);

        CitaResponse resultado =
                citaService.crearCita(citaRequest);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCita());
        assertEquals(
                EstadoCita.CONFIRMADA,
                resultado.getEstadoCita()
        );

        verify(citaRepository, times(1)).save(cita);
    }

    // ==========================================================
    // TEST 3
    // NO DEBE CREAR CITA SI LA AGENDA NO EXISTE
    // ==========================================================

    @Test
    void crearCita_debeLanzarErrorCuandoAgendaNoExiste() {

        when(agendaClient.obtenerAgendaPorId(1L))
                .thenReturn(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> citaService.crearCita(citaRequest)
        );
    }

    // ==========================================================
    // TEST 4
    // NO DEBE CREAR CITA EN DOMINGO
    // ==========================================================

    @Test
    void crearCita_debeLanzarErrorCuandoEsDomingo() {

        citaRequest.setFechaCita(
                LocalDate.of(2026, 6, 21)
        );

        when(agendaClient.obtenerAgendaPorId(1L))
                .thenReturn(agendaResponse);

        assertThrows(
                IllegalArgumentException.class,
                () -> citaService.crearCita(citaRequest)
        );
    }

    // ==========================================================
    // TEST 5
    // NO DEBE CREAR CITA FUERA DE HORARIO
    // ==========================================================

    @Test
    void crearCita_debeLanzarErrorCuandoHoraFueraDeHorario() {

        citaRequest.setHoraCita(
                LocalTime.of(18, 0)
        );

        when(agendaClient.obtenerAgendaPorId(1L))
                .thenReturn(agendaResponse);

        assertThrows(
                IllegalArgumentException.class,
                () -> citaService.crearCita(citaRequest)
        );
    }

    // ==========================================================
    // TEST 6
    // NO DEBE CREAR CITA DUPLICADA
    // ==========================================================

    @Test
    void crearCita_debeLanzarErrorCuandoYaExiste() {

        when(agendaClient.obtenerAgendaPorId(1L))
                .thenReturn(agendaResponse);

        when(citaRepository.existsByFechaCitaAndHoraCita(
                citaRequest.getFechaCita(),
                citaRequest.getHoraCita()))
                .thenReturn(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> citaService.crearCita(citaRequest)
        );
    }
}