package poo_trabajofinal;

import formularios.MainForm;



public class POO_TRABAJOFINAL {

    public static void main(String[] args) {
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
}