/**
 *
 * @author borja
 */
import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private int rut; 
    private String nombre; 
    private String curso; 
    private List<RegistroAsistencia> historial;

    public Alumno(int rut, String nombre, String curso) {
        this.rut = rut; 
        this.nombre = nombre; 
        this.curso = curso;
        this.historial = new ArrayList<>();
    }
    
    public int getRut() { return rut; }
    public void setRut(int rut) { this.rut = rut; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    
    public List<RegistroAsistencia> getHistorial() { return historial; }
    
    public void agregarRegistro(RegistroAsistencia registro) throws RegistroDupException {
        for (RegistroAsistencia r : historial){
            if (r.getFecha().equals(registro.getFecha())){
                throw new RegistroDupException("Registro duplicado en la fecha " + registro.getFecha());
            }
        }
        historial.add(registro);
    }

    public double calcularPorcentajeAsistencia() {
        if (historial.isEmpty()) return 0.0;
        int diasPresente = 0, salidasTempranas = 0, diasExcluidos = 0;
        
        for (RegistroAsistencia r : historial) {
            switch(r.getEstado()){
                case EstadoAsistencia.PRESENTE: 
                    diasPresente++; 
                    break;
                case EstadoAsistencia.SALIDA_TEMPRANA: 
                    diasPresente++; 
                    salidasTempranas++; 
                    break;
                case EstadoAsistencia.INASISTENCIA_EXTRAORDINARIA: 
                    diasExcluidos++; 
                    break;
            }
        }
        int penalizacion = salidasTempranas / 3;
        int asistenciasEfectivas = diasPresente - penalizacion;
        int totalDiasEvaluables = historial.size() - diasExcluidos;
        
        if (totalDiasEvaluables <= 0) return 0.0;
        return ((double) asistenciasEfectivas / totalDiasEvaluables) * 100;
    }

    @Override
    public String toString() {
        return "RUT: " + rut + " | " + nombre + " (" + curso + ") | Asistencia: " + String.format("%.1f", calcularPorcentajeAsistencia()) + "%";
    }
}