package Entities;

public class Direccion {
    private int id;
    private int idPersona;
    private String direccion;

    public Direccion(int id, int idPersona, String direccion) {
        this.id = id;
        this.idPersona = idPersona;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
