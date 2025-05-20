package prog2.model.bitacola;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bitacola implements InBitacola, Serializable {

    private ArrayList<PaginaBitacola> paginesBitacola;

    public Bitacola() {

        this.paginesBitacola = new ArrayList<>();
    }

    public void afegeixPagina(PaginaBitacola p) {
        this.paginesBitacola.add(p);
    }

    public List<PaginaIncidencies> getIncidencies() {
        List<PaginaIncidencies> paginesIncidencies = new ArrayList<PaginaIncidencies>();
        for (PaginaBitacola p : paginesBitacola) {
            if (p instanceof PaginaIncidencies) {
                paginesIncidencies.add((PaginaIncidencies) p);
            }
        }
        return paginesIncidencies;
    }

    public String toString(){
        String pagines = "";
        if (paginesBitacola.isEmpty()) {
            return "No hi ha pàgines a la bitàcola\n";
        } else {
            for (PaginaBitacola p : paginesBitacola) {
                pagines += p.toString();
            }
        }
        return pagines;
    }
    public ArrayList<PaginaBitacola> getPaginesBitacola() {
        return paginesBitacola;
    }
}


