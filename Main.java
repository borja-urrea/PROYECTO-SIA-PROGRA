import javax.swing.JOptionPane;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaAsistencia sistema = new SistemaAsistencia();
        
        GestorCSV.cargarDatosAlumnos(sistema);

        String[] opciones = {"Consola", "Ventana (GUI)"};
        int seleccion = JOptionPane.showOptionDialog(null, 
            "¿Cómo desea ejecutar el sistema?", "Inicio - Gestión Asistencia",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
            null, opciones, opciones[0]);

        if (seleccion == 0) {
            iniciarConsola(sistema);
        } else if (seleccion == 1) {
            iniciarVentana(sistema);
        }

        GestorCSV.guardarDatos(sistema);
        System.out.println("Datos guardados en alumnos.csv. Sistema cerrado correctamente.");
    }

    private static void iniciarConsola(SistemaAsistencia sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        
        do {
            System.out.println("\n--- MENU CONSOLA ---");
            System.out.println("1. Listar Alumnos\n2. Agregar Alumno\n3. Ver Asistencia de Alumno\n4. Marcar Asistencia Diaria\n5. Salir");
            System.out.print("Elija una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                continue; 
            }

            try {
                if (opcion == 1) {
                    if (sistema.getAlumnos().isEmpty()) {
                        System.out.println("No hay alumnos registrados.");
                    } else {
                        for(Alumno a : sistema.getAlumnos().values()) {
                            System.out.println(a.toString());
                        }
                    }
                } else if (opcion == 2) {
                    System.out.print("RUT (solo números): "); 
                    int r = Integer.parseInt(sc.nextLine());
                    System.out.print("Nombre: "); String n = sc.nextLine();
                    System.out.print("Curso: "); String c = sc.nextLine();
                    sistema.agregarAlumno(new Alumno(r, n, c));
                    System.out.println("Alumno agregado exitosamente.");
                } else if (opcion == 3) {
                    System.out.print("RUT del Alumno (solo números): "); 
                    int r = Integer.parseInt(sc.nextLine());
                    Alumno al = sistema.buscarAlumno(r);
                    System.out.println(al.toString());
                    for(RegistroAsistencia reg : al.getHistorial()) {
                        System.out.println("Fecha: " + reg.getFecha() + " | Estado: " + reg.getEstado());
                    }
                } else if (opcion == 4) {
                    System.out.print("RUT (solo números): "); 
                    int r = Integer.parseInt(sc.nextLine());
                    
                    System.out.print("Día (número): ");
                    int d = Integer.parseInt(sc.nextLine()); 
                    System.out.print("Mes (número): ");
                    int m = Integer.parseInt(sc.nextLine());
                    System.out.print("Estado (1:Pres, 2:Falta, 3:FaltaExt, 4:SalTemp): ");
                    int est = Integer.parseInt(sc.nextLine());
                    
                    sistema.marcarAsistencia(r, d, m, est);
                    System.out.println("Asistencia registrada con éxito.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error de ingreso: Ha ingresado texto donde se esperaba un número.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } while (opcion != 5);
    }

    private static void iniciarVentana(SistemaAsistencia sistema) {
        String[] menu = {"Listar Alumnos", "Agregar Alumno", "Ver Ficha", "Marcar Asistencia", "Salir"};
        int opcion = 0;
        
        do {
            opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menu, menu[0]);
            
            try {
                if (opcion == 0) {
                    if (sistema.getAlumnos().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay alumnos registrados.");
                    } else {
                        StringBuilder sb = new StringBuilder("ALUMNOS:\n");
                        for(Alumno a : sistema.getAlumnos().values()) sb.append(a.toString()).append("\n");
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                } else if (opcion == 1) {
                    String rutStr = JOptionPane.showInputDialog("RUT (solo números):");
                    if(rutStr == null) continue;
                    int rut = Integer.parseInt(rutStr); 
                    
                    String nom = JOptionPane.showInputDialog("Nombre:");
                    String cur = JOptionPane.showInputDialog("Curso:");
                    sistema.agregarAlumno(new Alumno(rut, nom, cur));
                    JOptionPane.showMessageDialog(null, "Agregado correctamente.");
                    
                } else if (opcion == 3) {
                    String rutStr = JOptionPane.showInputDialog("RUT del Alumno (solo números):");
                    if(rutStr == null) continue;
                    int rut = Integer.parseInt(rutStr);
                    
                    String diaStr = JOptionPane.showInputDialog("Día (número):");
                    int dia = Integer.parseInt(diaStr); 
                    
                    String mesStr = JOptionPane.showInputDialog("Mes (número):");
                    int mes = Integer.parseInt(mesStr);
                    
                    String estStr = JOptionPane.showInputDialog("Estado (1:Pres, 2:Falta, 3:FaltaExt, 4:SalTemp):");
                    int estado = Integer.parseInt(estStr);
                    
                    sistema.marcarAsistencia(rut, dia, mes, estado);
                    JOptionPane.showMessageDialog(null, "Asistencia registrada.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Debe ingresar valores numéricos válidos (RUT, Día, Mes o Estado).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
            }
        } while (opcion != 4 && opcion != -1); 
    }
}