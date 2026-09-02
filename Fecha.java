/**
 *
 * @author borja
 */
import java.util.ArrayList;
import java.util.HasMap;
import java.util.List;
import java.util.Map;

public class Fecha {
    private int dia;   
    private int mes;

    public Fecha(int dia, int mes){
        this.dia = dia;
        this.mes = mes; 
    }

    public int getDia() {return dia;}
    public void setDia(int dia) {this.dia = dia;}

    public int getMes() {return mes;}
    public void setMes(int mes) {this.mes = mes;}

    @Override
    public boolean equals(object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fecha fecha = (Fecha) obj;
        return dia == fecha.dia && mes == fecha.mes;
    }
    
    @Override
    public String toString(){
        return dia + "/" + mes;
    }
}
