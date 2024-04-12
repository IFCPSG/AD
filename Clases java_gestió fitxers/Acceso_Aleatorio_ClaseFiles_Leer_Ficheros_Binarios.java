package ad.t1.ifc32b.javanio.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class BynaryFiles {
    public static void main(String[] args) {
        Path path = Path.of("ficheros/fichero_binario");
        escribir(path);
        leer(path);
    }

    private static void escribir(Path path) {
        Aula aula = new Aula(1 , "Acces a dades" , 99);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  //CREAMOS UN OBJETO PARA ESCRIBIR DATOS EN LA RAM
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream); //CREAMOS UN OBJETO PARA ESCRIBIR OBJETOS EN EL LUGAR INDICADO
            //EN ESTE CASO ESCRIBIMOS EN EL BYTEARRAYOUTPUTSTREAM --> RAM
        } catch (IOException ex) {
            System.err.println("Error al crear el object outputStream");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            objectOutputStream.writeObject(aula);
        } catch (IOException ex) {
            System.err.println("Error al escribir en la memoria RAM");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
        try {
            Files.write(path , byteArrayOutputStream.toByteArray());
        } catch (IOException ex) {
            System.err.println("Error al escribir en el fichero");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }
        System.out.println("Fichero escrito correctamente");
    }
    private static void leer(Path path) {
        byte[] bytesFichero = new byte[]{};
        try {
            bytesFichero = Files.readAllBytes(path); //LEEMOS FICHERO
        } catch (IOException ex) {
            System.err.println("Error a leer los bytes del fichero:" + path.toString());
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytesFichero); //GUARDAMOS EN LA RAM
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException ex) {
            System.err.println("Error al crear el objectOuputStream:");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
        try {
            Aula aula = (Aula) objectInputStream.readObject();
            System.out.println(aula.toString());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error al leer el objeto de la memoria RAM y convertirlo en Aula");
            System.exit(-3);
        }

    }
}
