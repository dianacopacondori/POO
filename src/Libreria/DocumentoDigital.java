
package Libreria;

import Interfaces.IClasificable;
import Interfaces.IExportableXML;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public abstract class DocumentoDigital implements IExportableXML, IClasificable { //ROMINA
    
    protected String ID;
    protected String titulo;
    protected String autor;
    protected LocalDate fechaCreacion;
    protected double tamañoKB;
    
    private List<String> etiquetas = new ArrayList<>();

    public DocumentoDigital(){};
    
    public DocumentoDigital(String ID, String titulo, String autor, LocalDate fechaCreacion, double tamañoKB) {
        this.ID = ID;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaCreacion = fechaCreacion;
        this.tamañoKB = tamañoKB;
    }
    
    public abstract void procesarDocumento(); //metodo abstracto 

    // gettes & getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getTamañoKB() {
        return tamañoKB;
    }

    public void setTamañoKB(double tamañoKB) {
        this.tamañoKB = tamañoKB;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
      @Override         //DIANA - Imprime en texto
    public String toString() {
        return String.format("[%s] %s (por %s, %s KB)", 
            this.getClass().getSimpleName(), titulo, autor, tamañoKB);
    }
    
   // Implementación de IClasificable
    public void agregarEtiqueta(String tag) {
        this.etiquetas.add(tag);
    }
    
    public List<String> ObtenerEtiquetas() {
        return new ArrayList<>(etiquetas);
    }
    
    // Implementación de IExportableXML (plantilla básica – mejorado en XMLSerializer)
    @Override
    public String serializarXML() {
        return String.format("""
                             <documento tipo="%s" id="%s">
                               <titulo>%s</titulo>
                               <autor>%s</autor>
                               <fecha>%s</fecha>
                               <tama\u00f1oKB>%.2f</tama\u00f1oKB>
                             </documento>""",
                this.getClass().getSimpleName(),
                ID, titulo, autor,
                fechaCreacion != null ? fechaCreacion.toString(): "",
                tamañoKB
        );
    }

    @Override
    public void deserializarXML(String xml) {
        // Simplificado: en producción usar un parser (DOM/StAX/JAXB)
        // Aquí solo es demostrativo; la lógica real irá en XMLSerializer
        throw new UnsupportedOperationException("Deserialización por documento individual no implementada aquí");
    }
    
}
