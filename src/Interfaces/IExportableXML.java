
package Interfaces;

/** Interfaz para serialización/deserialización XML.
 * @author cvDiana
 */
public interface IExportableXML {
    
    public String serializarXML();
    public void deserializarXML(String xml);

}
