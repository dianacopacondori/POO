
package Libreria;

import java.util.Date;


public class LibroDigital extends DocumentoDigital {
    private String isbn;
    private int numeroPaginas;

    

    public LibroDigital(String isbn, int numeroPaginas, String ID, String titulo, String autor, Date fechaCreacion, double tamañoKB) {
        super(ID, titulo, autor, fechaCreacion, tamañoKB);
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
    }
    
    @Override
    public void procesarDocumento(){
        
    };

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    
}
