
package InicioSesion;

import java.util.Scanner;

public class Prueba_InicioSesion {
    
    
    public static void main(String[] args) {
        Autenticacion sistema = new Autenticacion();
        Scanner credenciales = new Scanner(System.in);
        
        int intentos = 0;
        int maximosIntentos = 3;
        
        while (intentos < maximosIntentos){
        System.out.print("Ingrese su usuario: ");
        String usuario = credenciales.nextLine();
        System.out.print("Ingrese su contrasena: ");
        String contraseña = credenciales.nextLine();

        if (sistema.loguear(usuario, contraseña)) {
            System.out.println("Acceso correcto!");
            System.out.println("***********BIENVENIDO***********");
        } else {
            intentos ++;
            System.out.println("-------------Usuario o contrasena incorrectos-------------");
        }
        

    } 
        if (intentos == maximosIntentos){
            System.out.println("Lo sentimos, ha superado el numero de intentos");
        }
    }

    

    
}

