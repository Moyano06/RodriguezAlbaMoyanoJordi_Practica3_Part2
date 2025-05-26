package prog2.vista;

import javax.swing.*;
import java.awt.event.*;

public class FrmGestioComponentsCentral extends JDialog {
    private JPanel panell;
    private JButton btnConfBarres;
    private JSlider sldrBarres;
    private JCheckBox reactorSeleccionatEsActiuCheckBox;
    private JCheckBox chkB1;
    private JCheckBox chkB2;
    private JCheckBox chkB3;
    private JCheckBox chkB4;
    private JPanel panellSistemaRefrigeració;
    private JPanel panellReactor;
    private JPanel panellBarres;
    private JLabel lblForade;
    private JTextArea txtArea;


    public FrmGestioComponentsCentral(JFrame principal, CentralUB central) {
        setContentPane(panell);
        setSize(500, 500);
        setLocationRelativeTo(principal);
        btnConfBarres.setText("Confirmar (valor actual " + central.adaptador.getInsersioBarres() + "% )");
        sldrBarres.setValue((int)central.adaptador.getInsersioBarres());
        chkB1.setSelected(central.adaptador.estaActivaBomba(0));
        chkB2.setSelected(central.adaptador.estaActivaBomba(1));
        chkB3.setSelected(central.adaptador.estaActivaBomba(2));
        chkB4.setSelected(central.adaptador.estaActivaBomba(3));
        reactorSeleccionatEsActiuCheckBox.setSelected(central.adaptador.estaActiuReactor());
        txtArea.setText(central.adaptador.mostraEstatBombes());

        // Listener per al botó que recull el valor del slider
        btnConfBarres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valorSlider = sldrBarres.getValue();
                try {
                    central.adaptador.setInsersioBarres((float)valorSlider);
                    btnConfBarres.setText("Confirmar (valor actual " + central.adaptador.getInsersioBarres() + "% )");
                } catch (CentralUBException ex) {
                    JOptionPane.showMessageDialog(null, "Valor del slider: " + valorSlider);
                }

            }
        });


        reactorSeleccionatEsActiuCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean actiu = reactorSeleccionatEsActiuCheckBox.isSelected();
                if (actiu) {
                    try {
                        central.adaptador.activaReactor();
                    } catch (CentralUBException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    central.adaptador.desactivaReactor();

                }
            }
        });

        // Altres checkboxes per barres
        chkB1.addActionListener(e -> gestionaBomba(chkB1, 0, central));
        chkB2.addActionListener(e -> gestionaBomba(chkB2, 1, central));
        chkB3.addActionListener(e -> gestionaBomba(chkB3, 2, central));
        chkB4.addActionListener(e -> gestionaBomba(chkB4, 3, central));
    }


    private void gestionaBomba(JCheckBox checkbox, int idBomba, CentralUB central) {
        if (checkbox.isSelected()) {

            try {
                central.adaptador.activaBomba(idBomba);
            } catch (CentralUBException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } else {

            try {
                central.adaptador.desactivaBomba(idBomba);
            } catch (CentralUBException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }
}
