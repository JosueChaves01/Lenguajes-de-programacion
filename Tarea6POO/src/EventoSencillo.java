public class EventoSencillo extends Evento {
    public EventoSencillo(String nombre, String ubicacion, String fecha) {
        super(nombre, ubicacion, fecha);
    }

    public String getTipo() {
        return "Evento Sencillo";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
