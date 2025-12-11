/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xml;

import Catalogo.CatalogoManager;
import Libreria.ArticuloCientifico;
import Libreria.DocumentoDigital;
import Libreria.Imagen;
import Libreria.LibroDigital;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

/**
 *
 * @author cvdia
 */
public class XMLDeserializer {

    public static void cargarCatalogo(String ruta, CatalogoManager catalogo) throws IOException {

        String xml = Files.readString(Path.of(ruta));   //leer todo el XML

        // Separar cada <documento ...> ... </documento>
        String[] bloques = xml.split("</documento>");

        for (String bloque : bloques) {

            if (!bloque.contains("<documento")) {
                continue;
            }

            // Extraer atributos (tipo e id)
            String tipo = extraer(bloque, "tipo");
            String id = extraer(bloque, "id");

            String titulo = extraerTag(bloque, "titulo");
            String autor = extraerTag(bloque, "autor");
            String fecha = extraerTag(bloque, "fecha");
            String tam = extraerTag(bloque, "tamañoKB");

            // Convertir valores
            LocalDate fechaCreacion = fecha.isEmpty() ? LocalDate.now() : LocalDate.parse(fecha);
            double tamKB = tam.isEmpty() ? 0 : Double.parseDouble(tam);

            DocumentoDigital doc = null;

            // Seleccionar clase según tipo
            switch (tipo) {
                case "ArticuloCientifico" -> {
                    String revista = extraerTag(bloque, "revista");
                    String doi = extraerTag(bloque, "doi");

                    doc = new ArticuloCientifico(
                            id, titulo, autor, fechaCreacion, tamKB,
                            revista, doi
                    );
                    System.out.println("Cargando: " + tipo + " - " + titulo);

                }

                case "Imagen" -> {
                    String resolucion = extraerTag(bloque, "resolucion");
                    String formato = extraerTag(bloque, "formato");

                    doc = new Imagen(id, titulo, autor, fechaCreacion, tamKB, resolucion, formato);
                System.out.println("Cargando: " + tipo + " - " + titulo);

                }

                case "LibroDigital" -> {
                    String isbn = extraerTag(bloque, "isbn");
                    String paginasStr = extraerTag(bloque, "paginas");
                    int paginas = paginasStr.isEmpty() ? 0 : Integer.parseInt(paginasStr);

                    doc = new LibroDigital(id, titulo, autor, fechaCreacion, tamKB, isbn, paginas);
                System.out.println("Cargando: " + tipo + " - " + titulo);

                }
            }

            if (doc != null) {
                catalogo.agregarDocumento(doc);
            }
        }
    }

    private static String extraer(String txt, String atributo) {

        String busqueda = atributo + "=\"";
        int i = txt.indexOf(busqueda);
        if (i == -1) {
            return "";
        }

        int ini = i + busqueda.length();
        int fin = txt.indexOf("\"", ini);

        if (fin == -1) {
            return "";
        }
        return txt.substring(ini, fin).trim();
    }

    private static String extraerTag(String txt, String tag) {
        // Busca dentro de <tag>valor</tag>
        String iniTag = "<" + tag + ">";
        String finTag = "</" + tag + ">";

        int i = txt.indexOf(iniTag);
        int f = txt.indexOf(finTag);

        if (i == -1 || f == -1) {
            return "";
        }

        return txt.substring(i + iniTag.length(), f).trim();
    }
}
