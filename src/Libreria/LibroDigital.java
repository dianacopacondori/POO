
package Libreria;

import java.time.LocalDate;


/** 
 * Subclase de DocumentoDigital para libros digitales
 * @author cvDiana
 */
public class LibroDigital extends DocumentoDigital { //ROMINA
    private String isbn;
    private int numeroPaginas;

    

    public LibroDigital( String ID, String titulo, String autor, LocalDate fechaCreacion, double tamañoKB, String isbn, int numeroPaginas) {
        super(ID, titulo, autor, fechaCreacion, tamañoKB);
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
    }
    
    @Override
    public void procesarDocumento(){
        System.out.println("Vizualizando en modo lectura...");
    };

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    // DIANA
     public String serializarXML() {
        return String.format(
            "<LibroDigital id=\"%s\" titulo=\"%s\" autor=\"%s\" fecha=\"%s\" tamañoKB=\"%.2f\" isbn=\"%s\" paginas=\"%d\"/>",
            ID, titulo, autor, fechaCreacion, tamañoKB, isbn, numeroPaginas);
    }
    
     public static LibroDigital deserializarXML(String xmlLine) {
        // Simplificado: asume formato <LibroDigital atributos.../>
        String clean = xmlLine.replaceAll("<LibroDigital ", "").replaceAll("/>", "").trim();
        String[] parts = clean.split("\"\\s+");
        java.util.Map<String, String> attrs = new java.util.HashMap<>();
        for (String part : parts) {
            if (part.contains("=")) {
                String[] kv = part.split("=", 2);
                attrs.put(kv[0].trim(), kv[1].replace("\"", "").trim());
            }
        }
        return new LibroDigital(
            attrs.get("ID"),
            attrs.get("titulo"),
            attrs.get("autor"),
            java.time.LocalDate.parse(attrs.get("fecha")),
            Double.parseDouble(attrs.get("tamañoKB")),
            attrs.get("isbn"),
            Integer.parseInt(attrs.get("paginas"))
        );
    }
}
