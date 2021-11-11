package cl.prueba.restfull.login.model;

public class Estado {
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Estado(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public Estado() {
    }
}
