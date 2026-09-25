/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miapp.modelo;
import java.util.ArrayList;
import com.miapp.servicios.*;

/**
 *
 * @author santi
 */
public final class Profesor extends Persona implements IBuscador{
    
    private final double salarioBase;
    private static int totalProfesores = 0;
    private ArrayList<Curso> cursos;
    
    public Profesor(String nombre, String apellido, int id, double salarioBase) {
        super(nombre, apellido, id);
        this.salarioBase = salarioBase;
        totalProfesores++;
        this.cursos = new ArrayList<>();
    }
    
    public static int getProximoId() {  
        return totalProfesores + 1;
    
    }
    
    public ArrayList<Curso> getCursos()
    {
       return cursos;
    }
    
    public void addCursos(Curso c){
        cursos.add(c);
    }
          
    public void removeCursos(Curso c){
        cursos.remove(c);
    }
    
    
    @Override
    public double calcularPago(){
        return salarioBase;
    }
    
     public static int getTotalProfesores() {
        return totalProfesores;
    }
    
    public void impartirClase(Curso c)
    {
        System.out.println("El profesor "+ this.getNombre() + " esta dando la clase del curso " + c.getCodigo());
        addCursos(c);
    }

    @Override
    public void buscarEstudiante(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void buscarEstudiantePorCarrera(String carrera) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cargarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void buscarEstudiantePorCurso(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void buscarCursosPorProfesor(String nombreCompleto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
