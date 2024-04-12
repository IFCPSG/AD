package Entities;

import Entities.Direccion;

import java.util.ArrayList;

public class Persona {
    private int id;
    private String nombre;
    private String telefono;
    private String direccion;
    private ArrayList<Direccion> direcciones;

    // Constructor
    public Persona(int id, String nombre, String telefono, String direccion, ArrayList<Direccion> direcciones) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.direcciones = direcciones;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ArrayList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    // Método para agregar una dirección a la lista de direcciones
    public void agregarDireccion(Direccion nuevaDireccion) {
        this.direcciones.add(nuevaDireccion);
    }

    // Método para imprimir información de la persona
    public void mostrarInformacion() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);

        if (direcciones != null && !direcciones.isEmpty()) {
            System.out.println("Direcciones adicionales:");
            for (Direccion dir : direcciones) {
                System.out.println(" - " + dir.getDireccion());
            }
        }
    }
}