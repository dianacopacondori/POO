
package Catalogo;

import Busqueda.Buscador;
import Etiqueta.TagManager;
import Libreria.DocumentoDigital;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import xml.XMLSerializer;

/**
 *
 * @author Mhia
 */
public class CatalogoManager {


    private List<DocumentoDigital> inventario = new ArrayList<>();
    private Map<String, DocumentoDigital> mapaBusqueda = new HashMap<>(); //id -> documento
    private XMLSerializer<DocumentoDigital> serializer = new XMLSerializer<>();
    private TagManager tagManager = new TagManager();

    private final String rutaXML = "documentos.xml";

    public CatalogoManager() {}

    // -------------------------------------------
    // AGREGAR DOCUMENTO
    // -------------------------------------------
    public void agregarDocumento(DocumentoDigital d) {
        inventario.add(d);
        mapaBusqueda.put(d.getID(), d);
    }

    // -------------------------------------------
    // ELIMINAR DOCUMENTO
    // -------------------------------------------
    public boolean eliminarDocumento(String id) {
        DocumentoDigital d = mapaBusqueda.remove(id);

        if (d != null) {
            inventario.remove(d);
            return true;
        }
        return false;
    }

    // -------------------------------------------
    // BÚSQUEDAS
    // -------------------------------------------
    public List<DocumentoDigital> buscarPorTitulo(String titulo) {
        return Buscador.buscar(inventario, titulo,
                doc -> doc.getTitulo().toLowerCase().contains(titulo.toLowerCase()));
    }

    public List<DocumentoDigital> buscarPorAutor(String autor) {
        return Buscador.buscar(inventario, autor,
                doc -> doc.getAutor().toLowerCase().contains(autor.toLowerCase()));
    }
    
    public List<DocumentoDigital> buscarPorTipo(Class<?> tipo) {
        return inventario.stream()
                .filter(doc -> tipo.isInstance(doc))
                .collect(Collectors.toList());
    }

    // -------------------------------------------
    // REPORTES
    // -------------------------------------------
    public List<DocumentoDigital> generarReporteTamaño() {
        return inventario.stream()
                .sorted(Comparator.comparing(DocumentoDigital::getTamañoKB).reversed())
                .collect(Collectors.toList());
    }

    public List<DocumentoDigital> generarReporteAntiguedad() {
        return inventario.stream()
                .sorted(Comparator.comparing(DocumentoDigital::getFechaCreacion))
                .collect(Collectors.toList());
    }

    // -------------------------------------------
    // CARGAR XML (básico)
    // -------------------------------------------
    public void cargarDatos() {
        try {
            serializer.cargarArchivo("catalogo.xml");
            // En implementación real: reconstruir inventario desde XML
        } catch (Exception e) {
            System.err.println(" No se encontro catalogo.xml");
        }
    }


    // -------------------------------------------
    // GUARDAR XML
    // -------------------------------------------
    public void guardarDatos() {
        serializer.setElementos(inventario);
        try {
            serializer.guardarArchivo("catalogo.xml");
            System.out.println("Datos guardados en catalogo.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DocumentoDigital> getInventario() {
        return inventario;
    }

    public TagManager getTagManager() {
        return tagManager;
    }
    
   
}      

