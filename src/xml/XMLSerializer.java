
package xml;

import java.util.ArrayList;
import java.util.List;


public class XMLSerializer <T> {
    
    private List<T> elementos;

    public XMLSerializer(List<T> elementos) {
        this.elementos = new ArrayList<>();
    }
    
    
    public String serializar(List<T> lista){
        return null;
    }
    
    public List<T> deserializar(String xml) {
        return null;
    }
    
    
    public void guardarArchivo(String ruta) {
    }

    public List<T> cargarArchivo(String ruta) {
        return null;
    }
    
}
