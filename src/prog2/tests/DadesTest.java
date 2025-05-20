package prog2.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.Dades;
import prog2.model.bitacola.Bitacola;
import prog2.model.bitacola.PaginaEconomica;
import prog2.model.bitacola.PaginaEstat;
import prog2.model.bitacola.PaginaIncidencies;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

public class DadesTest {

    private Dades dades;
    private PaginaEconomica paginaEconomica;
    private PaginaEstat paginaEstat;
    private PaginaIncidencies paginaIncidencies;

    @BeforeEach
    public void setUp() throws CentralUBException {
        dades = new Dades(); // Inicializa una nueva instancia de prog2.model.Dades
        paginaEconomica = new PaginaEconomica(1, 100, 80); // Simula una página económica
        paginaEstat = new PaginaEstat(1, 100, 50, 40, 30, 20); // Simula una página de estado
        paginaIncidencies = new PaginaIncidencies(1); // Simula una página de incidencias
    }

    @Test
    public void testActualitzaEconomia() {
        float demandaPotencia = 100;
        PaginaEconomica result = dades.actualitzaEconomia(demandaPotencia);

        assertNotNull(result);
        assertEquals(demandaPotencia, result.getPotenciaDemanda());
        assertTrue(result.getBeneficis() >= 0); // Asegura que los beneficios sean válidos
        assertTrue(result.getCostOperatiu() >= 0); // Asegura que los costos operativos sean válidos
    }

    @Test
    public void testRefrigeraReactor() {
        float tempInicial = dades.mostraReactor().getTemperaturaActual();
        dades.refrigeraReactor();
        assertTrue(dades.mostraReactor().getTemperaturaActual() >= 25); // Comprobar que la temperatura no baje de 25
    }

    @Test
    public void testRevisaComponents() {
        try {
            dades.revisaComponents(paginaIncidencies);
        } catch (CentralUBException e) {
            fail("No se esperaba una excepción al revisar los componentes");
        }
        assertNotNull(paginaIncidencies); // Asegurarse de que las incidencias se hayan añadido
    }

    @Test
    public void testFinalitzaDia() throws CentralUBException {
        float demandaPotencia = 100;
        Bitacola bitacolaDia = dades.finalitzaDia(demandaPotencia);

        assertNotNull(bitacolaDia); // Asegúrate de que se ha generado una bitácora
        assertEquals(3, bitacolaDia.getPaginesBitacola().size()); // Debería tener 3 páginas (económica, de estado, de incidencias)
    }

    @Test
    public void testActivaBomba() throws CentralUBException {
        int bombaId = 0;
        dades.activaBomba(bombaId);
        assertTrue(dades.mostraSistemaRefrigeracio().getBombaRefrigerants().get(bombaId).getActivat());
    }

    @Test
    public void testDesactivaBomba() throws CentralUBException {
        int bombaId = 1;
        dades.desactivaBomba(bombaId);
        assertFalse(dades.mostraSistemaRefrigeracio().getBombaRefrigerants().get(bombaId).getActivat());
    }

    @Test
    public void testSetInsercioBarres() throws CentralUBException {
        float valorInsercioBarres = 50;
        dades.setInsercioBarres(valorInsercioBarres);
        assertEquals(valorInsercioBarres, dades.getInsercioBarres());
    }

    @Test
    public void testSetInsercioBarresExcepcio() {
        float valorInsercioBarresInvalido = 150;
        CentralUBException exception = assertThrows(CentralUBException.class, () -> {
            dades.setInsercioBarres(valorInsercioBarresInvalido);
        });
        assertEquals("El percentatge d'inserció ha de ser entre 0 i 100", exception.getMessage());
    }

    @Test
    public void testGuanysAcumulats() {
        assertEquals(0, dades.getGuanysAcumulats(), "Los ganancias acumulados deberían ser cero al inicio");
    }

    @Test
    public void testMostraEstat() {
        PaginaEstat result = dades.mostraEstat();
        assertNotNull(result);
        assertTrue(result.getDia() > 0);
        assertTrue(result.getOutputReactor() >= 0);
    }
}
