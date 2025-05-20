package prog2.model.components;

import prog2.model.bitacola.PaginaIncidencies;
import prog2.vista.CentralUBException;

import java.io.Serializable;

public class Reactor implements InComponent, Serializable {
    private float temperaturaActual;
    private boolean foraDeServei;
    private boolean activat;
  
    public Reactor() {
        foraDeServei = false;
        activat = true;
        temperaturaActual = 25;
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
        this.activat = false;
    }


    public boolean getActivat() {
        return activat;
    }

    public boolean getForaDeServei() {
        return foraDeServei;
    }


    public void revisa(PaginaIncidencies p) throws CentralUBException {
        if (temperaturaActual >1000){
            System.out.println("La temperatura supera els 1000 graus per tant el reactor d'ha de desactivar i es queda fora de servei");
            desactiva();
            foraDeServei = true;
        }
        else if (temperaturaActual <1000 && !activat){
            foraDeServei = false;
            activa();

        }
    }


    public float getCostOperatiu() {
        if (activat) {
            return 35;
        }
        else{
            return 0;
        }
    }


    public float calculaOutput(float input) {
        if (activat){
            return temperaturaActual + ((100 - input)*10);
        }
        else{
            return temperaturaActual;
        }
    }

    public void setTemperaturaActual(float temperaturaActual) {
        this.temperaturaActual = temperaturaActual;
    }

    public float getTemperaturaActual() {
        return temperaturaActual;
    }
}
