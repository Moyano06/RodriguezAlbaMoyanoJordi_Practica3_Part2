package prog2.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame {
    private JPanel panellPrincipal;
    private JButton btnGestionar;
    private JButton btnInformacio;
    private JButton btnFinalitzarDia;
    private JButton btnGuardar;
    private JTextField txtInformacióInicial;
    private CentralUB central = new CentralUB();


    public static void main (String []args){
        SwingUtilities.invokeLater(()-> {
            AppCentralUB app = null;
            try {
                app = new AppCentralUB();
            } catch (CentralUBException e) {
                throw new RuntimeException(e);
            }
            app.setVisible(true);
        });

    }
    public AppCentralUB() throws CentralUBException {
        setTitle("App CentralUB");
        setContentPane(panellPrincipal);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        txtInformacióInicial.setEditable(false);
        txtInformacióInicial.setText(InformacioDiaria());

        //Action listener 1. Gestionar Components
        btnGestionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            FrmGestioComponentsCentral dialog = new FrmGestioComponentsCentral(AppCentralUB.this,central);
            dialog.setVisible(true);
                txtInformacióInicial.setText(InformacioDiaria());
            }
        });

        //Action listener 2.Informació
        btnInformacio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            FrmVisualitzarInformacio dialog = new FrmVisualitzarInformacio(AppCentralUB.this, central);
            dialog.setVisible(true);
                txtInformacióInicial.setText(InformacioDiaria());
            }
        });

        //Action listener 3. Finalitzar Dia
        btnFinalitzarDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmFinalitzaDia dialog = new FrmFinalitzaDia(AppCentralUB.this, central);
                dialog.setVisible(true);
                txtInformacióInicial.setText(InformacioDiaria());
            }
        });

        //Action listener 4. Guardar i tal
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGuardarCarregar dialog = new FrmGuardarCarregar(AppCentralUB.this, central);
                dialog.setVisible(true);
                txtInformacióInicial.setText(InformacioDiaria());

            }
        });

    }
    // per fer mes facil el posar la informacio a cada pagina
    public String InformacioDiaria(){
        StringBuffer sb = new StringBuffer();
        sb.append("Dia: ");
        sb.append(central.adaptador.getDia());
        sb.append(", Demanda Diaria: ");
        sb.append(central.getDemandaPotencia());
        sb.append(", Guanys Acumulats: ");
        sb.append(central.adaptador.getGuanysAcumulats());
        return sb.toString();
    }


}
