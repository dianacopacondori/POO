/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package formularios;

import Catalogo.CatalogoManager;
import Libreria.DocumentoDigital;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cvdia
 */
public class ReportesForm extends javax.swing.JPanel {
private CatalogoManager catalogoManager;
private DefaultTableModel modeloTabla;
    /**
     * Creates new form ReportesForm
     */
    public ReportesForm() {
        initComponents();
        inicializarTabla();
    }
    
    private void inicializarTabla(){
        String[] columnas = {"ID", "TITULO", "AUTOR", "TIPO","TAMAÑO(KB)", "FECHA"};
        modeloTabla = new DefaultTableModel(columnas,0){
             @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaReporte.setModel(modeloTabla);
    }
    
    private void actualizarTabla(List<DocumentoDigital> documentos) {
        modeloTabla.setRowCount(0);
        
        for (DocumentoDigital doc : documentos) {
            modeloTabla.addRow(new Object[]{
                doc.getID(),
                doc.getTitulo(),
                doc.getAutor(),
                doc.getClass().getSimpleName(),
                doc.getTamañoKB(),
                doc.getFechaCreacion()
            });
        }
    }
    
      private void exportarReporte() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte XML");
        fileChooser.setSelectedFile(new java.io.File("reporte.xml"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File archivo = fileChooser.getSelectedFile();
                // Aquí iría tu lógica de exportación XML
                JOptionPane.showMessageDialog(this, "Exportado a: " + archivo.getAbsolutePath());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
      
       private void mostrarDetalles(int fila) {
        String id = modeloTabla.getValueAt(fila, 0).toString();
        String titulo = modeloTabla.getValueAt(fila, 1).toString();
        String autor = modeloTabla.getValueAt(fila, 2).toString();
        
        String detalles = String.format(
            "ID: %s\nTítulo: %s\nAutor: %s\nTipo: %s\nTamaño: %s KB\nFecha: %s",
            id, titulo, autor,
            modeloTabla.getValueAt(fila, 3),
            modeloTabla.getValueAt(fila, 4),
            modeloTabla.getValueAt(fila, 5)
        );
        
        JOptionPane.showMessageDialog(this, detalles, "Detalles del Documento", JOptionPane.INFORMATION_MESSAGE);
    }
       
   private void generarReporte(int criterio) {
    if (catalogoManager.getInventario().isEmpty()) {
        JOptionPane.showMessageDialog(this, "No hay documentos en el catálogo");
        return;
    }
    
    List<DocumentoDigital> documentos;
    
    switch (criterio) {
        case 0: // Mayor a menor tamaño
            documentos = catalogoManager.getInventario().stream()
                .sorted((a, b) -> {
                    int sizeA = (int) a.getTamañoKB();
                    int sizeB = (int) b.getTamañoKB();
                    return Integer.compare(sizeB, sizeA); // Descendente
                })
                .collect(Collectors.toList());
            break;
            
        case 1: // Menor a mayor tamaño
            documentos = catalogoManager.getInventario().stream()
                .sorted((a, b) -> {
                    int sizeA = (int) a.getTamañoKB();
                    int sizeB = (int) b.getTamañoKB();
                    return Integer.compare(sizeA, sizeB); // Ascendente
                })
                .collect(Collectors.toList());
            break;
            
        case 2: // Más reciente
            documentos = catalogoManager.getInventario().stream()
                .sorted((a, b) -> {
                    LocalDate fechaA = a.getFechaCreacion();
                    LocalDate fechaB = b.getFechaCreacion();
                    return fechaB.compareTo(fechaA); // Más reciente primero
                })
                .collect(Collectors.toList());
            break;
            
        case 3: // Más antiguo
            documentos = catalogoManager.getInventario().stream()
                .sorted((a, b) -> {
                    LocalDate fechaA = a.getFechaCreacion();
                    LocalDate fechaB = b.getFechaCreacion();
                    return fechaA.compareTo(fechaB); // Más antiguo primero
                })
                .collect(Collectors.toList());
            break;
            
        default:
            documentos = catalogoManager.getInventario();
    }
    
    actualizarTabla(documentos);
    lblContador.setText("Documentos: " + documentos.size());
}
   
   private void exportarAXML(File archivo) throws IOException {
    try (java.io.PrintWriter writer = new java.io.PrintWriter(archivo, "UTF-8")) {
        writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.println("<reporte>");
        writer.println("  <fecha>" + new java.util.Date() + "</fecha>");
        writer.println("  <total>" + modeloTabla.getRowCount() + "</total>");
        writer.println("  <documentos>");
        
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            writer.println("    <documento id=\"" + (i+1) + "\">");
            
            for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                String columna = modeloTabla.getColumnName(j);
                String valor = modeloTabla.getValueAt(i, j).toString();
                
                // Etiqueta simple
                String tag = columna.replaceAll("[^a-zA-Z0-9]", "");
                
                writer.println("      <" + tag + ">" + valor + "</" + tag + ">");
            }
            
            writer.println("    </documento>");
        }
        
        writer.println("  </documentos>");
        writer.println("</reporte>");
    }
}
   private String escapeXML(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&apos;");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboOrdenar = new javax.swing.JComboBox<>();
        btnGenerar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReporte = new javax.swing.JTable();
        btnExportarXML = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        lblContador = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel1.setText("Reportes de Documentos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(121, 121, 121))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel2.setText("Ordenar por:");

        comboOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tamaño (Mayor a Menor)", "Tamaño (Menor a Mayor)", "Fecha (Más reciente)", "Fecha (Más antiguo)" }));

        btnGenerar.setBackground(new java.awt.Color(102, 204, 255));
        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        tablaReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Titulo", "Autor", "Tipo", "Tamaño(KB)", "Fecha"
            }
        ));
        tablaReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaReporteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaReporte);

        btnExportarXML.setBackground(new java.awt.Color(102, 204, 255));
        btnExportarXML.setText("Exportar a XML");
        btnExportarXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarXMLActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(102, 204, 255));
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        lblContador.setText("Documentos: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(comboOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnGenerar)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportarXML)
                .addGap(55, 55, 55)
                .addComponent(btnCerrar)
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContador)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblContador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportarXML)
                    .addComponent(btnCerrar))
                .addGap(0, 8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
         if (catalogoManager == null) {
        JOptionPane.showMessageDialog(this, 
            "Error: CatalogoManager no inicializado", 
            "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    int opcion = comboOrdenar.getSelectedIndex();
    generarReporte(opcion);
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnExportarXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarXMLActionPerformed
          if (modeloTabla.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this,
            "No hay datos para exportar. Genere un reporte primero.",
            "Sin Datos", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Dialogo para guardar archivo
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Exportar Reporte como XML");
    fileChooser.setSelectedFile(new java.io.File("reporte_documentos.xml"));
    
    int resultado = fileChooser.showSaveDialog(this);
    
    if (resultado == JFileChooser.APPROVE_OPTION) {
        try {
            exportarAXML(fileChooser.getSelectedFile());
            JOptionPane.showMessageDialog(this,
                "Reporte exportado exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnExportarXMLActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
    setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void tablaReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaReporteMouseClicked
         if (evt.getClickCount() == 2) { // Doble clic
        int fila = tablaReporte.getSelectedRow();
        if (fila != -1) {
            mostrarDetalles(fila);
        }
    }
    }//GEN-LAST:event_tablaReporteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnExportarXML;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JComboBox<String> comboOrdenar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblContador;
    private javax.swing.JTable tablaReporte;
    // End of variables declaration//GEN-END:variables
}
