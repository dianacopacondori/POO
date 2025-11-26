
package Libreria;

import java.util.Date;


public class Imagen extends DocumentoDigital {
    private String resolucion;
    private String  formato;

    

    public Imagen(String resolucion, String formato, String ID, String titulo, String autor, Date fechaCreacion, double tamañoKB) {
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
