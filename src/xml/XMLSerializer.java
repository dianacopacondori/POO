
package xml;

import Catalogo.CatalogoManager;
import Interfaces.IExportableXML;
import Libreria.DocumentoDigital;
import java.io.PrintWriter;


/**Clase genérica para serializar/deserializar colecciones a/desde XML.
 * @author cvDiana
 * @param <T> 
 */
public class XMLSerializer <T extends IExportableXML> {
    
       public static void guardarCatalogo(CatalogoManager catalogo, String ruta) throws Exception {

        try (PrintWriter pw = new PrintWriter(ruta, "UTF-8")) {

            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<catalogo>");

            for (DocumentoDigital d : catalogo.getInventario()) {
                pw.println("  <documento id=\"" + d.getID() + "\" tipo=\"" + d.getClass().getSimpleName() + "\">");
                pw.println("    <titulo>" + d.getTitulo() + "</titulo>");
                pw.println("    <autor>" + d.getAutor() + "</autor>");
                pw.println("    <fecha>" + d.getFechaCreacion() + "</fecha>");
                pw.println("    <tamañoKB>" + d.getTamañoKB() + "</tamañoKB>");
                pw.println("  </documento>");
            }

            pw.println("</catalogo>");
        }
    }

}
