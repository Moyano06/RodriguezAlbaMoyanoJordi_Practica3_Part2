package prog2.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.bitacola.Bitacola;
import prog2.model.bitacola.PaginaEconomica;
import prog2.model.bitacola.PaginaEstat;
import prog2.model.bitacola.PaginaIncidencies;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BitacolaTest {

    private Bitacola bitacola;
    private PaginaEstat paginaEstat;
    private PaginaEconomica paginaEconomica;
    private PaginaIncidencies paginaIncidencies;

    @BeforeEach
    public void setUp() {
        bitacola = new Bitacola();
        paginaEstat = new PaginaEstat(1, 75, 500, 300, 280, 1000);
        paginaEconomica = new PaginaEconomica(2, 1200, 1150);
        paginaIncidencies = new PaginaIncidencies(3);
        paginaIncidencies.afegeixIncidencia("Error en sistema de refrigeració");
    }

    @Test
    public void testAfegeixPaginaYToString() {
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaEconomica);
        String contenido = bitacola.toString();
        assertTrue(contenido.contains("# Pàgina Estat"));
        assertTrue(contenido.contains("# Pàgina Econòmica"));
    }

    @Test
    public void testToStringVacio() {
        String resultado = bitacola.toString();
        assertEquals("No hi ha pàgines a la bitàcola\n", resultado);
    }

    @Test
    public void testGetIncidencies() {
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);
        List<PaginaIncidencies> incidencies = bitacola.getIncidencies();
        assertEquals(1, incidencies.size());
        assertEquals(paginaIncidencies, incidencies.get(0));
    }

    @Test
    public void testGetIncidenciesVacio() {
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaEconomica);
        List<PaginaIncidencies> incidencies = bitacola.getIncidencies();
        assertTrue(incidencies.isEmpty());
    }
}
