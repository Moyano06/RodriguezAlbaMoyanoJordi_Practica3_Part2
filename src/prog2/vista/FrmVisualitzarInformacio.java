package prog2.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FrmVisualitzarInformacio extends JDialog {

    private JPanel panellPrincipal;
    private JTextArea txtArea;
    private JPanel adalt;
    private JComboBox comBox;
    private JLabel lbl;

    public FrmVisualitzarInformacio(JFrame principal, CentralUB central) {
        setContentPane(panellPrincipal);
        setSize(500, 500);
        setLocationRelativeTo(principal);
        setVisible(true);
        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setText(central.adaptador.mostraEstatCentral());
        JScrollPane scroll = new JScrollPane(txtArea);
        panellPrincipal.add(scroll, BorderLayout.CENTER);

        //Confirmar
        comBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    actualitzaText((String) e.getItem(), central);
                }
            }
        });
    }
    private void actualitzaText(String seleccio, CentralUB central) {
        switch (seleccio) {
            case "Estat de la Central":
                txtArea.setText(central.adaptador.mostraEstatCentral());
                break;
            case "Quadern de Bitàcola":
                txtArea.setText(central.adaptador.mostraBitacola());
                break;
            case "Incidències":
                txtArea.setText(central.adaptador.mostraTotesIncidencies());
                break;
            default:
                txtArea.setText("Selecció desconeguda.");
        }
    }
}

