import java.io.*;

public class GestorCSV {
    
    public static void cargarDatosAlumnos(SistemaAsistencia sistema) {
        try (BufferedReader br = new BufferedReader(new FileReader("alumnos.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if(datos.length == 3) {
                    int rut = Integer.parseInt(datos[0]); 
                    sistema.agregarAlumno(new Alumno(rut, datos[1], datos[2]));
                }
            }
        } catch (Exception e) { 
        }
    }

    public static void guardarDatos(SistemaAsistencia sistema) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("alumnos.csv"))) {
            for (Alumno al : sistema.getAlumnos().values()) {
                pw.println(al.getRut() + "," + al.getNombre() + "," + al.getCurso());
            }
        } catch (IOException e) {
            System.out.println("Error guardando alumnos: " + e.getMessage());
        }
    }
}