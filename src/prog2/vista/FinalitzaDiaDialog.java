package prog2.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalitzaDiaDialog extends JDialog {
    private CentralUB central;
    private JPanel panell;
    private JButton btnConfirmar;
    private JTextArea txtArea;

    public FinalitzaDiaDialog(JFrame principal, CentralUB central) {
        super (principal , "Finalitza dia", true);
        this.central = central;
        setSize(300,300);
        setLocationRelativeTo(principal);
        btnConfirmar.setEnabled(true);
        setContentPane(panell);
        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtArea);
        panell.add(scroll, BorderLayout.CENTER);
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executaFinalitzaDia();
                btnConfirmar.setEnabled(false);
            }
        });
    }
    private void executaFinalitzaDia() {
        try {
            float demandaActual = central.getDemandaPotencia();
            String infoDia = central.adaptador.finalitzaDia(demandaActual);
            float novaDemanda = central.generaDemandaPotencia();
            central.setDemandaPotencia(novaDemanda);

            String textFinal = "Informació del dia finalitzat: \n" +
                    infoDia +
                    "\n Nova demanda per demà: " + novaDemanda + " unitats";

            txtArea.setText(textFinal);
        } catch (CentralUBException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error durant la finalització del dia",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
