
package Libreria;

import java.time.LocalDate;
import java.util.Date;


public class Imagen extends DocumentoDigital { //Romina
    private String resolucion;
    private String  formato;

    

    public Imagen(String resolucion, String formato, String ID, String titulo, String autor, LocalDate fechaCreacion, double tamañoKB) {
        super(ID, titulo, autor, fechaCreacion, tamañoKB);
        this.resolucion = resolucion;
        this.formato = formato;
    }
    

    
    @Override
    public void procesarDocumento(){
        System.out.println("Renderizando la imagen en alta definicion...");
    };

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
    
    
}
