package FicherosXMLEjercicio1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Crear algunas recetas de ejemplo
        Receta receta1 = new Receta("Tarta de Manzana", "Deliciosa tarta de manzana", "Manzanas, harina, azúcar, canela");
        Receta receta2 = new Receta("Ensalada César", "Clásica ensalada césar", "Lechuga, pollo, parmesano, aderezo césar");

        List<Receta> recetas = new ArrayList<>();
        recetas.add(receta1);
        recetas.add(receta2);

        // Exportar recetas a XML
        String rutaArchivo = "recetas.xml";
        ExportadorXML.exportarRecetasAXML(recetas, rutaArchivo);
    }
}
