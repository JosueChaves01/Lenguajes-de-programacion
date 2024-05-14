public class EventoEmpresarial extends Evento {
    private String sede;

    public EventoEmpresarial(String n, String u, String f,String sede) {
        super(n,u,f);
        this.sede = sede;
    }

    public String getTipo() {
        return "Evento Empresarial";
    }

    public String getInfo() {
        return super.getInfo() + ", Sede: " + sede;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    @Override
    public String toString() {
        return "EventoEmpresarial{" +
                "sede='" + sede + '\'' +
                "} " + super.toString();
    }

}