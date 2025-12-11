/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poo_trabajofinal;

import Catalogo.CatalogoManager;
import Libreria.*;
import formularios.BusquedaForm;
import formularios.RegistroForm;

import java.time.LocalDate;
import javax.swing.JFrame;

/**
 * 
 * @author cvdia
 */
public class POO_TRABAJOFINAL {

    public static void main(String[] args) {
        CatalogoManager cm = new CatalogoManager();
        cm.cargarDatos();
        
        // Crear documentos de ejemplo
        LibroDigital libro = new LibroDigital("L001", "El Quijote", "Miguel de Cervantes",
                LocalDate.of(1605, 1, 1), 1500.5, "978-3-16-148410-0", 980);
        ArticuloCientifico art = new ArticuloCientifico("A001", "Deep Learning Survey", "Jose Garcia",
                LocalDate.now().minusMonths(3), 2.3, "Journal of AI", "10.1234/jai.2024.001");
        Imagen img = new Imagen("I001", "Logo UTP", "Diseñador UTP",
                LocalDate.of(2023, 9, 15), 0.8, "1920x1080", "PNG");
        
         // Agregar al catalogo
        cm.agregarDocumento(libro);
        cm.agregarDocumento(art);
        cm.agregarDocumento(img);
        
        System.out.println("\n------Procesando documentos-----");
        for (var doc : cm.getInventario()) {
            doc.procesarDocumento(); 
        }
        
        // Etiquetas
        cm.getTagManager().clasificarDocumento(libro, "clasico");
        cm.getTagManager().clasificarDocumento(libro, "literatura");
        cm.getTagManager().clasificarDocumento(art, "IA");

        System.out.println("\nEtiquetas de 'El Quijote': " + libro.ObtenerEtiquetas());

        // Reportes 
        System.out.println("\n-----Reporte por tamaño (desc)-------");
        cm.generarReporteTamaño().forEach(System.out::println);

        // Guardar
        cm.guardarDatos();
        
        BusquedaForm form = new BusquedaForm(cm);
            
        javax.swing.JFrame ventana = new javax.swing.JFrame("Búsqueda");
        ventana.setContentPane(form);
        ventana.pack();
        ventana.setLocationRelativeTo(null); // centrar
        ventana.setVisible(true);
        
        
        RegistroForm regPanel = new RegistroForm(cm);

        JFrame ventanaReg = new JFrame("Registro de Documentos");
        ventanaReg.setContentPane(regPanel);
        ventanaReg.pack();
        ventanaReg.setLocationRelativeTo(null);
        ventanaReg.setVisible(true);

        
    }
}
