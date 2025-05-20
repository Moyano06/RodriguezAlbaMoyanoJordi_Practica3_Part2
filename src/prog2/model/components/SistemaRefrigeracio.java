package prog2.model.components;

import prog2.model.bitacola.PaginaIncidencies;
import prog2.vista.CentralUBException;

import java.io.Serializable;
import java.util.ArrayList;

public class SistemaRefrigeracio implements InComponent, Serializable {
    private ArrayList<BombaRefrigerant> bombaRefrigerants;
    private boolean foraDeServei;
    private boolean activat;

    public SistemaRefrigeracio(){
        bombaRefrigerants = new ArrayList<BombaRefrigerant>();
        foraDeServei = false;
        activat = true;
    }

    public ArrayList<BombaRefrigerant> getBombaRefrigerants() {
        return bombaRefrigerants;
    }

    public void revisa(PaginaIncidencies p) throws CentralUBException {
        for (BombaRefrigerant b : bombaRefrigerants) {
            b.revisa(p);
        }

        boolean algunaActiva = getActivat();

        if (algunaActiva && !activat) {
            activa();
        } else if (!algunaActiva && activat) {
            desactiva();
        }
    }


    public float getCostOperatiu() {
        if (activat){
            int costTotal = 0;
            for (BombaRefrigerant b : bombaRefrigerants) { costTotal += b.getCostOperatiu(); }
            return costTotal;
        }
        else{
            return 0;
        }
    }
  
    public void afegirBomba(BombaRefrigerant b){
        bombaRefrigerants.add(b);
    }

    public void activa() throws CentralUBException {
        if (foraDeServei){
            throw new CentralUBException("Esta fora de servei, no es pot activar");
        }
        else{
            this.activat = true;
            for (BombaRefrigerant b : bombaRefrigerants) {b.activa();}
        }
    }

    public void desactiva() {
        this.activat = false;
        for (BombaRefrigerant b : bombaRefrigerants) {b.desactiva();}
    }

    public boolean getActivat() {
        boolean Activada1 = false;
        int suma = 0;
        for (BombaRefrigerant b : bombaRefrigerants) {
            if (b.getActivat()){
                Activada1 = true;
                suma += 1;
                 }
        }
        if (Activada1){
            System.out.println("Hi han "+ suma+ " bombes refrigerants activades");
            return true;
        }else{
            System.out.println("No hi han bombes activades");
            return false;
        }

    }
  
    public float calculaOutput(float input) {
            int grausTotal = 0;
            for (BombaRefrigerant b : bombaRefrigerants) {
                grausTotal += b.getCapacitat(); }
            return Math.min(input, grausTotal);

    }
}
