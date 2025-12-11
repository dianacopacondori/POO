/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package formularios;

import Catalogo.CatalogoManager;
import Libreria.DocumentoDigital;
import java.io.File;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
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
        String[] columnas = {"ID", "Título", "Autor", "Tipo", "Tamaño(KB)", "Fecha"};
    modeloTabla = new DefaultTableModel(columnas, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    tablaReporte.setModel(modeloTabla);
    }
    
    private void actualizarTabla(List<DocumentoDigital> documentos) {
          modeloTabla.setRowCount(0);
    if (documentos == null) {
        lblContador.setText("Documentos: 0");
        return;
    }

    for (DocumentoDigital d : documentos) {
        modeloTabla.addRow(new Object[]{
                d.getID(),
                d.getTitulo(),
                d.getAutor(),
                d.getClass().getSimpleName(),
                d.getTamañoKB(),
                d.getFechaCreacion()
        });
         }

    lblContador.setText("Documentos: " + documentos.size());
    }

   private void generarReporte() {
     if (catalogoManager == null || catalogoManager.getInventario().isEmpty()) {
        JOptionPane.showMessageDialog(this, "No hay documentos en el catálogo");
        return;
    }

    List<DocumentoDigital> docs = catalogoManager.getInventario();

    switch (comboOrdenar.getSelectedIndex()) {
        case 0:
            docs = docs.stream()
                .sorted(Comparator.comparingDouble(DocumentoDigital::getTamañoKB).reversed())
                .toList();

        case 1:
            docs = docs.stream()
                .sorted(Comparator.comparingDouble(DocumentoDigital::getTamañoKB))
                .toList();

        case 2 : docs = docs.stream()
                .sorted(Comparator.comparing(
                        DocumentoDigital::getFechaCreacion,
                        Comparator.nullsLast(Comparator.naturalOrder())
                ).reversed())
                .toList();

        case 3 : docs = docs.stream()
                .sorted(Comparator.comparing(
                        DocumentoDigital::getFechaCreacion,
                        Comparator.nullsLast(Comparator.naturalOrder())
                ))
                .toList();
    }

    actualizarTabla(docs);
}
   
   private void exportarAXML() {
   if (modeloTabla.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "No hay datos para exportar.");
        return;
    }

    JFileChooser chooser = new JFileChooser();
    chooser.setSelectedFile(new File("reporte.xml"));

    if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        try (PrintWriter w = new PrintWriter(chooser.getSelectedFile(), "UTF-8")) {

            w.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            w.println("<reporte>");
            w.println("  <total>" + modeloTabla.getRowCount() + "</total>");
            w.println("  <documentos>");

            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                w.println("    <documento>");
                for (int c = 0; c < modeloTabla.getColumnCount(); c++) {
                    String tag = modeloTabla.getColumnName(c).replaceAll("[^A-Za-z0-9]","");
                    String val = String.valueOf(modeloTabla.getValueAt(i, c));
                    w.println("      <" + tag + ">" + escapeXML(val) + "</" + tag + ">");
                }
                w.println("    </documento>");
            }

            w.println("  </documentos>");
            w.println("</reporte>");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
   private String escapeXML(String s) {
    return s.replace("&","&amp;")
            .replace("<","&lt;")
            .replace(">","&gt;")
            .replace("\"","&quot;")
            .replace("'","&apos;");
}
   
// Ver detalles (DOBLE CLIC)
private void detallesFila(int fila) {
    if (fila < 0) return;

    String msg = "";
    for (int c = 0; c < modeloTabla.getColumnCount(); c++) {
        msg += modeloTabla.getColumnName(c) + ": " +
               modeloTabla.getValueAt(fila, c) + "\n";
    }

    JOptionPane.showMessageDialog(this, msg, "Detalles", JOptionPane.INFORMATION_MESSAGE);
}

public void setCatalogoManager(CatalogoManager c) {
    this.catalogoManager = c;
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
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
    generarReporte();
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnExportarXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarXMLActionPerformed
     exportarAXML();
    }//GEN-LAST:event_btnExportarXMLActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
     java.awt.Window w = javax.swing.SwingUtilities.getWindowAncestor(this);
    if (w != null) {
        w.dispose(); // cierra el diálogo/ventana
    }
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void tablaReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaReporteMouseClicked
         if (evt.getClickCount() == 2) { // Doble clic
         detallesFila(tablaReporte.getSelectedRow());
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
