package operaciones_ficheros_streams;

    import java.io.*;

    public class BinaryFileWriter {
        public static void main(String[] args) {
            String fileName = "archivo.bin";

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
                // Escribir datos binarios en el archivo
                dos.writeInt(42);
                dos.writeDouble(3.14);
                dos.writeUTF("¡Hola, mundo!");

                System.out.println("Datos escritos correctamente en " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

