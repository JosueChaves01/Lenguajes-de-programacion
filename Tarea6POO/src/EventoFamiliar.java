import java.util.ArrayList;
import java.util.List;

public class EventoFamiliar extends Evento {
    private boolean regalo;

    public EventoFamiliar(String n, String u, String f, boolean regalo) {
        super(n,u,f);
        this.regalo = regalo;
    }

    public String getTipo() {
        return "Evento Familiar";
    }

    public String getInfo() {
        return super.getInfo() + ", Regalo: " + regalo;
    }

    public boolean isRegalo() {
        return regalo;
    }

    public void setRegalo(boolean regalo) {
        this.regalo = regalo;
    }

    @Override
    public String toString() {
        return "EventoFamiliar{" +
                "regalo=" + regalo +
                "} " + super.toString();
    }
}
