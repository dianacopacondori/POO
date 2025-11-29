
package Busqueda;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author romin
 */
public class Buscador { //Romina
        //Metodo generico que busca dentro de la coleccion
        //El método recorre todos los elementos de la colección y compara cada uno  con la clave de búsqueda usando el método equals.
        public static <T, K> List<T> buscar(Collection<T> coleccion, K claveBusqueda,
                                         java.util.function.Predicate<T> criterio) {
        return coleccion.stream()
                .filter(criterio)
                .collect(Collectors.toList());
    }

}
