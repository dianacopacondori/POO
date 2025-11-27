
package Libreria;

import java.time.LocalDate;



public abstract class DocumentoDigital {
    
    protected String ID;
    protected String titulo;
    protected String autor;
    protected LocalDate fechaCreacion;
    protected double tamañoKB;

    public DocumentoDigital(){};
    
    public DocumentoDigital(String ID, String titulo, String autor, LocalDate fechaCreacion, double tamañoKB) {
        this.ID = ID;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaCreacion = fechaCreacion;
        this.tamañoKB = tamañoKB;
    }
    
    public void procesarDocumento(){
        
    };

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
    
      @Override
    public String toString() {
        return String.format("[%s] %s (por %s, %s KB)", 
            this.getClass().getSimpleName(), titulo, autor, tamañoKB);
    }

    
}
