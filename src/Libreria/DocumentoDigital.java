
package Libreria;

import java.util.Date;


public abstract class DocumentoDigital {
    
    protected String ID;
    protected String titulo;
    protected String autor;
    protected Date fechaCreacion;
    protected double tamañoKB;

    public DocumentoDigital(){};
    
    public DocumentoDigital(String ID, String titulo, String autor, Date fechaCreacion, double tamañoKB) {
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
    
    
}
