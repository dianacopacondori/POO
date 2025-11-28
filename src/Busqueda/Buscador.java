
package Busqueda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author romin
 */
public class Buscador { //Romina
        //Metodo generico que busca dentro de la coleccion
        //El método recorre todos los elementos de la colección y compara cada uno  con la clave de búsqueda usando el método equals.
        public static <T, K> List<T> buscar(Collection<T> coleccion, K claveBusqueda) { 
        List<T> resultados = new ArrayList<>();

        for (T elemento : coleccion) {
            if (elemento.equals(claveBusqueda)) {
                resultados.add(elemento);
            }
        }

        return resultados;
    }
}
