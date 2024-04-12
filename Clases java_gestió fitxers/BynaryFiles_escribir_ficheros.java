import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BynaryFiles {
    public static void main(String[] args) {
        Path path = Path.of("fichero_binario");
        escribir(path);
    }

    private static void escribir(Path path) {

        Aula aula = new Aula(1 , "Acceso a datos" , 99);
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
    }

}