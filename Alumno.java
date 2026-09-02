/**
 *
 * @author borja
 */
import java.util.ArrayList;
import java.util.HasMap;
import java.util.List;
import java.util.Map;

public class Alumno {
    private String nombre; 
    private String rut; 
    private String curso; //En palabras (ej: Cuarto Basico A)
    private List<RegistroAsistencia> historial;

    public Alumno(String rut, String nombre, String curso) {
        this.rut = rut;
        this.nombre = nombre;
        this.curso = curso;
        this.historial = new ArrayList<>();
    }
    
    public String getRut() {return rut;}
    public void setRut(String rut) {this.rut = rut;}
    
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCurso() {return curso;}
    public void setCurso(String curso) {this.curso = curso;}

    public List <RegistroAsistencia> getHistorial() {return historial;}
    public void setHistorial(List <registroAsistencia> historial) {this.historial = historial;}
    
    public boolean agregarRegistro(RegistroAsistencia registro){
        for (int i = 0; i < historial.size(); i++){
            RegistroAsistencia r = historial.get(i);
            if (r.getFecha().equals(registro.getFecha())){
                return false;
            }
        }
        historial.add(registro);
        return true;
    }

    public double calcularPorcentajeAsistencia() {
        if (historial.isEmpty()) return 0.0;
    
        int diasPresente = 0;
        int salidasTemprandas = 0;
        int diasExcluidos = 0;
        
        for (int i = 0; i < historial.size(); i ++) {
            RegistroAsistencia r = historial.get(i);
            switch(r.getEstado()){
                case EstadoAsistencia.PRESENTE:
                    diasPresente ++;
                    break;
                case EstadoAsistencia.SALIDA_TEMPRANA:
                    diasPresente ++;
                    salidasTempranas ++;
                    break;
                case EstadoAsistencia.INASISTENCIA_EXTRAORDINARIA:
                    diasExcluidos++;
                    break;
                case EstadoAsistencia.INASISTENCIA_REGULAR:
                    break;
            }
        }
        
        int penalizacion = salidasTempranas / 3;
        int asistenciasEfectivas = diasPresente - penalizacion;
        
        int totalDiasEvaluables = historial.size() - diasExcluidos;
        
        if (totalDiasEvaluables == 0) return 0.0;
        return ((double) asistenciasEfectivas / totalDiasEvaluables) * 100;
    }
}