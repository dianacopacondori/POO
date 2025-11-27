
package xml;

import Interfaces.IExportableXML;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**Clase genérica para serializar/deserializar colecciones a/desde XML.
 * @author cvDiana
 * @param <T> 
 */
public class XMLSerializer <T extends IExportableXML> {
    
    private final Class <T> type;

    public XMLSerializer(Class<T> type) {
        this.type=type;
    }
    
    public void guardarXML(List<T> items, String rutaArchivo) throws IOException{
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(rutaArchivo))){
            writer.write("<?xml version=\"1.0\" encodig=\"UTF-8\"?>\n");
            writer.write("<Catalogo>\n");
            for(T item : items){
                writer.write(" " + item.serializarXML() + "\n");
            }
            writer.write("</Catalogo>");
        }
    }
   
    // No genérico puro por deserialización dependiente del tipo → delegamos a subclases estáticas
    public List<T> cargarDesdeXML(String ruta) throws IOException {
        List<T> items = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(ruta));
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("<LibroDigital")) {
                items.add((T) Libreria.LibroDigital.deserializarXML(line));
            } else if (line.startsWith("<ArticuloCientifico")) {
                items.add((T) Libreria.ArticuloCientifico.deserializarXML(line));
            } else if (line.startsWith("<Imagen")) {
                items.add((T) com.utp.documentos.modelo.Imagen.deserializarXML(line));
            }
        }
        return items;
    }
    
}
