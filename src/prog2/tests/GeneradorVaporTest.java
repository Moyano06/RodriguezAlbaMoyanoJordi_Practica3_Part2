package prog2.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.bitacola.PaginaIncidencies;
import prog2.model.components.GeneradorVapor;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

public class GeneradorVaporTest {

    private GeneradorVapor generadorVapor;
    private PaginaIncidencies paginaIncidencies;

    @BeforeEach
    public void setUp() {
        generadorVapor = new GeneradorVapor();
        paginaIncidencies = new PaginaIncidencies(1);
    }

    @Test
    public void testActivaGenerador() throws CentralUBException {
        assertTrue(generadorVapor.getActivat());
        generadorVapor.activa();
        assertTrue(generadorVapor.getActivat());
    }

    @Test
    public void testCalcularOutput() {
        float input = 100;
        float output = generadorVapor.calculaOutput(input);
        assertEquals(90, output);
    }

    @Test
    public void testRevisaGenerador() throws CentralUBException {
        generadorVapor.revisa(paginaIncidencies);
        assertTrue(paginaIncidencies.getIncidencies().isEmpty());
    }

    @Test
    public void testCostOperatiu() {
        assertEquals(25, generadorVapor.getCostOperatiu());
    }
}
