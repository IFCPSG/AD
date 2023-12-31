package ad.t1.ifc32b.ficheros_binarios;

import Persona.Persona;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //CREAR EL FICHERO DONDE GUARDAREMOS EL OBJETO
        File file = new File("ficheros/objeto");
        guardarObjeto(file);
        recuperarObject(file);

    }
    
    private static void guardarObjeto(File file) {
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.err.println("No se ha podido crear el fichero: " + file.getName());
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        //CREAR EL OBJETO QUE DESEAMOS GUARDAR
        //CREAR EL FileOutputStream para guardar información en el fichero --> flujo de datos de salida
        //Convertir el objeto en bytes
        //Guardar los bytes en el fichero

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el id de la persona");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el nombre de la persona");
        String nombre = scanner.nextLine();
        System.out.println("Introduce la edad de la persona");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el direccion de la persona");
        String direccion = scanner.nextLine();
        Persona persona = new Persona(id , nombre , edad , direccion);


        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            System.err.println("No se ha encontrado el fichero: " + file.getName());
            System.err.println(ex.getMessage());
            System.exit(-2);
        }

        ObjectOutputStream objectOutputStream = null;
        try {
            //Utiliza el fileOutputStream para saber donde ecribir
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (IOException ex) {
            System.err.println("N se ha podido crear el ObjectOutputStream");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }
        try {
            //Escribimos el objeto en el fichero ligado a FileOutputStream
            objectOutputStream.writeObject(persona);
        } catch (IOException ex) {
            System.err.println("No se ha podido escribir en el directorio: " + file.getName());
            System.exit(-4);
        }

        try {
            //Cerramos los OutputStreams
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException ex) {
            System.err.println("No se ha podido crear correctamente el fichero: " + file.getName());
            System.err.println(ex.getMessage());
            System.exit(-5);
        }
    }

    private static void recuperarObject(File file) {
        FileInputStream fileInputStream = null;
        try {
            //De donde debe leer
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            System.err.println("Error al abrir el FileInputStream");
            System.err.println(ex.getMessage());
            System.exit(-6);
        }
        ObjectInputStream objectInputStream = null;
        try {
            //Utiliza el FileInputStream para saber de donde leer
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException ex) {
            System.err.println("Error al abrir el objectInputStream");
            System.err.println(ex.getMessage());
            System.exit(-7);
        }
        try {
            //Lee el fichero y lo convierte en una persona
            Persona persona = (Persona) objectInputStream.readObject();
            System.out.println(persona);
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error al recuperar el objeto del fichero");
            System.err.println(ex.getMessage());
            System.exit(-8);

        }

        try {
            //Cerramos los InputStream
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException ex) {
            System.err.println("Error al cerrar los inputStream");
            System.err.println(ex.getMessage());
            System.exit(-9);
        }

    }

}