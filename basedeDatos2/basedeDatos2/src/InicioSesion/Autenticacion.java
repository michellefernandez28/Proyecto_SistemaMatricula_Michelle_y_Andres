
package InicioSesion;


import java.util.HashMap;
import java.util.Scanner;

public class Autenticacion {
    

    private HashMap<String, String> usuarios;

    public Autenticacion() {
        usuarios = new HashMap<>();
        usuarios.put("Michelle", "Mich1");
        usuarios.put("Andres", "Andre1");
        usuarios.put("Ronald", "Ronald1");
    }

    public boolean loguear(String usuario, String contraseña) {
        if (usuarios.containsKey(usuario)) {
            return usuarios.get(usuario).equals(contraseña);
        }
        return false;
    }

}


