
package Etiqueta;

import Interfaces.IClasificable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;

import java.util.ArrayList;


public class TagManager implements IClasificable {
    
    private Map<String, List<String>> etiquetas = new HashMap<>();

    public TagManager() {
    }
    
    @Override
    public void agregarEtiqueta(String idDocumento, String tag){
        
        if (!etiquetas.containsKey(idDocumento)) {
            etiquetas.put(idDocumento, new ArrayList<>());
        }

        List<String> lista = etiquetas.get(idDocumento);

        if (!lista.contains(tag)) {
            lista.add(tag);
        }
    };
    
    

    public List<String> buscarPorEtiqueta(String tag) {
        
        List<String> resultado = new ArrayList<>();
        
        for (Map.Entry<String, List<String>> entry : etiquetas.entrySet()){
            
            if (entry.getValue().contains(tag)) {
                resultado.add(entry.getKey());
            }
        }


        return resultado;
    }

    @Override
    public List<String> ObtenerEtiquetas(String idDocumento) {
        
            if (etiquetas.containsKey(idDocumento)) {
                return etiquetas.get(idDocumento);   
                    
            } else {
                
                return new ArrayList<>();          
            }

    }

      
    
}
