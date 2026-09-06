public class Main {
    public static void main(String[] args) {
        SistemaAsistencia sistema = new SistemaAsistencia();
        
        System.out.println("--- INICIANDO PRUEBAS DEL SISTEMA ---");
        Alumno alumno1 = new Alumno("12345678-9", "Juan Perez", "Cuarto Basico A");
        Alumno alumno2 = new Alumno("98765432-1", "Maria Gomez", "Cuarto Basico B");
        sistema.registrarAlumno(alumno1);
        sistema.registrarAlumno(alumno2);
        System.out.println("Alumnos registrados con éxito.");
        Fecha fecha1 = new Fecha(15, 4);
        Fecha fecha2 = new Fecha(16, 4);

        sistema.marcarAsistencia("12345678-9", fecha1, EstadoAsistencia.PRESENTE);
        sistema.marcarAsistencia("12345678-9", fecha2, EstadoAsistencia.SALIDA_TEMPRANA);

        sistema.marcarAsistencia("98765432-1", fecha1, EstadoAsistencia.INASISTENCIA_REGULAR);

        System.out.println("\n--- JUSTIFICANDO FALTA DE MARIA ---");
        sistema.justificarFalta("98765432-1", fecha1);

        sistema.mostrarFichaAlumno("12345678-9");
        sistema.mostrarFichaAlumno("98765432-1");
    }
}
