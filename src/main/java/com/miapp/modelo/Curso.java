/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miapp.modelo;
import java.util.ArrayList;

/**
 *
 * @author santi
 */
public class Curso {
    private static int totalCursos = 0;
    private String codigo;
    private int creditos;
    private ArrayList<Estudiante> estudiantesMatriculados;
    private Profesor profesor;

    public Curso(String codigo, int creditos) {
        this.codigo = codigo;
        this.creditos = creditos;
        this.estudiantesMatriculados = new ArrayList<>();
        totalCursos++;
    }
    
    public ArrayList<Estudiante> getEstudiantesMatriculados()
    {
       return estudiantesMatriculados;
    }
    
    public void addEstudiantesMatriculados(Estudiante e){
        estudiantesMatriculados.add(e);
    }
          
    public void removeEstudiantesMatriculados(Estudiante e){
        estudiantesMatriculados.remove(e);
    }

    public static int getTotalCursos() {
        return totalCursos;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
    @Override
    public String toString() {
        return "Curso{" + "codigo=" + codigo + ", creditos=" + creditos + ", estudiantesMatriculados=" + estudiantesMatriculados + '}';
    }
    
    
}
