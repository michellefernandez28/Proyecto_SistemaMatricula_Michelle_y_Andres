package GestordeUsuarios;

import basededatos.BasedeDatos;
import java.util.*;

public class GestordeUsuarios {
    private final Map<String, BasedeDatos> usuarios;

    public GestordeUsuarios() {
        usuarios = new HashMap<>();
    }

    public void agregarUsuario(String nombre, String apellido, String cedula) {
        if (cedula == null || cedula.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || apellido == null || apellido.trim().isEmpty()) {
            System.out.println("Error: Nombre, apellido y cedula no pueden estar vacios.");
            return;
        }
        if (usuarios.containsKey(cedula)) {
            System.out.println("Error: Ya existe un usuario con esta cedula.");
            return;
        }
        BasedeDatos nuevoUsuario = new BasedeDatos(cedula, nombre, apellido);
        usuarios.put(cedula, nuevoUsuario);
        System.out.println("Usuario registrado exitosamente.");
    }

    public BasedeDatos obtenerUsuarioPorCedula(String cedula) {
        return usuarios.get(cedula);
    }

    public boolean eliminarUsuario(String cedula) {
        if (usuarios.containsKey(cedula)) {
            usuarios.remove(cedula);
            System.out.println("Usuario eliminado correctamente.");
            return true;
        } else {
            System.out.println("Error: No se encontro un usuario con la cedula proporcionada.");
            return false;
        }
    }

    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            usuarios.values().forEach(usuario -> System.out.println(usuario));
        }
    }

    public List<BasedeDatos> buscarUsuarios(String criterio, String valor) {
        List<BasedeDatos> resultados = new ArrayList<>();
        for (BasedeDatos usuario : usuarios.values()) {
            switch (criterio.toLowerCase()) {
                case "cedula":
                    if (usuario.getCedula().equalsIgnoreCase(valor)) {
                        resultados.add(usuario);
                    }
                    break;
                case "nombre":
                    if (usuario.getNombre().equalsIgnoreCase(valor)) {
                        resultados.add(usuario);
                    }
                    break;
                case "apellido":
                    if (usuario.getApellido().equalsIgnoreCase(valor)) {
                        resultados.add(usuario);
                    }
                    break;
                default:
                    System.out.println("Criterio de busqueda no valido.");
                    return resultados;
            }
        }
        return resultados;
    }
}
