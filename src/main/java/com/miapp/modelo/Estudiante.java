package com.miapp.modelo;
import java.util.ArrayList;
import com.miapp.servicios.*;
import com.miapp.utilidades.*;


/**
 * Modelo: representa la entidad Estudiante.
 */
public final class Estudiante extends Persona implements Inscribible {  

    private static int totalEstudiantes = 0;
    private static final int PROMEDIO_MINIMO = 0;
    private static final int PROMEDIO_MAXIMO = 5;
    public static final int MAX_MATERIAS = 5;

    // ── Atributos de instancia ────────────────────────────────────────────────
    private String carrera;
    private double promedio;
    private EstadoMatricula estadoMatricula;
    private ArrayList<Curso> cursosMatriculados;

    // ── Constructor ───────────────────────────────────────────────────────────

    public Estudiante(int id, String nombre, String apellido, String carrera, double promedio) {
        super(nombre, apellido, id);
        this.carrera  = carrera;
        this.estadoMatricula = EstadoMatricula.ACTIVO;
        cursosMatriculados = new ArrayList<>();
   
        if (promedio >= PROMEDIO_MINIMO && promedio <= PROMEDIO_MAXIMO) {
            this.promedio = promedio;
        } else {
            this.promedio = 0.0;  // Por defecto si está fuera de rango
        }
        
        // nuevo: Incrementa el contador estático de estudiantes
        totalEstudiantes++;
    }

    // ── Métodos estáticos (de clase) ──────────────────────────────────────────

    public static int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    public static void reiniciarContador() {
        totalEstudiantes = 0;
    }

    public static int getProximoId() {  
        return totalEstudiantes + 1;
    
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getCarrera() { 
        return carrera; 
    }

    public double getPromedio() { 
        return promedio; 
    }
    
    public EstadoMatricula getEstadoMatricula(){
        return estadoMatricula;
    }
    
    public ArrayList<Curso> getCursosMatriculados(){
        return cursosMatriculados;
    }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setCarrera(String carrera) { 
        this.carrera = carrera; 
    }
    
    public void setEstadoMatricula(EstadoMatricula estadoMatricula){
        this.estadoMatricula = estadoMatricula;
    }

    /**
     Valida el promedio antes de asignarlo usando constantes finales
     * @param p promedio a validar (debe estar entre PROMEDIO_MINIMO y PROMEDIO_MAXIMO)
     */
    public void setPromedio(double p) {
        // nuevo: Uso de constantes finales para validación
        if (p >= PROMEDIO_MINIMO && p <= PROMEDIO_MAXIMO) {
            this.promedio = p;
        }
    }
    
    @Override
    public boolean inscribir(Curso curso) {
        this.cursosMatriculados.add(curso);
        return true;
    }
    
    @Override
    public double calcularPago(){
        return 0;
    }
    
    /**
     Método final: no puede ser sobrescrito por subclases
     */
    @Override
    public final String toString() {
        return "ID: " + id
             + " | Nombre: " + this.getNombre()
             + " | Apellido: " + this.getApellido()
             + " | Carrera: " + carrera
             + " | Promedio: " + String.format("%.2f", promedio);
    }
}