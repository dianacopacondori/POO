
package Interfaces;

import java.util.List;

//@auto Romina

public interface IClasificable {
    
    public void agregarEtiqueta(String idDocumento,String tag);
    // Metodo para agregar etiquetas, segun codigo (idDocumento)
    
    public List<String> ObtenerEtiquetas(String idDocumento);
    // metodo para obtener las etiquetas, segun el idDocumento,
}
