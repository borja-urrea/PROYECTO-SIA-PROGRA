/**
 *
 * @author borja
 */
import java.util.ArrayList;
import java.util.HasMap;
import java.util.List;
import java.util.Map;

public class SistemaAsistencia {
    private Map <String, Alumno> alumnos;
    
    public SistemaAsistencia(){
        this.alumnos = new HashMap<>();
    }
    
    //Metodos de lectura
    
    public Map<String, Alumno> getAlumno() {return alumnos;}
    public void setAlumnos(Map<String, Alumno> alumnos) {this.alumnos = alumnos;}

    public void registrarAlumno(Alumno alumno) {
        alumnos.put(alumno.getRut(), alumno)
    }
    
    public void marcarAsistencia(String rut, Fecha fecha, int estado){
        Alumno alumno = alumnos.get(rut);
        if(alumno != null){
            boolean exito = alumno.agregarRegistro(new RegistroAsistencia(fecha, estado));
            if(!exito) {
                System.out.println("Error: Ya existe un registro para el alumno el día " + fecha);
            }
        } else {
            System.out.println("Error: Alumno no encontrado");
        }
    }

    public void justificarFalta(String rut, Fecha fecha){
        Alumno alumno = alumnos.get(rut);
        if(alumno != null){
            for(RegistroAsistencia r : alumno.getHistorial()) {
                if(r.getFecha().equals(fecha) && r.getEstado() == EstadoAsistencia.INASISTENCIA_REGULAR){
                    r.setEstado(EstadoAsistencia.INASISTENCIA_EXTRAORDINARIA);
                    System.out.println("Falta del " + fecha + "justificada con exito.");
                    return;
                }
            }
            System.out.println("No se encontro inasistencia regular en esa fecha para ser justificada.");
        }
    }

    public void mostrarFichaAlumno(String rut) {
        Alumno alumno = alumnos.get(rut);
        if (alumno != null){
            System.out.println("\n--- Ficha de " + alumno.getNombre() + " (RUT: " + alumno.getRut() + " ) ---");
            for(RegistroAsistencia r : alumno.getHistorial()){
                String nombreEstado = "";
                switch(r.getEstado()) {
                    case 1: nombreEstado = "Presente"; break;
                    case 2: nombreEstado = "Falta Regular"; break;
                    case 3: nombreEstado = "Falta Justificada (Extraordinaria)"; break; 
                    case 4: nombreEstado = "Salida Temprana"; break; 
                }
                System.out.println("Fecha: " + r.getFecha() + " | Estado: " + nombreEstado + " (" + r.getEstado() + ")");
            }
            System.out.println("Porcentaje de Asistencia Real: %.2f%%\n", alumno.calcularPorcentajeAsistencia());
        }
    }
}
