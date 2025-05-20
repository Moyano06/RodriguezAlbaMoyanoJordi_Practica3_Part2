package prog2.model.bitacola;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import java.util.List;

/**
 *
 * @author Daniel Ortiz
 */
public interface InBitacola {
    /**
     * Afegeix una pagina a la bitàcola.
     * @param p Objecte de tipus prog2.model.bitacola.PaginaBitacola
     */
    public void afegeixPagina(PaginaBitacola p);
    
    /**
     * Obté una llista amb totes les pàgines d'incidències contingudes dins 
     * de la bitàcola
     */
    public List<PaginaIncidencies> getIncidencies();
}
