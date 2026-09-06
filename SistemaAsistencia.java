/**
 *
 * @author borja
 */
import java.util.*;

public class SistemaAsistencia {
    private Map<Integer, Alumno> alumnos;
    
    public SistemaAsistencia(){
        this.alumnos = new HashMap<>();
    }
    
    public Map<Integer, Alumno> getAlumnos() { return alumnos; }
    
    public void agregarAlumno(Alumno alumno) {
        alumnos.put(alumno.getRut(), alumno);
    }

    public Alumno buscarAlumno(int rut) throws AlumnoNoEncontradoExceptions {
        if(!alumnos.containsKey(rut)) {
            throw new AlumnoNoEncontradoExceptions("No se encontró el RUT: " + rut);
        }
        return alumnos.get(rut);
    }

    public void marcarAsistencia(int rut, Fecha fecha, int estado) throws AlumnoNoEncontradoExceptions, RegistroDupException {
        Alumno alumno = buscarAlumno(rut);
        alumno.agregarRegistro(new RegistroAsistencia(fecha, estado));
    }
    
    public void marcarAsistencia(int rut, int dia, int mes, int estado) throws AlumnoNoEncontradoExceptions, RegistroDupException {
        marcarAsistencia(rut, new Fecha(dia, mes), estado);
    }

    public List<Alumno> obtenerAlumnosEnRiesgo(double porcentajeMinimo) {
        List<Alumno> enRiesgo = new ArrayList<>();
        for(Alumno al : alumnos.values()) {
            if(!al.getHistorial().isEmpty() && al.calcularPorcentajeAsistencia() < porcentajeMinimo) {
                enRiesgo.add(al);
            }
        }
        return enRiesgo;
    }
    
}