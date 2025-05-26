package prog2.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VisualitzaEstatDialog extends JDialog {

    private JPanel panellPrincipal;
    private JTextArea txtArea;
    private JPanel adalt;
    private JComboBox comBox;
    private JButton btnConfirmar;
    private JLabel lbl;

    public VisualitzaEstatDialog(JFrame principal, CentralUB central) {
        setContentPane(panellPrincipal);
        setSize(500,500);
        setLocationRelativeTo(principal);
        setVisible(true);
        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtArea);
        panellPrincipal.add(scroll, BorderLayout.CENTER);

        //Confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String estat = (String) comBox.getSelectedItem();
                if (estat.equals("Estat de la Central")){
                    txtArea.setText(central.adaptador.mostraEstatCentral());
                }
                else if(estat.equals("Quadern de Bitàcola")){
                    txtArea.setText(central.adaptador.mostraBitacola());
                }
                else{
                    txtArea.setText(central.adaptador.mostraTotesIncidencies());
                }

            }
        });
    }

}

