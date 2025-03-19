package SistemadeInscripcion;

import basededatos.BasedeDatos;
import GestordeUsuarios.GestordeUsuarios;
import java.util.*;
import InicioSesion.Autenticacion;

public class SistemadeInscripcion {
    private static final List<String> CURSOS_VALIDOS = Arrays.asList("Futbol = Lunes 6pm", "Costura = Sabado 10am", "Guitarra = Sabado 3pm", "Manualidades = Martes 2pm", "Muay Thai = Sabado 7pm");
    private final Map<String, Set<String>> cursos;
    private static final Autenticacion autenticacion = new Autenticacion();
    private final GestordeUsuarios gestorUsuarios;
    

    public SistemadeInscripcion(GestordeUsuarios gestorUsuarios) {
        this.gestorUsuarios = gestorUsuarios;
        cursos = new HashMap<>();
        for (String curso : CURSOS_VALIDOS) {
            cursos.put(curso, new HashSet<>());
        }
    }
    
    public void iniciarSesion() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("**** Iniciar Sesión ****");
        System.out.print("Ingrese su usuario: ");
        String usuario = entrada.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = entrada.nextLine();
        if (autenticacion.loguear(usuario, contraseña)) {
            System.out.println("Acceso correcto!");
            System.out.println("***********BIENVENIDO***********");
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
            System.exit(0);
        }
    }

    public void matricularUsuario(int cursoIndex, String cedula) {
        if (cursoIndex < 1 || cursoIndex > CURSOS_VALIDOS.size()) {
            System.out.println("Error: Opcion de curso no valida.");
            return;
        }
        String curso = CURSOS_VALIDOS.get(cursoIndex - 1);
        BasedeDatos usuario = gestorUsuarios.obtenerUsuarioPorCedula(cedula);
        if (usuario == null) {
            System.out.println("Error: Usuario no registrado.");
            return;
        }
        if (cursos.get(curso).contains(cedula)) {
            System.out.println("El usuario " + usuario.getNombre() + " ya esta matriculado en " + curso);
        } else {
            cursos.get(curso).add(cedula);
            System.out.println("Usuario " + usuario.getNombre() + " matriculado en " + curso);
        }
    }

    public int contarInscritos(int cursoIndex) {
        if (cursoIndex < 1 || cursoIndex > CURSOS_VALIDOS.size()) {
            System.out.println("Error: Opcion de curso no valida.");
            return 0;
        }
        String curso = CURSOS_VALIDOS.get(cursoIndex - 1);
        return cursos.getOrDefault(curso, new HashSet<>()).size();
    }

    public void eliminarUsuario(String cedula) {
        if (gestorUsuarios.eliminarUsuario(cedula)) {
            cursos.values().forEach(inscritos -> inscritos.remove(cedula));
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("Error: No se encontro un usuario con la cedula proporcionada.");
        }
    }

    public void mostrarUsuariosRegistrados() {
        gestorUsuarios.mostrarUsuarios();
    }

    public void buscarUsuarios() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el valor de busqueda:");
        System.out.println("1. Cedula");
        System.out.println("2. Nombre");
        System.out.println("3. Apellido");
        System.out.print("Opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        String criterio = "";
        switch (opcion) {
            case 1:
                criterio = "cedula";
                break;
            case 2:
                criterio = "nombre";
                break;
            case 3:
                criterio = "apellido";
                break;
            default:
                System.out.println("Opcion invalida.");
                return;
        }

        System.out.print("Ingrese el valor de busqueda: ");
        String valor = scanner.nextLine();
        List<BasedeDatos> resultados = gestorUsuarios.buscarUsuarios(criterio, valor);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron usuarios.");
        } else {
            System.out.println("Usuarios encontrados:");
            for (BasedeDatos usuario : resultados) {
                System.out.println(usuario);
            }
        }
    }

    public static void main(String[] args) {
        GestordeUsuarios gestorUsuarios = new GestordeUsuarios();
        SistemadeInscripcion sistema = new SistemadeInscripcion(gestorUsuarios);
        
        
        
        
        Scanner entrada = new Scanner(System.in);
        String opcion;

        
        do {
            System.out.println("\nSeleccione una opcion:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Matricular usuario en curso");
            System.out.println("3. Contar inscritos en un curso");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Mostrar lista de usuarios registrados");
            System.out.println("6. Buscar usuario");
            System.out.println("7. Salir");
            System.out.print("Opcion: ");
            opcion = entrada.nextLine().trim();

            switch (opcion) {
                case "1":
                    System.out.print("Ingrese el nombre del usuario: ");
                    String nombre = entrada.nextLine();
                    System.out.print("Ingrese el apellido del usuario: ");
                    String apellido = entrada.nextLine();
                    System.out.print("Ingrese la cedula del usuario: ");
                    String cedula = entrada.nextLine();
                    gestorUsuarios.agregarUsuario(nombre, apellido, cedula);
                    break;
                case "2":
                case "3":
                    System.out.println("Seleccione el curso:");
                    for (int i = 0; i < CURSOS_VALIDOS.size(); i++) {
                        System.out.println((i + 1) + ". " + CURSOS_VALIDOS.get(i));
                    }
                    System.out.print("Opcion: ");
                    int cursoIndex = entrada.nextInt();
                    entrada.nextLine();
                    if (opcion.equals("2")) {
                        System.out.print("Ingrese la cedula del usuario: ");
                        cedula = entrada.nextLine();
                        sistema.matricularUsuario(cursoIndex, cedula);
                    } else {
                        int inscritos = sistema.contarInscritos(cursoIndex);
                        System.out.println("Numero de inscritos en " + CURSOS_VALIDOS.get(cursoIndex - 1) + ": " + inscritos);
                    }
                    break;
                case "4":
                    System.out.print("Ingrese la cedula del usuario a eliminar: ");
                    cedula = entrada.nextLine();
                    sistema.eliminarUsuario(cedula);
                    break;
                case "5":
                    sistema.mostrarUsuariosRegistrados();
                    break;
                case "6":
                    sistema.buscarUsuarios();
                    break;
                case "7":
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Error: Opcion no valida. Intente nuevamente.");
            }
        } while (!opcion.equals("7"));
        entrada.close();
    }
}