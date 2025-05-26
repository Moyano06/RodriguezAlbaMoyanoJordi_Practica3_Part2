package prog2.model;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import prog2.model.bitacola.Bitacola;
import prog2.model.bitacola.PaginaEconomica;
import prog2.model.bitacola.PaginaEstat;
import prog2.model.bitacola.PaginaIncidencies;
import prog2.model.components.*;
import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Ortiz
 */

public class Dades implements InDades, Serializable {

    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 250;

    private VariableUniforme variableUniforme;
    private float insercioBarres;
    private Reactor reactor;
    private SistemaRefrigeracio sistemaRefrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;
    private Bitacola bitacola;
    private int dia;
    private float guanysAcumulats;


    public Dades() throws CentralUBException {
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.reactor.desactiva();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;
        
        // Afegeix bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(variableUniforme, 0);
        BombaRefrigerant b1 = new BombaRefrigerant(variableUniforme, 1);
        BombaRefrigerant b2 = new BombaRefrigerant(variableUniforme, 2);
        BombaRefrigerant b3 = new BombaRefrigerant(variableUniforme, 3);
        
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);

        this.sistemaRefrigeracio.desactiva();
    }
    
    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a 
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou 
     * beneficis, penalització per excès de potència, costos operatius y 
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     */
    public PaginaEconomica actualitzaEconomia(float demandaPotencia){
        float potenciaGenerada = calculaPotencia();
        PaginaEconomica paginaEconomica = new PaginaEconomica(dia, demandaPotencia, potenciaGenerada);

        //Primer calculem la potencia satisfeta
        paginaEconomica.setPotenciaSatisfeta(100*potenciaGenerada/demandaPotencia);

        // Després, afegim els beneficis i la penalització segons les potències que tinguem
        if (demandaPotencia < potenciaGenerada) {
            paginaEconomica.setBeneficis(demandaPotencia);
            paginaEconomica.setPenalitzacio(250);
        } else if (demandaPotencia == potenciaGenerada) {
            paginaEconomica.setBeneficis(demandaPotencia);
            paginaEconomica.setPenalitzacio(0);
        } else {
            paginaEconomica.setBeneficis(potenciaGenerada);
            paginaEconomica.setPenalitzacio(0);
        }

        // A continuació calculem els costos operatius
        float cost = reactor.getCostOperatiu() + sistemaRefrigeracio.getCostOperatiu() +
                generadorVapor.getCostOperatiu() + turbina.getCostOperatiu();
        paginaEconomica.setCostOperatiu(cost);

        //Finalment els guanys acumulats
        float guanys = guanysAcumulats;
        guanys += paginaEconomica.getBeneficis() - paginaEconomica.getPenalitzacio() - paginaEconomica.getCostOperatiu();
        paginaEconomica.setGuanysAcumulats(guanys);


        return paginaEconomica;
    }

    /**
     * Aquest mètode ha de establir la nova temperatura del reactor.
     */
    public void refrigeraReactor() {
        float novaTemp = reactor.calculaOutput(insercioBarres) - sistemaRefrigeracio.calculaOutput(reactor.calculaOutput(insercioBarres));
        if (novaTemp < 25) {
            reactor.setTemperaturaActual(25);
        } else {
            reactor.setTemperaturaActual(novaTemp);
        }
    }

    /**
     * Aquest mètode ha de revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
  public void revisaComponents(PaginaIncidencies paginaIncidencies) throws CentralUBException {
        System.out.println("Revisant components");
        reactor.revisa(paginaIncidencies);
        sistemaRefrigeracio.revisa(paginaIncidencies);
    }


    public float getInsercioBarres() {
        return insercioBarres;
    }
  
  public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        if (insercioBarres <= 100 && insercioBarres >= 0) {
            this.insercioBarres = insercioBarres;
        }
        else{
            throw new CentralUBException("El percentatge d'inserció ha de ser entre 0 i 100");
        }
  }
  
  public void activaReactor() throws CentralUBException {
            reactor.activa();
    }
  
    public void desactivaReactor() {
        reactor.desactiva();
    }
    public Reactor mostraReactor() {
        return reactor;
    }
  
    public void activaBomba(int id) throws CentralUBException {
        boolean trobada = false;
        for (BombaRefrigerant bomba : sistemaRefrigeracio.getBombaRefrigerants()){
            if (bomba.getId() == id) {
                bomba.activa();
                trobada = true;
                break;
            }
        }
        if (!trobada){
            throw new CentralUBException("No s'ha trobat cap bomba amb aquest ID ");
        }
    }

    public void desactivaBomba(int id) throws CentralUBException {
        boolean trobada = false;
        for (BombaRefrigerant bomba : sistemaRefrigeracio.getBombaRefrigerants()){
            if (bomba.getId() == id) {
                bomba.desactiva();
                trobada = true;
                break;
            }
        }
        if (!trobada){
            throw new CentralUBException("No s'ha trobat cap bomba amb aquest ID ");
        }
    }

    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return this.sistemaRefrigeracio;
    }

    public float calculaPotencia() {
        ArrayList<InComponent> llistaComponents = new ArrayList<>();
        llistaComponents.add(reactor);
        llistaComponents.add(sistemaRefrigeracio);
        llistaComponents.add(generadorVapor);
        llistaComponents.add(turbina);

        float resultat = insercioBarres;

        for (InComponent component : llistaComponents) {
            resultat = component.calculaOutput(resultat);
        }

        return resultat;
    }

    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    public PaginaEstat mostraEstat() {
        float outputReactor = reactor.calculaOutput(insercioBarres);
        float outputSisRefrigeracio = sistemaRefrigeracio.calculaOutput(outputReactor);
        float outputGeneradorVapor = generadorVapor.calculaOutput(outputSisRefrigeracio);
        float outputTurbina = turbina.calculaOutput(outputGeneradorVapor);

        return new PaginaEstat(dia, insercioBarres, outputReactor, outputSisRefrigeracio, outputGeneradorVapor,
                outputTurbina);
    }

    public Bitacola mostraBitacola() {
        return this.bitacola;
    }

    @Override
    public List<PaginaIncidencies> mostraIncidencies() {
        return mostraBitacola().getIncidencies();
    }

    public Bitacola finalitzaDia(float demandaPotencia) throws CentralUBException{
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);

        // Genera pàgina d'estat amb la configuració escollida (la nova pàgina
        // d'estat inclou la nova configuració escollida per l'operador abans de
        // refrigerar el reactor)
        PaginaEstat paginaEstat = mostraEstat();

        // Actualitza estat de la central...

        // Refrigera el reactor
        refrigeraReactor();

        // Revisa els components de la central i registra incidències
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        revisaComponents(paginaIncidencies);

        // Incrementa dia
        dia += 1;

        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);

        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }
    public int getDia() {
        return this.dia;
    }
    public BombaRefrigerant getBomba(int id) { return sistemaRefrigeracio.getBombaRefrigerant(id); }  // o com estiguin guardades
    public Reactor getReactor() { return reactor; }

}
