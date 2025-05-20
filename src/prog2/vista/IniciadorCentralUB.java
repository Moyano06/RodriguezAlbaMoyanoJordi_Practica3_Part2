package prog2.vista;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dortiz
 */
public class IniciadorCentralUB {

    /**
     * Main method of the program. It is used to start the program and to
     * prepare the text menu.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CentralUBException {
        // Creem un objecte de la prog2.vista
        CentralUB centralUB=new CentralUB();

        // Inicialitza l'execució de la prog2.vista
        centralUB.gestioCentralUB();
    }
}
