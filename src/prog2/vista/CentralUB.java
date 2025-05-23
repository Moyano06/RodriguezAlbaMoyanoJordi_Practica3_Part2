package prog2.vista;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import prog2.adaptador.Adaptador;

import java.util.Scanner;

/**
 *
 * @author Daniel Ortiz
 */
public class CentralUB {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;

    /** Creem un adaptador **/
    public Adaptador adaptador = new Adaptador();

    private Scanner sc = new Scanner(System.in);
    
    /* Constructor*/
    public CentralUB() throws CentralUBException {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();

    }
    
    public void gestioCentralUB() throws CentralUBException {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        // Completar
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("prog2.vista.Menu Principal", OpcionsMenuPrincipal.values());
        menu.setDescripcions(descMenuPrincipal);
        OpcionsMenuPrincipal opcio;

        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);


            switch (opcio) {
                case GESTIO_BARRES_CONTROL:
                    gestioBarresControl(sc);
                    break;
                case GESTIO_REACTOR:
                    gestioReactor(sc);
                    break;
                case GESTIO_SISTEMA_REFRIGERACIO:
                    gestioSistemaRefrigeracio(sc);
                    break;
                case MOSTRAR_ESTAT_CENTRAL:
                    System.out.println(adaptador.mostraEstatCentral());
                    break;
                case MOSTRAR_BITACOLA:
                    System.out.println(adaptador.mostraBitacola());
                    break;
                case MOSTRAR_INCIDENCIES:
                    System.out.println(adaptador.mostraTotesIncidencies());
                    break;
                case OBTENIR_DEMANDA_SATISFETA_AMB_CONFIGURACIO_ACTUAL:
                    System.out.println(adaptador.mostraEstatDemandaActual(demandaPotencia));
                    break;
                case FINALITZAR_DIA:
                    finalitzaDia();
                    break;
                case GUARDAR_DADES:
                    System.out.println("Guardant dades...");
                    adaptador.guardaDades("dades.txt");
                    break;
                case CARREGAR_DADES:
                    System.out.println("Carregar dades:");
                    adaptador.carregaDades("dades.txt");
                    break;
                case SORTIR:
                    System.out.println("Fins aviat!");
                    break;
            }
        } while (opcio != OpcionsMenuPrincipal.SORTIR);
        
    }

    private void gestioBarresControl(Scanner sc) throws CentralUBException{

        Menu<OpcionsSubmenuGestioBarresControl> submenu = new Menu<>("Gestió Barres de Control", OpcionsSubmenuGestioBarresControl.values());
        submenu.setDescripcions(descMenuBarresControl);
        OpcionsSubmenuGestioBarresControl opcioBC;

        do {
            submenu.mostrarMenu();
            opcioBC = submenu.getOpcio(sc);

            switch (opcioBC) {
                case OBTENIR_INSERCIO_BARRES:
                    System.out.println("Insersió Barres: " + adaptador.getInsersioBarres());
                    break;
                case ESTABLIR_INSERCIO_BARRES:
                    System.out.println("Quin grau (%) d'inserció de barres vols establir?");
                    adaptador.setInsersioBarres(sc.nextFloat());
                    break;
                case SORTIR:
                    System.out.println("Tornant al menú principal...");
                    break;
            }
        } while (opcioBC != OpcionsSubmenuGestioBarresControl.SORTIR);
    }

    private void gestioReactor(Scanner sc) throws CentralUBException{

        Menu<OpcionsSubmenuGestioReactor> submenu = new Menu<>("Gestió prog2.model.components.Reactor", OpcionsSubmenuGestioReactor.values());
        submenu.setDescripcions(descMenuReactor);
        OpcionsSubmenuGestioReactor opcioReactor;

        do {
            submenu.mostrarMenu();
            opcioReactor = submenu.getOpcio(sc);

            switch (opcioReactor) {
                case ACTIVAR_REACTOR:
                    adaptador.activaReactor();
                    System.out.println("prog2.model.components.Reactor activat");
                    break;
                case DESACTIVAR_REACTOR:
                    adaptador.desactivaReactor();
                    System.out.println("prog2.model.components.Reactor desactivat");
                    break;
                case MOSTRAR_ESTAT:
                    System.out.println(adaptador.mostraEstatReactor());
                    break;
                case SORTIR:
                    System.out.println("Tornant al menú principal...");
                    break;
            }
        } while (opcioReactor != OpcionsSubmenuGestioReactor.SORTIR);
    }

    private void gestioSistemaRefrigeracio(Scanner sc) throws CentralUBException {

        Menu<OpcionsSubmenuGestioSistemaRefrigeracio> submenu = new Menu<>("Gestió Sistema Refrigeració", OpcionsSubmenuGestioSistemaRefrigeracio.values());
        submenu.setDescripcions(descMenuSistemaRefrigeracio);
        OpcionsSubmenuGestioSistemaRefrigeracio opcioSR;

        do {
            submenu.mostrarMenu();
            opcioSR = submenu.getOpcio(sc);

            switch (opcioSR) {
                case ACTIVAR_TOTES_BOMBES:
                    adaptador.activaTotesBombes();
                    System.out.println("Totes les bombes activades");
                    break;
                case DESACTIVAR_TOTES_BOMBES:
                    adaptador.desactivaTotesBombes();
                    System.out.println("Totes les bombes desactivades");
                    break;
                case ACTIVAR_BOMBA:
                    System.out.println("Quina bomba vols activar? (del 0 al 3)");
                    adaptador.activaBomba(sc.nextInt());
                    break;
                case DESACTIVAR_BOMBA:
                    System.out.println("Quina bomba vols desactivar? (del 0 al 3)");
                    adaptador.desactivaBomba(sc.nextInt());
                    break;
                case MOSTRAR_ESTAT:
                    System.out.println(adaptador.mostraEstatBombes());
                    break;
                case SORTIR:
                    System.out.println("Tornant al menú principal...");
                    break;
            }
        } while (opcioSR != OpcionsSubmenuGestioSistemaRefrigeracio.SORTIR);
    }

    // Opcions del menú principal
    static private enum OpcionsMenuPrincipal {
        GESTIO_BARRES_CONTROL,
        GESTIO_REACTOR,
        GESTIO_SISTEMA_REFRIGERACIO,
        MOSTRAR_ESTAT_CENTRAL,
        MOSTRAR_BITACOLA,
        MOSTRAR_INCIDENCIES,
        OBTENIR_DEMANDA_SATISFETA_AMB_CONFIGURACIO_ACTUAL,
        FINALITZAR_DIA,
        GUARDAR_DADES,
        CARREGAR_DADES,
        SORTIR
    }

    // Opcions del submenú de Gestió Barres de Control
    static private enum OpcionsSubmenuGestioBarresControl {
        OBTENIR_INSERCIO_BARRES,
        ESTABLIR_INSERCIO_BARRES,
        SORTIR
    }

    // Opcions del submenú de Gestió prog2.model.components.Reactor
    static private enum OpcionsSubmenuGestioReactor {
        ACTIVAR_REACTOR,
        DESACTIVAR_REACTOR,
        MOSTRAR_ESTAT,
        SORTIR
    }

    // Opcions del submenú de Gestió Sistema Refrigeració
    static private enum OpcionsSubmenuGestioSistemaRefrigeracio {
        ACTIVAR_TOTES_BOMBES,
        DESACTIVAR_TOTES_BOMBES,
        ACTIVAR_BOMBA,
        DESACTIVAR_BOMBA,
        MOSTRAR_ESTAT,
        SORTIR
    }

    //  Descripcions del menú principal i dels corresponents submenús
    static private String[] descMenuPrincipal = {
            "Gestió Barres de Control",
            "Gestió prog2.model.components.Reactor",
            "Gestió Sistema Refrigeració",
            "Mostrar Estat Central",
            "Mostrar Bitàcola",
            "Mostrar Incidències",
            "Obtenir Demanda Satisfeta amb Configuració Actual",
            "Finalitzar Dia",
            "Guardar dades",
            "Carregar dades",
            "Sortir"
    };


    static private String[] descMenuBarresControl = {
            "Obtenir Inserció Barres",
            "Establir Inserció Barres",
            "Sortir"
    };

    static private String[] descMenuReactor = {
            "Activar prog2.model.components.Reactor",
            "Desactivar prog2.model.components.Reactor",
            "Mostrar Estat",
            "Sortir"
    };

    static private String[] descMenuSistemaRefrigeracio = {
            "Activar Totes les Bombes",
            "Desactivar Totes les Bombes",
            "Activar Bomba",
            "Desactivar Bomba",
            "Mostrar Estat",
            "Sortir"
    };
    
    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
            if (valor < DEMANDA_MIN)
                return DEMANDA_MIN;
            else
                return valor;
    }
    
    private void finalitzaDia() throws CentralUBException{
        // Finalitzar dia i imprimir informacio de la central
        String info = new String();
        info = adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }

    public float getDemandaPotencia(){
        return demandaPotencia;
    }
}
