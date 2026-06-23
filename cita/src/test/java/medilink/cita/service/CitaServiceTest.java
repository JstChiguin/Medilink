package medilink.cita.service;

import medilink.cita.client.AgendaClient;
import medilink.cita.dto.request.CitaRequest;
import medilink.cita.dto.response.CitaResponse;
import medilink.cita.mapper.CitaMapper;
import medilink.cita.model.entity.Cita;
import medilink.cita.model.enums.EstadoCita;
import medilink.cita.model.enums.ModalidadCita;
import medilink.cita.repository.CitaRepository;

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


// HABILITA MOCKITO PARA LOS TESTS
@ExtendWith(MockitoExtension.class)
class CitaServiceTest {


    // MOCKS (DEPENDENCIAS SIMULADAS)
    @Mock
    private CitaRepository citaRepository;

    @Mock
    private CitaMapper citaMapper;

    @Mock
    private AgendaClient agendaClient;


    // SERVICIO REAL A PROBAR
    @InjectMocks
    private CitaService citaService;


    // OBJETOS DE APOYO
    private Cita cita;
    private CitaRequest citaRequest;
    private CitaResponse citaResponse;


    // SE EJECUTA ANTES DE CADA TEST
    // CREA DATOS DE PRUEBA
    @BeforeEach
    void setUp() {

        // ENTIDAD CITA
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


        // REQUEST DE ENTRADA
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


        // RESPONSE ESPERADA
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
    }

    // TEST 1 - OBTENER TODAS LAS CITAS
    @Test
    void obtenerTodasLasCitas_debeRetornarListaDeCitas() {

        // Simula lo que devuelve el repositorio
        when(citaRepository.findAll())
                .thenReturn(List.of(cita));

        // Simula el mapper
        when(citaMapper.toResponseList(List.of(cita)))
                .thenReturn(List.of(citaResponse));

        // Ejecuta método real
        List<CitaResponse> resultado =
                citaService.obtenerTodasLasCitas();

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getIdCita());

        verify(citaRepository, times(1)).findAll();
    }

    // TEST 2 - CREAR CITA CORRECTAMENTE
    @Test
    void crearCita_debeGuardarYRetornarCita() {

        // No existe otra cita en la misma fecha y hora
        when(citaRepository.existsByFechaCitaAndHoraCita(
                citaRequest.getFechaCita(),
                citaRequest.getHoraCita()))
                .thenReturn(false);

        // Conversión Request -> Entity
        when(citaMapper.toEntity(citaRequest))
                .thenReturn(cita);

        // Simula guardado en BD
        when(citaRepository.save(cita))
                .thenReturn(cita);

        // Conversión Entity -> Response
        when(citaMapper.toResponse(cita))
                .thenReturn(citaResponse);

        // Ejecuta método real
        CitaResponse resultado =
                citaService.crearCita(citaRequest);

        // Validaciones
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCita());
        assertEquals(
                EstadoCita.CONFIRMADA,
                resultado.getEstadoCita()
        );

        verify(citaRepository, times(1)).save(cita);
    }

    // TEST 3 - NO PERMITE AGENDAR DOMINGO
    @Test
    void crearCita_debeLanzarErrorCuandoEsDomingo() {

        // Domingo
        citaRequest.setFechaCita(
                LocalDate.of(2026, 6, 21)
        );

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> citaService.crearCita(citaRequest)
                );

        assertEquals(
                "No se pueden agendar citas los domingos",
                exception.getMessage()
        );

        verify(citaRepository, never()).save(any());
    }

    // TEST 4 - NO PERMITE HORAS FUERA DEL HORARIO LABORAL
    @Test
    void crearCita_debeLanzarErrorCuandoHoraFueraDeHorario() {

        // Hora inválida
        citaRequest.setHoraCita(
                LocalTime.of(18, 0)
        );

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> citaService.crearCita(citaRequest)
                );

        assertEquals(
                "La cita debe estar dentro del horario de atención (08:00 a 17:00)",
                exception.getMessage()
        );

        verify(citaRepository, never()).save(any());
    }

    // TEST 5 = NO PERMITE CITAS DUPLICADAS
    @Test
    void crearCita_debeLanzarErrorCuandoYaExiste() {

        // Simula que ya existe una cita
        when(citaRepository.existsByFechaCitaAndHoraCita(
                citaRequest.getFechaCita(),
                citaRequest.getHoraCita()))
                .thenReturn(true);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> citaService.crearCita(citaRequest)
                );

        assertEquals(
                "Ya existe una cita agendada en esa fecha y hora",
                exception.getMessage()
        );

        verify(citaRepository, never()).save(any());
    }
}