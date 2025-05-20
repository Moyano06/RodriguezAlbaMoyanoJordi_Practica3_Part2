package prog2.model.components;

import prog2.model.bitacola.PaginaIncidencies;
import prog2.vista.CentralUBException;

import java.io.Serializable;

public class GeneradorVapor implements InComponent, Serializable {

    private boolean foraDeServei;
    private boolean activat;

    public GeneradorVapor() {
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
        System.out.println("El generador de vapor no es pot desactivar");
    }


    public boolean getActivat() {
        return activat;
    }

    // El generador de vapor no té incidències possibles
    public void revisa(PaginaIncidencies p) throws CentralUBException{}


    public float getCostOperatiu() {
        if (activat) {
            return 25;
        }
        else{
            return 0;
        }
    }


    public float calculaOutput(float input) {
        if (activat){
            return (float)(input * 0.9) ;
        }
        else{
            return 25;
        }
    }
}
