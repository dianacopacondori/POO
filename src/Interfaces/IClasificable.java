
package Interfaces;

import java.util.List;


public interface IClasificable {
    
    public void agregarEtiqueta(String idDocumento,String tag);
    
    public List<String> ObtenerEtiquetas(String idDocumento);
}
