package uvg.edu.gt;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VectorHeapTest {

    @Test
    public void testAddAndRemove() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        Paciente paciente1 = new Paciente("Juan Perez", "Dolor de cabeza", 'C');
        Paciente paciente2 = new Paciente("Maria Lopez", "Fractura de pierna", 'A');
        Paciente paciente3 = new Paciente("Luis Gomez", "Apendicitis", 'B');

        heap.add(paciente1);
        heap.add(paciente2);
        heap.add(paciente3);

        assertEquals("El paciente con mayor prioridad (A) debería ser atendido primero", paciente2, heap.remove());
        assertEquals("El siguiente paciente con mayor prioridad (B) debería ser atendido después", paciente3, heap.remove());
        assertEquals("El último paciente con menor prioridad (C) debería ser atendido al final", paciente1, heap.remove());
    }
    
}

