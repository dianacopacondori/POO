
package Catalogo;

import Busqueda.Buscador;
import Etiqueta.TagManager;
import Libreria.DocumentoDigital;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
   private static final String RUTA_CATALOGO = "catalogo.xml";


       public List<DocumentoDigital> getInventario() {
        return Collections.unmodifiableList(inventario);
    }
       
    private final String rutaXML = "documentos.xml";

    public CatalogoManager() {
    System.out.println("\n" + "=".repeat(60));
    System.out.println("CREANDO NUEVO CATALOGO MANAGER");
    System.out.println("=".repeat(60));
    
    // Inicializar vacío
    inventario = new ArrayList<>();
    mapaBusqueda = new HashMap<>();
    
    // Luego cargar
    cargarDatos();
    
    System.out.println("Estado inicial del catálogo:");
    System.out.println("- Total documentos: " + inventario.size());
    System.out.println("=".repeat(60) + "\n");}

    // -------------------------------------------
    // AGREGAR DOCUMENTO
    // -------------------------------------------
    public void agregarDocumento(DocumentoDigital d) {
        inventario.add(d);
        mapaBusqueda.put(d.getID(), d);
        guardarDatos();
    }

    // -------------------------------------------
    // ELIMINAR DOCUMENTO
    // -------------------------------------------
    public boolean eliminarDocumento(String id) {
        DocumentoDigital d = mapaBusqueda.remove(id);

        if (d != null) {
            inventario.remove(d);
            guardarDatos();
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
    
    public DocumentoDigital buscarPorID(String id) {
    return mapaBusqueda.get(id);
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
      
        File f = new File(RUTA_CATALOGO);
       System.out.println("========================================================");
    System.out.println("INFORMACIÓN DE CARGA:");
    System.out.println("Ruta relativa: " + RUTA_CATALOGO);
    System.out.println("Ruta absoluta: " + f.getAbsolutePath());
    System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));
    System.out.println("Archivo existe?: " + f.exists());
    System.out.println("Tamaño del archivo: " + (f.exists() ? f.length() + " bytes" : "N/A"));
    System.out.println("========================================================");
    
    if (!f.exists()) {
        System.out.println("No existe el archivo de catálogo: " + f.getPath());
        return;
    }

      try {
        // LIMPIAR ANTES DE CARGAR
        inventario.clear();
        mapaBusqueda.clear();
        
        xml.XMLDeserializer.cargarCatalogo(f.getPath(), this);
        
        System.out.println("Datos cargados exitosamente. Documentos: " + inventario.size());

    } catch (Exception e) {
        System.err.println("✗ ERROR al cargar el catálogo:");
        e.printStackTrace();
        // Mantener el catálogo vacío en caso de error
        inventario.clear();
        mapaBusqueda.clear();
    }
    }


    // -------------------------------------------
    // GUARDAR XML
    // -------------------------------------------
    public void guardarDatos() {
        File f = new File(RUTA_CATALOGO);
    File parent = f.getParentFile();
    if (parent != null && !parent.exists()) {
        if (!parent.mkdirs()) {
            System.err.println("No se pudo crear la carpeta: " + parent.getPath());
        }
    }

    try {
        // xml.XMLSerializer.guardarCatalogo escribe todo el inventario desde el catalogo (this)
        xml.XMLSerializer.guardarCatalogo(this, f.getPath());
        System.out.println("Datos guardados en: " + f.getPath());
    } catch (Exception e) {
        System.err.println("Error guardando catálogo en " + f.getPath() + ": " + e.getMessage());
        e.printStackTrace();
    }
    
    }
}      

