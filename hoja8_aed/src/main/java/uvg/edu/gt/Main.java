package uvg.edu.gt;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase principal que simula un sistema de atención de emergencias en un hospital.
 * Permite la gestión de pacientes mediante una cola de prioridad, ofreciendo funcionalidades
 * para agregar, atender y listar pacientes en espera, basándose en su prioridad de atención.
 * Nicolás Concuá
 * 23197
 * Hoja de trabajo 8
 */
public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static VectorHeap<Paciente> colaDeEmergenciaVectorHeap = new VectorHeap<>();
    private static PriorityQueue<Paciente> colaDeEmergenciaPriorityQueue = new PriorityQueue<>();

    /**
     * Método principal que ejecuta el menú del sistema de atención de emergencias.
     * @param args Argumentos de línea de comando (no utilizados).
     */

    public static void main(String[] args) {
        boolean menu = true;
        cargarTxt("pacientes.txt");
        while (menu){
            System.out.println("Bienvenido al sistema de atención de emergencias");
            System.out.println("1. Ingresar paciente");
            System.out.println("2. Atender paciente");
            System.out.println("3. Ver pacientes en espera");
            System.out.println("4. Salir");
            System.out.println("Ingrese la opción deseada: ");
            int opcion = sc.nextInt();
            
            switch (opcion){
                case 1:
                    agregarPaciente();
                    break;
                case 2:
                    atenderPaciente();
                    break;
                case 3:
                    System.out.println("Pacientes en espera: " + colaDeEmergenciaVectorHeap);
                    System.out.println("Pacientes en espera: " + colaDeEmergenciaPriorityQueue);
                    break;
                case 4:
                    menu = false;
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    /**
     * Método para agregar un nuevo paciente al sistema. Solicita al usuario ingresar los datos
     * del paciente y lo añade a la cola de emergencia.
     */
    private static void agregarPaciente() {
        System.out.print("Ingrese nombre del paciente: ");
        sc.nextLine(); 
        String nombre = sc.nextLine();
        System.out.print("Ingrese síntoma: ");
        String sintoma = sc.nextLine();
        System.out.print("Ingrese código de emergencia (A-E): ");
        char codigo = sc.next().charAt(0);
        sc.nextLine(); // Consumir línea restante
    
        Paciente paciente = new Paciente(nombre, sintoma, codigo);
        // Aquí puedes elegir a cuál cola añadirlo o añadirlo a ambas si así lo prefieres
        colaDeEmergenciaVectorHeap.add(paciente);
        colaDeEmergenciaPriorityQueue.add(paciente);
        
        // Ahora, añadir el paciente al archivo pacientes.txt
        try {
            FileWriter writer = new FileWriter("pacientes.txt", true); // Segundo argumento true para append
            writer.write(nombre + ", " + sintoma + ", " + codigo + "\n");
            writer.close();
            System.out.println("Paciente agregado correctamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }

    /**
     * Método que permite atender al paciente con mayor prioridad según el método de cola seleccionado.
     * Presenta al usuario las opciones entre usar VectorHeap o PriorityQueue y procede a atender
     * al paciente correspondiente.
     */
    private static void atenderPaciente() {
        System.out.println("¿Con qué método desea trabajar?");
        System.out.println("1. VectorHeap");
        System.out.println("2. PriorityQueue (Java Collection)");
        System.out.print("Opción: ");
        int metodo = sc.nextInt();
        sc.nextLine(); // Consumir línea restante

        switch (metodo) {
            case 1:
                if (!colaDeEmergenciaVectorHeap.isEmpty()) {
                    Paciente paciente = colaDeEmergenciaVectorHeap.remove();
                    System.out.println("Atendiendo a (VectorHeap): " + paciente);
                } else {
                    System.out.println("No hay pacientes en espera (VectorHeap).");
                }
                break;
            case 2:
                if (!colaDeEmergenciaPriorityQueue.isEmpty()) {
                    Paciente paciente = colaDeEmergenciaPriorityQueue.poll();
                    System.out.println("Atendiendo a (PriorityQueue): " + paciente);
                } else {
                    System.out.println("No hay pacientes en espera (PriorityQueue).");
                }
                break;
            default:
                System.out.println("Opción no válida. Por favor, intente de nuevo.");
        }
}

    /**
     * Carga los pacientes desde un archivo de texto a las colas de emergencia.
     * Lee el archivo especificado y añade cada paciente encontrado a las colas de prioridad.
     * @param rutaArchivo Ruta al archivo de texto que contiene los datos de los pacientes.
     */
    private static void cargarTxt(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        try {
            Scanner scannerArchivo = new Scanner(archivo);
            while (scannerArchivo.hasNextLine()) {
                String linea = scannerArchivo.nextLine();
                String[] datos = linea.split(", ");
                if (datos.length == 3) {
                    String nombre = datos[0];
                    String sintoma = datos[1];
                    char codigo = datos[2].charAt(0);
                    Paciente paciente = new Paciente(nombre, sintoma, codigo);
                    colaDeEmergenciaVectorHeap.add(paciente);
                    colaDeEmergenciaPriorityQueue.add(paciente);
                }
            }
            scannerArchivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + rutaArchivo);
        }
    }
    
}
