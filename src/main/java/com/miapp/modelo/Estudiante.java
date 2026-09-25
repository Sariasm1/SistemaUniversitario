    package com.miapp.modelo;
import java.util.ArrayList;

/**
 * Modelo: representa la entidad Estudiante.
 */
public final class Estudiante extends Persona {  

    private static int totalEstudiantes = 0;
    public static final int PROMEDIO_MINIMO = 0;
    public static final int PROMEDIO_MAXIMO = 5;
    public static final String CARRERA_PREDETERMINADA = "Sin especificar";
    public static final int MAX_MATERIAS = 5;

    // ── Atributos de instancia ────────────────────────────────────────────────
    private String carrera;
    private double promedio;
    private String estadoMatricula;
    private ArrayList<Curso> cursosMatriculados;

    // ── Constructor ───────────────────────────────────────────────────────────

    public Estudiante(int id, String nombre, String apellido, String carrera, double promedio) {
        super(nombre, apellido, id);
        this.carrera  = carrera;
   
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
    
    public String getEstadoMatricula(){
        return estadoMatricula;
    }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setCarrera(String carrera) { 
        this.carrera = carrera; 
    }
    
    public void setEstadoMatricula(String estadoMatricula){
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