
package Libreria;

import java.time.LocalDate;


/**
 * Subclase de DocumentoDigital para Articulos Cientificos
 * @author cvDiana
 */
public class ArticuloCientifico extends DocumentoDigital  {
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
        System.out.println("Vizualizando en modo lectura...");
    };

    public String getRevistaPublicacion() {
        return revistaPublicacion;
    }

    public void setRevistaPublicacion(String revistaPublicacion) {
        this.revistaPublicacion = revistaPublicacion;
    }
    
      public String serializarXML() {
        return String.format(
            "<LibroDigital id=\"%s\" titulo=\"%s\" autor=\"%s\" fecha=\"%s\" tamañoKB=\"%.2f\" revistaPublicacion=\"%s\" doi=\"%d\"/>",
            ID, titulo, autor, fechaCreacion, tamañoKB, revistaPublicacion, doi);
    }
    
     public static ArticuloCientifico deserializarXML(String xmlLine) {
        // Simplificado: asume formato <ArticuloCIentifico atributos.../>
        String clean = xmlLine.replaceAll("<ArticuloCientifico ", "").replaceAll("/>", "").trim();
        String[] parts = clean.split("\"\\s+");
        java.util.Map<String, String> attrs = new java.util.HashMap<>();
        for (String part : parts) {
            if (part.contains("=")) {
                String[] kv = part.split("=", 2);
                attrs.put(kv[0].trim(), kv[1].replace("\"", "").trim());
            }
        }
        return new ArticuloCientifico(
            attrs.get("ID"),
            attrs.get("titulo"),
            attrs.get("autor"),
            java.time.LocalDate.parse(attrs.get("fecha")),
            Double.parseDouble(attrs.get("tamañoKB")),
            attrs.get("revistaPublicacion"),
            attrs.get("doi")
        );
    }
    
    
}
