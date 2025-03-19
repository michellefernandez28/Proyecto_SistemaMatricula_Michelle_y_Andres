package basededatos;

public class BasedeDatos {
    private String cedula;
    private String nombre;
    private String apellido;

    public BasedeDatos(String cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    @Override
    public String toString() {
        return "Cedula: " + cedula + ", Nombre: " + nombre + " " + apellido;
    }
}
