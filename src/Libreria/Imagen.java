
package Libreria;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class Imagen extends DocumentoDigital { //Romina
    private String resolucion;
    private String  formato;

    public Imagen(String id, String titulo, String autor,
                  LocalDate fechaCreacion,       
                  double tamañoKB,
                  String resolucion,
                  String formato) {
        super(id, titulo, autor, fechaCreacion, tamañoKB);
        this.resolucion = resolucion;
        this.formato = formato;
    }

    

    
    @Override
    public void procesarDocumento(){ //imprime el comentario , de proceso de documento
        System.out.println("Renderizando la imagen en alta definicion: " + getTitulo());
    };

    
    //getters & setters
    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
    
    //DIANA
    @Override
    public String serializarXML(){
        return super.serializarXML().replace(
            "</documento>",
            String.format("  <resolucion>%s</resolucion>\n" +
                          "  <formato>%s</formato>\n" +
                          "</documento>", resolucion, formato)
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
