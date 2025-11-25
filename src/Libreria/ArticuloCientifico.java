
package Libreria;

import java.util.Date;


public class ArticuloCientifico extends DocumentoDigital  {
    private String revistaPublicacion;
    private String doi;

    public ArticuloCientifico() {
    }

    public ArticuloCientifico(String revistaPublicacion, String doi, String ID, String titulo, String autor, Date fechaCreacion, double tamañoKB) {
        super(ID, titulo, autor, fechaCreacion, tamañoKB);
        this.revistaPublicacion = revistaPublicacion;
        this.doi = doi;
    }

    @Override
    public void procesarDocumento(){
        
    };

    public String getRevistaPublicacion() {
        return revistaPublicacion;
    }

    public void setRevistaPublicacion(String revistaPublicacion) {
        this.revistaPublicacion = revistaPublicacion;
    }
    
    
    
    
}
