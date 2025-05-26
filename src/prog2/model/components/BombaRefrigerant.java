package prog2.model.components;

import prog2.model.VariableUniforme;
import prog2.model.bitacola.PaginaIncidencies;
import prog2.vista.CentralUBException;

import java.io.Serializable;

public class BombaRefrigerant implements InBombaRefrigerant, Serializable {
    private int id;
    private boolean activat;
    private boolean foraDeServei;
    private VariableUniforme variableUniforme;


    public BombaRefrigerant(VariableUniforme x, int id) {
        this.id = id;
        this.foraDeServei = false;
        this.activat = true;
        this.variableUniforme = x;

    }

    public int getId() {
        return this.id;
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

    public void revisa(PaginaIncidencies p) throws CentralUBException {
        if (variableUniforme.seguentValor() < 25) {
            desactiva();
            foraDeServei = true;
            p.afegeixIncidencia("La bomba refrigerant amb ID: " + getId() + " esta fora de servei");         }
        else {
            foraDeServei = false;

        }
    }

    public boolean getForaDeServei() {
        return foraDeServei;
    }

    public float getCapacitat() {
        if (activat) {
            return 250;
        }
        else{
            return 0;
        }
    }

    public float getCostOperatiu() {
        if (activat) {
            return 130;
        }
        else{
            return 0;
        }
    }
    public String toString (){
        return "Id=" + getId()+ ", Activat=" + getActivat() + ", Fora de servei:"+getForaDeServei();
    }
}
