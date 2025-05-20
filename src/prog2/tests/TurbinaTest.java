package prog2.tests;

import org.junit.jupiter.api.Test;
import prog2.model.bitacola.PaginaIncidencies;
import prog2.model.components.Turbina;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

public class TurbinaTest {

    @Test
    public void testCostOperatiuSempreActiva() {
        Turbina t = new Turbina();

        assertEquals(20, t.getCostOperatiu(), "El cost operatiu hauria de ser 20 perquè la turbina sempre està activada");
    }

    @Test
    public void testActivarSempreActiva() throws CentralUBException {
        Turbina t = new Turbina();
        t.activa();
        assertTrue(t.getActivat(), "La turbina hauria d'estar activada després de cridar activa");
    }

    @Test
    public void testCalculaOutputCorrecte() {
        Turbina t = new Turbina();

        assertEquals(0, t.calculaOutput(30), "Ha de retornar input * 2 si input >=100");
    }

    @Test
    public void testCalculaOutputIncorrecte() {
        Turbina t = new Turbina();
        assertEquals(300, t.calculaOutput(150), "Si input < 100 ha de retornar 0");
    }

    @Test
    public void testGetActivatSempreTrue() {
        Turbina t = new Turbina();

        assertTrue(t.getActivat(), "getActivat() hauria de retornar true perquè la turbina sempre està activada");
    }

    @Test
    public void testRevisaNoFaRes() {
        Turbina t = new Turbina();
        t.revisa(new PaginaIncidencies(1));
        assertTrue(t.getActivat(), "revisa() no hauria de canviar l'estat de la turbina");
    }
}
