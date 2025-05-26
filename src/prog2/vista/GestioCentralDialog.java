package prog2.vista;

import javax.swing.*;
import java.awt.event.*;

public class GestioCentralDialog extends JDialog {
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

    public GestioCentralDialog(JFrame principal, CentralUB central) {
        setContentPane(panell);
        setSize(500, 500);
        setLocationRelativeTo(principal);
        sldrBarres.setValue((int)central.adaptador.getInsersioBarres());
        txtArea.setText(central.adaptador.mostraEstatBombes());

        // Listener per al botó que recull el valor del slider
        btnConfBarres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valorSlider = sldrBarres.getValue();
                try {
                    central.adaptador.setInsersioBarres((float)valorSlider);
                } catch (CentralUBException ex) {
                    JOptionPane.showMessageDialog(null, "Valor del slider: " + valorSlider);
                }

            }
        });

        // Listeners per als checkboxes del reactor
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
        chkB1.addActionListener(e -> gestionaBomba(chkB1, 1, central));
        chkB2.addActionListener(e -> gestionaBomba(chkB2, 2, central));
        chkB3.addActionListener(e -> gestionaBomba(chkB3, 3, central));
        chkB4.addActionListener(e -> gestionaBomba(chkB4, 4, central));
    }

    /**
     * Mètode per gestionar l'estat de cada barra segons si està seleccionada o no
     */
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
