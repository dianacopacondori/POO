
package xml;

import Interfaces.IExportableXML;
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
    
    private List <T> elementos;

    public XMLSerializer() {
    }

   
    public XMLSerializer(Class<T> type) {
        this.elementos= new ArrayList<>();
    }

    public List<T> getElementos() {
        return new ArrayList<>(elementos);
    }

    public void setElementos(List<T> elementos) {
        this.elementos = new ArrayList<>(elementos);
    }
    
    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<catalogo>\n");
        for (T elem : elementos) {
            sb.append("  ").append(elem.serializarXML().replaceAll("\n", "\n  ")).append("\n");
        }
        sb.append("</catalogo>");
        return sb.toString();
    }
    
    public void guardarArchivo(String ruta) throws IOException {
        Files.writeString(Paths.get(ruta), serializar());
    }
    
     public void cargarArchivo(String ruta) throws IOException {
        String xml = Files.readString(Paths.get(ruta));
        System.out.println("XML cargado desde: " + ruta);
    }

}
