package prog2.model.components;

import prog2.model.bitacola.PaginaIncidencies;
import prog2.vista.CentralUBException;

import java.io.Serializable;

public class Turbina implements InComponent, Serializable {
    private boolean foraDeServei;
    private boolean activat;

    public Turbina() {
        foraDeServei = false;
        activat = true;
    }
    public void activa() throws CentralUBException {
        if (foraDeServei){
            throw new CentralUBException("Esta fora de servei, no es pot activar");
        }
        else{
            this.activat = true;
        }
    }

    public void desactiva() {
        System.out.println("La turbina no es pot desactivar");
    }

    public boolean getActivat() {
        return activat;
    }

    // La turbina no té incidències possibles
    public void revisa(PaginaIncidencies p) {}


    public float getCostOperatiu() {
        if (activat) {
            return 20;
        }
        else{
            return 0;
        }
    }


    public float calculaOutput(float input) {
        if (activat && input >=100){
            return input*2;
        }
        else {
            return 0;
        }
    }
}
