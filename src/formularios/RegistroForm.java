

package formularios;

import Catalogo.CatalogoManager;
import Libreria.*;
import java.time.LocalDate;
import javax.swing.*;

public class RegistroForm extends javax.swing.JPanel {

                private CatalogoManager catalogo;



                //     CONSTRUCTORES

                public RegistroForm(CatalogoManager catalogo) {
                    this.catalogo = catalogo;
                    initComponents();
                }

                public RegistroForm() {
                    initComponents();
                }



                //     MÉTODO: LIMPIAR CAMPOS

                private void limpiarCampos() {
                    txtID.setText("");
                    txtTitulo.setText("");
                    txtAutor.setText("");
                    txtFecha.setText("");
                    txtTamano.setText("");
                    comboTipo.setSelectedIndex(0);
                }



                //     MÉTODO: GUARDAR

                private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

                    try {
                        String id = txtID.getText().trim();
                        String titulo = txtTitulo.getText().trim();
                        String autor = txtAutor.getText().trim();
                        String fechaT = txtFecha.getText().trim();
                        String tamT = txtTamano.getText().trim();
                        String tipo = comboTipo.getSelectedItem().toString();

                        if (id.isEmpty() || titulo.isEmpty() || autor.isEmpty()
                                || fechaT.isEmpty() || tamT.isEmpty()) {

                            JOptionPane.showMessageDialog(this,
                                    "Complete todos los campos.",
                                    "Aviso",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        LocalDate fecha = LocalDate.parse(fechaT);
                        double tamano = Double.parseDouble(tamT);

                        DocumentoDigital nuevo;

                        switch (tipo) {
                            case "ArticuloCientifico" ->
                                    nuevo = new ArticuloCientifico(id, titulo, autor,
                                            fecha, tamano, "Revista X", "DOI-001");

                            case "Imagen" ->
                                    nuevo = new Imagen(id, titulo, autor,
                                            fecha, tamano, "800x600", "PNG");

                            default ->
                                    nuevo = new LibroDigital(id, titulo, autor,
                                            fecha, tamano, "N/A", 0);
                        }

                        catalogo.agregarDocumento(nuevo);
                        catalogo.guardarDatos();

                        JOptionPane.showMessageDialog(this,
                                "Documento registrado correctamente.",
                                "Éxito",
                                JOptionPane.INFORMATION_MESSAGE);

                        limpiarCampos();

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this,
                                "El tamaño debe ser un número válido.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this,
                                "Error: " + e.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }



                //     MÉTODO: LIMPIAR

                private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
                    limpiarCampos();
                }



                //     MÉTODO: CERRAR

                private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {
                    var ventana = SwingUtilities.getWindowAncestor(this);
                    if (ventana != null) ventana.dispose();
                }


    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtTamano = new javax.swing.JTextField();
        comboTipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        
        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16));
        jLabel1.setText("Registro de Documentos");
        jPanel1.add(jLabel1);

        
        jLabel2.setText("ID:");
        jLabel3.setText("Título:");
        jLabel4.setText("Autor:");
        jLabel5.setText("Fecha:");
        jLabel6.setText("Tamaño (KB):");
        jLabel7.setText("Tipo:");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"LibroDigital", "ArticuloCientifico", "Imagen"}
        ));

        
        btnGuardar.setBackground(new java.awt.Color(0, 204, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(evt -> btnGuardarActionPerformed(evt));

        btnLimpiar.setBackground(new java.awt.Color(0, 204, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(evt -> btnLimpiarActionPerformed(evt));

        btnCerrar.setBackground(new java.awt.Color(0, 204, 255));
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(evt -> btnCerrarActionPerformed(evt));


        // --------- DISEÑO AUTOMÁTICO NETBEANS ----------
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtID)
                    .addComponent(txtTitulo)
                    .addComponent(txtAutor)
                    .addComponent(txtFecha)
                    .addComponent(txtTamano)
                    .addComponent(comboTipo, 0, 180, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40)
                .addComponent(btnGuardar)
                .addGap(20)
                .addComponent(btnLimpiar)
                .addGap(20)
                .addComponent(btnCerrar)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTitulo))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAutor))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFecha))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTamano))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboTipo))
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnCerrar))
                .addGap(20))
        );
    }


    
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnCerrar;

    private javax.swing.JComboBox<String> comboTipo;

    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;

    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTamano;
}
