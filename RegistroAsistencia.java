/**
 *
 * @author borja
 */
public class RegistroAsistencia {
    private Fecha fecha;
    private int estado;

    public RegistroAsistencia(Fecha fecha, int estado){
        this.fecha = fecha;
        this.estado = estado;
    }
    
    public Fecha getFecha() { return fecha; }
    public void setFecha(Fecha fecha) { this.fecha = fecha; }
    public int getEstado() { return estado; }
    public void setEstado(int estado){ this.estado = estado; }
}