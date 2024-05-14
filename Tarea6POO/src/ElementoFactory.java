public interface ElementoFactory {
    Elemento crearEvento(String n, String u, String f);
    Elemento crearEventoFam(String n, String u, String f, boolean regalo);
    Elemento crearEventoEmp(String n, String u, String f, String s);
    Elemento crearContacto(Persona per, String t,String c);
    Elemento crearContactoEmp(Persona per, String t,String c, String e, String p);
    Elemento crearContactoFam(Persona per, String t,String c, String p, String u);



}
