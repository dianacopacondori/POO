
package Libreria;

import java.time.LocalDate;
import java.util.List;


/**
 * Subclase de DocumentoDigital para Articulos Cientificos
 * @author cvDiana
 */
public class ArticuloCientifico extends DocumentoDigital  { //ROMINA
    private String revistaPublicacion;
    private String doi;

    public ArticuloCientifico() {
    }

    public ArticuloCientifico( String ID, String titulo, String autor, LocalDate fechaCreacion, double tamañoKB, String revistaPublicacion, String doi) {
        super(ID, titulo, autor, fechaCreacion, tamañoKB);
        this.revistaPublicacion = revistaPublicacion;
        this.doi = doi;
    }

    @Override
    public void procesarDocumento(){
        System.out.println("Procesando analisis cientifico: "+ getTitulo());
    };

    public String getRevistaPublicacion() {
        return revistaPublicacion;
    }

    public void setRevistaPublicacion(String revistaPublicacion) {
        this.revistaPublicacion = revistaPublicacion;
    }
    
    
    // DIANA
      public String serializarXML() {
       return super.serializarXML().replace(
            "</documento>",
            String.format("  <revista>%s</revista>\n" +
                          "  <doi>%s</doi>\n" +
                          "</documento>", revistaPublicacion, doi)
        );
    }

    @Override
    public void agregarEtiqueta(String idDocumento, String tag) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> ObtenerEtiquetas(String idDocumento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
      
}
