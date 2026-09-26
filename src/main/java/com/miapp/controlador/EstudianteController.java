package com.miapp.controlador;

import com.miapp.modelo.*;
import com.miapp.servicios.IBuscador;
import com.miapp.vista.EstudianteView;
import com.miapp.utilidades.*;

import java.util.ArrayList;
import java.util.List;


public class EstudianteController implements IBuscador {

    // ── Constantes finales ────────────────────────────────────────────────────
    private static final int CANTIDAD_ESTUDIANTES_INICIALES = 12;
    private static final String MENSAJE_BUSQUEDA_VACIA = "Por favor ingrese un nombre para buscar.";
    private static final String MENSAJE_BUSQUEDA_CARRERA_VACIA = "Por favor seleccione una carrera para buscar.";
    private static final String MENSAJE_BUSQUEDA_CURSO_VACIO = "Por favor seleccione un curso para buscar.";
    private static final String MENSAJE_BUSQUEDA_PROFESOR_VACIO = "Por favor seleccione un profesor.";
    private static final String MENSAJE_MATERIA_MAXIMA = "El estudiante superó el limite de materias";
    private static final String MENSAJE_CURSO_ACTUAL = "El estudiante ya se encuentra en este curso";
    private static final String MENSAJE_ESTADO_VACIO = "Por favor seleccione un estado para filtrar";
    private static final String MENSAJE_SIN_RESULTADOS = "No se encontraron estudiantes con ese estado.";

    // ── Vista ─────────────────────────────────────────────────────────────────
    private EstudianteView vista;

    // ── Array de estudiantes (fuente de datos) ────────────────────────────────
    private ArrayList<Estudiante> estudiantes;
    private Curso[] cursos;
    private ArrayList<Profesor> profesores;

    // ── Constructor ───────────────────────────────────────────────────────────

    public EstudianteController(EstudianteView vista) {
        this.vista = vista;
        // Primero cargar datos (inicializar estudiantes[])
        cargarDatos();
        // Luego asignar controlador a la vista (ahora es seguro acceder a estudiantes[])
        this.vista.setControlador(this);
    }

    // ── Implementación de la interfaz IBuscador ───────────────────────────────

    @Override
    public void cargarDatos() {
        inicializarEstudiantes();
        inicializarProfesores();
        inicializarCursos();
    }

    @Override
    public void buscarEstudiante(String criterio) {
        buscarPorCriterio(criterio);
    }

    @Override
    public void buscarEstudiantePorCarrera(String carrera) {
        buscarPorCarrera(carrera);
    }
    
    @Override
    public void buscarEstudiantePorCurso(String codigo) {
        buscarPorCurso(codigo);
    }
    
    @Override
    public void buscarCursosPorProfesor(String nombreCompleto) {
        buscarPorCursoPorProfesor(nombreCompleto);
    }

    // ── Carga de datos iniciales ──────────────────────────────────────────────

    private void inicializarEstudiantes() {
        this.estudiantes = new ArrayList();
        
        this.estudiantes.add(new Estudiante(1, "Ana ", "García", "Ingeniería de Sistemas", 4.5));
        this.estudiantes.add(new Estudiante(2, "Carlos", " López", "Ingeniería Civil", 3.8));
        this.estudiantes.add(new Estudiante(3, "María", "Rodríguez", "Medicina", 4.9));
        this.estudiantes.add(new Estudiante(4, "José ", "Martínez", "Derecho", 3.5));
        this.estudiantes.add(new Estudiante(5, "Laura ", "Sánchez", "Administración", 4.1));
        this.estudiantes.add(new Estudiante(6, "Andrés ", "Torres", "Ingeniería de Sistemas", 3.9));
        this.estudiantes.add(new Estudiante(7, "Valentina ", "Gómez", "Psicología", 4.3));
        this.estudiantes.add(new Estudiante(8, "Luis ", "Herrera", "Economía", 3.7));
        this.estudiantes.add(new Estudiante(9, "Sofía ", "Díaz", "Ingeniería Civil", 4.6));
        this.estudiantes.add(new Estudiante(10, "Juliana ", "Morales", "Medicina", 4.8));
        this.estudiantes.add(new Estudiante(11, "Ana Milena ", "Ruiz", "Derecho", 4.0));
        this.estudiantes.add(new Estudiante(12, "Carlos Andrés ", "Paz", "Administración", 3.6));

        // Log: informa cuántos estudiantes se cargaron usando static getTotalEstudiantes()
        System.out.println("Total de estudiantes cargados: " + Estudiante.getTotalEstudiantes());
    }
    
    private void inicializarProfesores() {
        this.profesores = new ArrayList();
        
        this.profesores.add(new Profesor("Juan ", "García", 1, 2100000));
        this.profesores.add(new Profesor("Fernando", "Castro", 2, 2850000));
        this.profesores.add(new Profesor("Diana", "Mendoza", 3, 3400000));
        this.profesores.add(new Profesor("Ricardo", "Vargas", 4, 2300000));
        this.profesores.add(new Profesor("Gabriela", "Paredes", 5, 3750000));

        // Log: informa cuántos estudiantes se cargaron usando static getTotalEstudiantes()
        System.out.println("Total de profesores cargados: " + Profesor.getTotalProfesores());
    }
    
    private void inicializarCursos(){
        cursos = new Curso[5];
        
        cursos[0] = new Curso("BDA150", 2);
        cursos[1] = new Curso("SIS101", 2);
        cursos[2] = new Curso("FMH500", 3);
        cursos[3] = new Curso("LMB250", 3);
        cursos[4] = new Curso("NZK325", 4);
        
        System.out.println("Total de cursos cargados: " + Curso.getTotalCursos());
    }

    // ── Lógica de búsqueda ────────────────────────────────────────────────────

  
    private void buscarPorCriterio(String criterio) {

        // Validación básica usando constante final
        if (criterio == null || criterio.isEmpty()) {
            vista.mostrarError(MENSAJE_BUSQUEDA_VACIA);
            return;
        }

        List<Estudiante> resultados = new ArrayList<>();
        String criterioBajo = criterio.toLowerCase();

        for (Estudiante e : estudiantes) {
            // Validar que el elemento no sea null
            if (e != null && (e.getNombre().toLowerCase().contains(criterioBajo) ||
                e.getApellido().toLowerCase().contains(criterioBajo))) {
                resultados.add(e);
            }
        }

        if (resultados.isEmpty()) {
            vista.mostrarEstudiantes(new ArrayList<>()); // mostrará mensaje vacío
        } else if (resultados.size() == 1) {
            // Un solo resultado: usar vista.mostrarEstudiante(fila)
            vista.mostrarEstudiante(convertirAFila(resultados.get(0)));
        } else {
            // Varios resultados: mostrar lista completa ya convertida a filas
            vista.mostrarEstudiantes(convertirAFilas(resultados));
        }
    }

   
    private void buscarPorCarrera(String carrera) {
        // Validación básica usando constante final
        if (carrera == null || carrera.isEmpty() || carrera.equals("Seleccionar...")) {
            vista.mostrarError(MENSAJE_BUSQUEDA_CARRERA_VACIA);
            return;
        }

        List<Estudiante> resultados = new ArrayList<>();

        // Búsqueda exacta por carrera
        for (Estudiante e : estudiantes) {
            // Validar que el elemento no sea null
            if (e != null && e.getCarrera().equalsIgnoreCase(carrera)) {
                resultados.add(e);
            }
        }

        // Mostrar resultados (ya convertidos a filas, no como Estudiante)
        vista.mostrarEstudiantes(convertirAFilas(resultados));
    }
    
    private void buscarPorCurso(String codigo) {
        // Validación básica usando constante final
        if (codigo == null || codigo.isEmpty() || codigo.equals("Seleccionar...")) {
            vista.mostrarError(MENSAJE_BUSQUEDA_CURSO_VACIO);
            return;
        }

          Curso cursoEncontrado = null;
          for (Curso c : cursos) {
              if (c != null && c.getCodigo().equalsIgnoreCase(codigo.trim())) {
                  cursoEncontrado = c;
                  break;
              }
          }
          
          if (cursoEncontrado == null) {
             vista.mostrarError("El objeto curso "+ codigo + " parece no existir [ERROR]");
              return;
          }

          ArrayList<Estudiante> resultados = cursoEncontrado.getEstudiantesMatriculados();
          
          if (resultados == null || resultados.isEmpty()) {
            vista.mostrarError("No hay estudiantes matriculados en este curso.");
            return;
         }

          vista.mostrarEstudiantes(convertirAFilas(resultados));
    }
    
    private void buscarPorCursoPorProfesor(String nombreApellido) {
        if (nombreApellido == null || nombreApellido.trim().isEmpty() || nombreApellido.equals("Seleccionar...")) {
            vista.mostrarError(MENSAJE_BUSQUEDA_PROFESOR_VACIO);
            return;
        }

        Profesor profesorEncontrado = null;
        for (Profesor p : profesores) {
            if (p != null) {
                String nombreCompleto = p.getNombre().trim() + " " + p.getApellido().trim();
                if (nombreCompleto.equalsIgnoreCase(nombreApellido.trim())) {
                    profesorEncontrado = p;
                    break;
                }
            }
        }

        if (profesorEncontrado == null) {
            vista.mostrarError("El profesor " + nombreApellido + " no fue encontrado.");
            return;
        }

        ArrayList<Curso> cursosDictados = profesorEncontrado.getCursos();

        if (cursosDictados == null || cursosDictados.isEmpty()) {
            vista.mostrarError("El profesor no tiene cursos asignados.");
            return;
        }

        vista.mostrarCursosPorProfesor(convertirAFilasCursos(cursosDictados));
}

    
    private Object[] convertirAFila(Estudiante e) {
        return new Object[]{
            e.getId(),
            e.getNombre(),
            e.getApellido(),
            e.getCarrera(),
            String.format("%.2f", e.getPromedio())
        };
    }

  
        private List<Object[]> convertirAFilas(List<Estudiante> lista) {
        List<Object[]> filas = new ArrayList<>();
        for (Estudiante e : lista) {
            filas.add(convertirAFila(e));
        }
        return filas;
    }
        
    private Object[] convertirAFilaCurso(Curso c) {
        return new Object[]{
            c.getCodigo(),
            c.getCreditos(),
            c.getEstudiantesMatriculados().size()
        };
    }

  
        private List<Object[]> convertirAFilasCursos(List<Curso> lista) {
        List<Object[]> filas = new ArrayList<>();
        for (Curso c : lista) {
            filas.add(convertirAFilaCurso(c));
        }
        return filas;
    }    

    public Estudiante obtenerEstudiantePorId(int id) {
        for (Estudiante e : estudiantes) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public String[] obtenerCarrerasUnicas() {
        List<String> carreras = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            // Validar que el elemento no sea null
            if (e != null) {
                String carrera = e.getCarrera();
                if (!carreras.contains(carrera)) {
                    carreras.add(carrera);
                }
            }
        }
        return carreras.toArray(new String[0]);
    }
    
    public String[] obtenerCursos(){
     List<String> listaCursos = new ArrayList<>();
        for (Curso c : cursos) {
             listaCursos.add(c.getCodigo());
        }
        return listaCursos.toArray(new String[0]);
    }
    
    public String[] obtenerProfesores(){
     List<String> listaCursos = new ArrayList<>();
        for (Profesor p : profesores) {
             listaCursos.add(p.getNombre().trim() + " " + p.getApellido().trim());
        }
        return listaCursos.toArray(new String[0]);
    }
    
    
    public final int obtenerTotalEstudiantes() {
        return Estudiante.getTotalEstudiantes();
    }
    
    public final int obtenerTotalProfesores() {
        return Profesor.getTotalProfesores();
    }

   
    public boolean agregarEstudiante(String nombre, String apellido, String carrera, double promedio) {
        // Validación de datos
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() ||
            carrera.equals("Seleccionar...")) { /// No funciona "carrera == null", ya que seleccionar cuenta como opc valida.
            vista.mostrarError("Todos los campos son obligatorios.");
            return false;
        }

        for(Estudiante e: estudiantes){
            if (e.getNombre().trim().equalsIgnoreCase(nombre.trim()) && e.getApellido().trim().equalsIgnoreCase(apellido.trim())){
                vista.mostrarError("Ya existe un estudiante con este nombre y apellido.");
                return false;
            }
        }
        
        // Crear nuevo estudiante con ID automático basado en el contador static
        int proximoId = Estudiante.getProximoId();
        Estudiante nuevoEstudiante = new Estudiante(proximoId, nombre, apellido, carrera, promedio);
        estudiantes.add(nuevoEstudiante);
        
        // Mostrar mensaje de éxito
        vista.mostrarMensaje("Estudiante agregado correctamente.\nTotal de estudiantes: " +
                            Estudiante.getTotalEstudiantes());

        return true;
    }
    
    public boolean agregarProfesor(String nombre, String apellido, double salarioBase) {
        // Validación de datos
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty()) { /// No funciona "carrera == null", ya que seleccionar cuenta como opc valida.
            vista.mostrarError("Todos los campos son obligatorios.");
            return false;
        }

        for(Profesor p: profesores){
            if (p.getNombre().trim().equalsIgnoreCase(nombre.trim()) && p.getApellido().trim().equalsIgnoreCase(apellido.trim())){
                vista.mostrarError("Ya existe un profesor con este nombre y apellido.");
                return false;
            }
        }
        
        int proximoId = Profesor.getProximoId();
        Profesor nuevoProfesor = new Profesor(nombre, apellido, proximoId, salarioBase);
        profesores.add(nuevoProfesor);
        
        // Mostrar mensaje de éxito
        vista.mostrarMensaje("Profesor agregado correctamente.\nTotal de Profesores: " +
                            Profesor.getTotalProfesores());

        return true;
    }
    
    public boolean asignarCursoAlProfesor(String nombreApellido, String codigo) {
        // Validación de datos
        if (nombreApellido == null || nombreApellido.trim().isEmpty() || nombreApellido.equals("Seleccionar...")) {
            vista.mostrarError(MENSAJE_BUSQUEDA_PROFESOR_VACIO);
            return false;
        }
        
        if (codigo == null || codigo.trim().isEmpty() || codigo.equals("Seleccionar...")) {
            vista.mostrarError(MENSAJE_BUSQUEDA_CURSO_VACIO);
            return false;
        }

        Profesor profesorEncontrado = null;
        for (Profesor p : profesores) {
            if (p != null) {
                String nombreCompleto = p.getNombre().trim() + " " + p.getApellido().trim();
                if (nombreCompleto.equalsIgnoreCase(nombreApellido.trim())) {
                    profesorEncontrado = p;
                    break;
                }
            }
        }

        if (profesorEncontrado == null) {
            vista.mostrarError("El profesor " + nombreApellido + " no fue encontrado.");
            return false;
        }
        

        Curso cursoEncontrado = null;
        for (Curso c : cursos) {
            if (c != null && c.getCodigo().equalsIgnoreCase(codigo.trim())) {
                cursoEncontrado = c;
                break;
            }
        }

        if (cursoEncontrado == null) {
            vista.mostrarError("El curso con código " + codigo + " no fue encontrado.");
            return false;
        }

        for (Curso c : profesorEncontrado.getCursos()) {
            if (c != null && c.getCodigo().equalsIgnoreCase(cursoEncontrado.getCodigo())) {
                vista.mostrarError("El profesor ya tiene asignado el curso "+ codigo);
                return false;
            }
        }

        profesorEncontrado.impartirClase(cursoEncontrado);
        vista.mostrarMensaje("Se le asignó el curso "+ codigo + " al profesor " + nombreApellido);
        return true;
    }
    
    public void inscribirEstudianteCurso(int idEstudiante, String codigoCurso){
        System.out.println("Recibido 1");
        if (codigoCurso == null || codigoCurso.trim().isEmpty() || codigoCurso.equals("Seleccionar...")) {
            vista.mostrarError(MENSAJE_BUSQUEDA_CURSO_VACIO);
            return;
    }
        System.out.println("Recibido 2");
        Estudiante estudianteEncontrado = null;
        for (Estudiante e : estudiantes) {
            if (e != null && e.getId() == idEstudiante) {
                estudianteEncontrado = e;
                break;
            }
        }
        System.out.println("Recibido 3");
        Curso cursoEncontrado = null;
        for (Curso c : cursos) {
        if (c != null && c.getCodigo().equalsIgnoreCase(codigoCurso.trim())) {
            cursoEncontrado = c;
            break;
        }
       }
        
        System.out.println("Recibido 4");
        if (estudianteEncontrado == null) {
            vista.mostrarError("El estudiante con ID " + idEstudiante + " no fue encontrado. [ERROR]");
            return;
        }
        
        System.out.println("Recibido 5");
        if (cursoEncontrado == null) {
        vista.mostrarError("El curso " + codigoCurso + " no fue encontrado. [ERROR]");
        return;
        }
        
        System.out.println("Recibido 6");
        if(estudianteEncontrado.getCursosMatriculados().size() >= Estudiante.MAX_MATERIAS)
        {
            vista.mostrarError(MENSAJE_MATERIA_MAXIMA);
            return;
        }


        /* 
        Para evitar volver a hacer una validación de codigoCurso con arrayList cursos de estudiante
        se verifica desde el mismo curso previamente indexeado y validado teniendo en cuenta que se
        mantendrá la relación biodireccional N:M entre Curso y Estudiante
        */
        System.out.println("Recibido 7");
        for(Estudiante e: cursoEncontrado.getEstudiantesMatriculados()){
            if (e.getId() == estudianteEncontrado.getId())
            {
               vista.mostrarError(MENSAJE_CURSO_ACTUAL);
               return;
            }
        }
        System.out.println("Recibido 8");
        estudianteEncontrado.inscribir(cursoEncontrado);
        cursoEncontrado.addEstudiantesMatriculados(estudianteEncontrado);
        vista.mostrarMensaje("Se matriculó al estudiante "+estudianteEncontrado.getNombre().trim() + " " + estudianteEncontrado.getApellido().trim()+" en el curso "+cursoEncontrado.getCodigo());
    }
    
    public void buscarEstudiantesPorEstado(String estado){
        if (estado == null || estado.trim().isEmpty() || estado.equals("Seleccionar...")) {
        vista.mostrarError(MENSAJE_ESTADO_VACIO);
        return;
        }
        
        ArrayList<Estudiante> estudiantesEncontrados = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            if (e != null && e.getEstadoMatricula() == EstadoMatricula.valueOf(estado.trim().toUpperCase())) {
                estudiantesEncontrados.add(e);
            }
        }
        
        if(estudiantesEncontrados.isEmpty()){
            vista.mostrarError(MENSAJE_SIN_RESULTADOS);
            return;
        }
        
        vista.mostrarEstudiantes(convertirAFilas(estudiantesEncontrados));
    }
}