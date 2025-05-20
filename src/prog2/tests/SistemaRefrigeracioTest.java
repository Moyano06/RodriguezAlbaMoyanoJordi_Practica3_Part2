package prog2.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2.model.VariableUniforme;
import prog2.model.bitacola.PaginaIncidencies;
import prog2.model.components.BombaRefrigerant;
import prog2.model.components.SistemaRefrigeracio;
import prog2.vista.CentralUBException;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaRefrigeracioTest {

    private SistemaRefrigeracio sistema;

    @BeforeEach
    public void inicialitzaSistema() {
        sistema = new SistemaRefrigeracio();
        VariableUniforme variable = new VariableUniforme(123L);

        // Afegim 4 bombes refrigerants
        for (int i = 0; i < 4; i++) {
            sistema.afegirBomba(new BombaRefrigerant(variable, i));
        }
    }

    @Test
    public void testSistemaActiuInicialment() {
        assertTrue(sistema.getActivat(), "El sistema ha d'estar actiu inicialment");
    }

    @Test
    public void testSistemaAmbTotesBombesActives() throws CentralUBException {
        PaginaIncidencies p = new PaginaIncidencies(1);
        sistema.revisa(p);
        assertTrue(sistema.getActivat(), "El sistema hauria de romandre actiu si totes les bombes estan activades");
    }

    @Test
    public void testSistemaAmbTotesBombesDesactivades() throws CentralUBException {
        for (BombaRefrigerant b : sistema.getBombaRefrigerants()) {
            b.desactiva();
        }

        PaginaIncidencies p = new PaginaIncidencies(1);
        assertFalse(sistema.getActivat(), "El sistema s'hauria de desactivar si totes les bombes estan desactivades");
    }

    @Test
    public void testSistemaAmbUnaBombaActiva() throws CentralUBException {
        for (int i = 0; i < 4; i++) {
            if (i != 1) sistema.getBombaRefrigerants().get(i).desactiva();
        }

        PaginaIncidencies p = new PaginaIncidencies(1);
        sistema.revisa(p);
        assertTrue(sistema.getActivat(), "El sistema hauria de seguir actiu si hi ha almenys una bomba activa");
    }

    @Test
    public void testCostOperatiuMajorQueZero() {
        float cost = sistema.getCostOperatiu();
        assertTrue(cost > 0, "El cost operatiu hauria de ser major que 0 quan el sistema està actiu");
    }

    @Test
    public void testCalculOutputNoSuperaInput() {
        float input = 300;
        float output = sistema.calculaOutput(input);
        assertTrue(output <= input, "El valor de sortida no pot superar l'entrada");
    }
}
