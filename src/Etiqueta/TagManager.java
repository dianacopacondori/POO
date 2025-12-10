
package Etiqueta;

import Interfaces.IClasificable;
import Libreria.DocumentoDigital;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;

import java.util.ArrayList;


public class TagManager implements IClasificable { //TODO ROMINA
    
    private Map<String, List<String>> etiquetas = new HashMap<>();  //colección de Map, con String para idDocumento, y una lista para las etiquetas que se guardan

    public TagManager() { //constructor  vacio
    }
    
    @Override
    public void agregarEtiqueta(String idDocumento, String tag){ //Este método se encarga de agregar las etiquetas, revisando el idDocumento.
        
        if (!etiquetas.containsKey(idDocumento)) {
            etiquetas.put(idDocumento, new ArrayList<>());
        }

        List<String> lista = etiquetas.get(idDocumento);

        if (!lista.contains(tag)) {
            lista.add(tag);
        }
    };
    
    

    public List<String> buscarPorEtiqueta(String tag) { //Este método buscar según la etiqueta tag por toda la colección
        
        List<String> resultado = new ArrayList<>();
        
        for (Map.Entry<String, List<String>> entry : etiquetas.entrySet()){
            
            if (entry.getValue().contains(tag)) {
                resultado.add(entry.getKey());
            }
        }


        return resultado;
    }

    @Override
    public List<String> ObtenerEtiquetas(String idDocumento) { //Método encargado de buscar en la lista según el idDocumentos, que etiquetas estan relacionada
        
            if (etiquetas.containsKey(idDocumento)) {
                return etiquetas.get(idDocumento);   
                    
            } else {
                
                return new ArrayList<>();          
            }

    }
    // asignar etiqueta a un documento (usando su IClasificable)
    public void clasificarDocumento(IClasificable doc, String tag) {
        if (doc instanceof DocumentoDigital d) {
            d.agregarEtiqueta(tag);
            agregarEtiqueta(d.getID(), tag);
        }
    }

      
    
}
