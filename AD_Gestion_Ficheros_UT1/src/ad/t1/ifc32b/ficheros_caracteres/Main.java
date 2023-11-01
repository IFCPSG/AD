package ad.t1.ifc32b.ficheros_caracteres;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("ficheros/caracteres.txt");
        escribir(file);
        leer(file);

    }

    private static void escribir(File file) {

        //1 FileWriter
        //2 Escribir
        //3 Cerrar
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file); //INICIALIZAMOS
        }catch (IOException ex){
            System.err.println("Error: Error con el FileWriter");
            System.err.println(ex.getMessage());
            System.exit(-1);

        }
        try {
            fileWriter.write("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc a commodo nibh, vel ornare est. Integer eget lacus ornare, accumsan velit et, commodo tortor. Morbi sollicitudin efficitur sapien eget aliquet. Duis viverra sem at dui dapibus pulvinar. Integer non lacinia urna. Sed ornare pretium ante eget pulvinar. Nullam gravida id metus at ornare.\n" +
                    "\n" +
                    "Proin interdum lectus velit, eget egestas ipsum tincidunt non. Curabitur ullamcorper sed leo id posuere. In nulla dolor, bibendum quis aliquam at, sollicitudin vitae purus. Cras pulvinar tortor augue, ac sodales libero vulputate ac. Aenean dictum lectus ultrices diam suscipit commodo. Donec consectetur neque lectus, sed facilisis orci vehicula ut. Aliquam erat volutpat. Aliquam vehicula tempor diam, eget vulputate nunc tristique in. Etiam nec iaculis ante. Nunc commodo in risus a porta. Morbi lacinia varius massa et porta. Sed enim leo, vehicula eget justo a, dignissim vestibulum lectus.\n" +
                    "\n" +
                    "Etiam nec nunc at sem vulputate malesuada vel non sapien. Quisque ac iaculis velit. Nam rhoncus arcu enim, quis commodo erat malesuada id. Vivamus sit amet finibus sapien, in pretium urna. Ut tincidunt vel leo eget porttitor. Nullam diam mi, suscipit a laoreet a, commodo et ante. In congue tempus ex ut efficitur. Ut ultrices, ante et fermentum scelerisque, dolor tellus rhoncus leo, vitae facilisis felis tortor id enim. Vivamus auctor consequat dui, eget porttitor sem ornare non.\n" +
                    "\n" +
                    "In hac habitasse platea dictumst. Maecenas pulvinar pretium magna. Pellentesque eu consectetur elit. Nunc sollicitudin et enim at vehicula. Proin posuere tristique tortor pharetra suscipit. Pellentesque tristique neque arcu, mattis auctor libero vehicula sit amet. Sed et felis et mauris auctor aliquam et quis libero. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce et risus diam. Nam dignissim lectus non neque facilisis, vitae maximus mi sollicitudin.\n" +
                    "\n" +
                    "Suspendisse potenti. Proin eu justo eget justo suscipit aliquam vel sit amet velit. Aenean urna risus, ornare at nisi in, maximus dignissim libero. Nullam id massa malesuada, aliquet neque sed, rutrum dui. Nulla nec lacinia neque. Suspendisse dapibus eleifend ornare. Sed blandit nisi sem, eget aliquet est laoreet at. Morbi sagittis dictum bibendum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Mauris ac finibus tortor. Pellentesque eget nulla efficitur, feugiat dui sit amet, venenatis arcu. Maecenas auctor risus finibus vulputate efficitur.\n" +
                    "\n" +
                    "Nullam vitae ornare nisi. Phasellus vulputate augue at justo lacinia, eu condimentum nisl vehicula. Vivamus ac lectus eu elit tincidunt placerat a ut nisl. Curabitur ultricies nisl ut nibh luctus condimentum. Duis lobortis libero tempus dui pulvinar, eu accumsan lectus aliquet. Donec ullamcorper dui elit, quis tempus ligula scelerisque eu. Etiam est lorem, vehicula sed consectetur eu, semper vitae diam. Aenean id dui erat. Integer ipsum metus, pellentesque suscipit sem eu, elementum tincidunt tortor. Sed quis tortor maximus, porta justo non, mollis nulla. Mauris ipsum metus, pulvinar eu massa eu, pharetra pellentesque magna. Integer sollicitudin diam ex, at congue mauris aliquet eget. In lobortis turpis quis sagittis scelerisque. Sed accumsan ultricies consequat. Curabitur ex diam, placerat sit amet lorem non, fringilla imperdiet urna. Nunc vel ligula condimentum, mollis.");
        }catch (IOException ex){
            System.err.println("Error: Error con el FileWriter");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }

        try {
            fileWriter.close();
        }
        catch (IOException ex){
            System.err.println("Error: Error con el FileWriter");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }

    }

    private static void leer(File file){
        FileReader fileReader = null;
        try {
            fileReader  = new FileReader(file);
        }catch (FileNotFoundException ex){
            System.err.println("No se ha podido crear el FileReader");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            int read;
            while ((read= fileReader.read()) != -1) {
                System.out.print((char)read);
            }
            System.out.println();
        }catch (IOException ex){
            System.err.println("Error al leer desde el FileReader");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
        try {
            fileReader.close();
        }catch (IOException ex){
            System.err.println("Error al cerrar el fileReader");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }

    }
}
