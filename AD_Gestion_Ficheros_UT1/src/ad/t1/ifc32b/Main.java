package ad.t1.ifc32b;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando programa...");
        File directorio = new File("ficheros");
        if (!directorio.exists() && !directorio.mkdir()) {
            System.err.println("No se ha podido crear el directorio");
            System.exit(-1);
        }

        File fichero = new File(directorio, "fichero.txt");

        try {
            if (fichero.createNewFile()) {
                System.out.println("Fichero creado correctamente");

            } else {
                System.out.println("El fichero ya existe.");
            }
        } catch (IOException ex) {
            System.err.println("No se ha podido crear el fichero");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }

        File renombre = new File(directorio, "fichero_copia.txt");
        fichero.renameTo(renombre);

        try {
            FileUtils.copyFile(renombre, fichero);

        }catch(IOException ex){
            System.err.println("No se ha podido copiar el fichero "+renombre.getName()+", al fichero: "+fichero.getName() );
        }
        try {
            FileUtils.moveFile(fichero, new File("fichero.txt"));
        }catch (IOException ex){
            System.err.println("No se ha podido mover el fichero: "+fichero.getName());
        }

     /*   if (fichero.delete()) {
            System.out.println("fichero eliminado correctamente");
        }
        else{
            System.err.println("No se ha podido eliminar el fichero");
        }*/
        System.out.println("Finalizando programa...");
    }
}
