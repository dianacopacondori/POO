
package Etiqueta;

import Interfaces.IClasificable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TagManager implements IClasificable {
    
    private Map<String, List<String>> etiquetas = new HashMap<>();

    public TagManager() {
    }
    
    @Override
    public void agregarEtiqueta(String idDocumento, String tag){
        
    };
    
    

    public List<String> buscarPorEtiqueta(String tag) {
        return null;
    }

    @Override
    public List<String> ObtenerEtiquetas(String idDocumento) {
        return null;
    }

      
    
}
