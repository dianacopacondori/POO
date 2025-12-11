
package Libreria;

import java.time.LocalDate;
import java.util.List;


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
        System.out.println("Vizualizando en modo lectura..." + getTitulo());
    };

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    // DIANA
     public String serializarXML() {
            return super.serializarXML().replace(
            "</documento>",
            String.format(
                "  <isbn>%s</isbn>\n" +
                "  <paginas>%d</paginas>\n" +
                "</documento>",
                isbn, numeroPaginas
            )
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
