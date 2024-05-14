public abstract class Evento extends Elemento{
    private String nombre;
    private String ubicacion;
    private String fecha;

    public Evento(String n, String u, String f) {
        nombre = n;
        ubicacion = u;
        fecha = f;
    }

    public abstract String getTipo();

    public String getInfo() {
        return "Nombre: " + nombre + ", Ubicación: " + ubicacion + ", Fecha: " + fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", fecha='" + fecha + '\'' +
                "} " + super.toString();
    }
}
