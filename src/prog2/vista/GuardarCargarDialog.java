package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GuardarCargarDialog extends JDialog {
    private CentralUB central;
    private JButton btnGuardar;
    private JButton btnCarregar;
    private JPanel panell;

    public GuardarCargarDialog(JFrame principal, CentralUB centralUB) {
        super (principal , "Gestió de Dades", true);
        setContentPane(panell);
        this.central = centralUB;
        setSize(300,300);
        setLocationRelativeTo(principal);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDades();
            }
        });
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDades();
            }
        });
    }

    private void guardarDades() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar dades");
        int seleccion = fileChooser.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fitxer = fileChooser.getSelectedFile();
            try {
                central.adaptador.guardaDades(fitxer.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Dades guardades correctament.");
            } catch (CentralUBException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void carregarDades() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Carregar dades");
        int seleccion = fileChooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fitxer = fileChooser.getSelectedFile();
            try {
                central.adaptador.carregaDades(fitxer.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Dades carregades correctament.");
            } catch (CentralUBException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
