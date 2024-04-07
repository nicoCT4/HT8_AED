package uvg.edu.gt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PacienteTest {

    @Test
    public void pacientesConMayorPrioridadSonComparadosCorrectamente() {
        Paciente pacienteConAltaPrioridad = new Paciente("Juan Perez", "Apendicitis", 'A');
        Paciente pacienteConBajaPrioridad = new Paciente("Ana Gomez", "Gripe", 'D');

        assertTrue(pacienteConAltaPrioridad.compareTo(pacienteConBajaPrioridad) < 0,
                "El paciente con código de emergencia 'A' debe tener mayor prioridad que el de código 'D'");
    }

    @Test
    public void pacientesConIgualPrioridadSonComparadosComoIguales() {
        Paciente paciente1 = new Paciente("Carlos Mora", "Infarto", 'A');
        Paciente paciente2 = new Paciente("Luisa Paez", "Paro Cardiaco", 'A');

        assertTrue(paciente1.compareTo(paciente2) == 0,
                "Dos pacientes con el mismo código de emergencia deben ser considerados de igual prioridad");
    }

    // Puedes añadir más pruebas para cubrir otros casos, como asegurarte de que el orden inverso también funcione correctamente.
}

