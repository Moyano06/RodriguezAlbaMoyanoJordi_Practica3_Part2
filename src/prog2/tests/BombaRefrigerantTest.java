package prog2.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.VariableUniforme;
import prog2.model.bitacola.PaginaIncidencies;
import prog2.model.components.BombaRefrigerant;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

public class BombaRefrigerantTest {

    private BombaRefrigerant bombaFunciona;
    private BombaRefrigerant bombaFalla;
    private PaginaIncidencies paginaIncidencies;

    @BeforeEach
    public void setUp() {
        // Usamos prog2.model.VariableUniforme con una semilla fija para que siempre devuelva los mismos valores.
        bombaFunciona = new BombaRefrigerant(new VariableUniforme(1), 1);  // Valor con semilla 1
        bombaFalla = new BombaRefrigerant(new VariableUniforme(2), 2);    // Valor con semilla 2
        paginaIncidencies = new PaginaIncidencies(1);
    }

    @Test
    public void testActivaIDesactiva() throws CentralUBException {
        bombaFunciona.desactiva();
        assertFalse(bombaFunciona.getActivat());

        bombaFunciona.activa();
        assertTrue(bombaFunciona.getActivat());
    }

    @Test
    public void testActivaLlançaExcepcioSiForaDeServei() {
        bombaFalla.desactiva();
        try {
            bombaFalla.revisa(paginaIncidencies);
        } catch (CentralUBException e) {
            fail("No s'esperava excepció durant revisa()");
        }

        assertTrue(bombaFalla.getForaDeServei());
        assertFalse(bombaFalla.getActivat());

        CentralUBException e = assertThrows(CentralUBException.class, () -> bombaFalla.activa());
        assertEquals("Esta fora de servei, no es pot activar", e.getMessage());
    }

    @Test
    public void testRevisaActivaBombaSiValorCorrecte() {
        try {
            bombaFunciona.desactiva();
            assertFalse(bombaFunciona.getActivat());

            bombaFunciona.revisa(paginaIncidencies);
            assertTrue(bombaFunciona.getActivat());
            assertFalse(bombaFunciona.getForaDeServei());
        } catch (CentralUBException e) {
            fail("No s'esperava excepció.");
        }
    }

    @Test
    public void testRevisaDetectaErrorIGeneraIncidencia() {
        try {
            bombaFalla.revisa(paginaIncidencies);
        } catch (CentralUBException e) {
            fail("No s'esperava excepció a revisa()");
        }

        assertTrue(bombaFalla.getForaDeServei());
        assertFalse(bombaFalla.getActivat());
        assertEquals(1, paginaIncidencies.getIncidencies().size());
        assertTrue(paginaIncidencies.getIncidencies().get(0).contains("fora de servei"));
    }

    @Test
    public void testCapacitatICost() {
        assertEquals(250, bombaFunciona.getCapacitat());
        assertEquals(130, bombaFunciona.getCostOperatiu());

        bombaFunciona.desactiva();
        assertEquals(0, bombaFunciona.getCapacitat());
        assertEquals(0, bombaFunciona.getCostOperatiu());
    }

    @Test
    public void testToString() {
        String s = bombaFunciona.toString();
        assertTrue(s.contains("Id=1"));
        assertTrue(s.contains("Activat=true"));
    }
}
