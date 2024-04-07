package uvg.edu.gt;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static VectorHeap<Paciente> colaDeEmergenciaVectorHeap = new VectorHeap<>();
    private static PriorityQueue<Paciente> colaDeEmergenciaPriorityQueue = new PriorityQueue<>();
    public static void main(String[] args) {
        boolean menu = true;
        cargarTxt("pacientes.txt");
        while (menu){
            System.out.println("Bienvenido al sistema de atención de emergencias");
            System.out.println("1. Ingresar paciente");
            System.out.println("2. Atender paciente");
            System.out.println("3. Guardar pacientes en archivo");
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
                    guardarPacientesTxt();
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

    private static void guardarPacientesTxt() {
        try {
            FileWriter writer = new FileWriter("pacientes.txt", false); // false para sobrescribir el archivo
    
            // Suponiendo que quieras guardar los pacientes de la cola PriorityQueue
            for(Paciente paciente : colaDeEmergenciaPriorityQueue) {
                writer.write(paciente.getNombre() + ", " + paciente.getSintoma() + ", " + paciente.getCodigoEmergencia() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
    
}
