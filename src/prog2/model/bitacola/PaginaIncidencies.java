package prog2.model.bitacola;

import java.util.ArrayList;
import java.util.List;

public class PaginaIncidencies extends PaginaBitacola {

    private List<String> Incidencies;

    public PaginaIncidencies(int dia) {
        super(dia);
        Incidencies = new ArrayList<>();
    }

    public List<String> getIncidencies() {
        return Incidencies;
    }

    public void afegeixIncidencia(String descIncidencia) {
        Incidencies.add(descIncidencia);
    }

    public String toString() {
        String stringFinal;
        stringFinal = " # Pàgina Incidències\n" + "- Dia: " + super.getDia() + "\n";
        if (Incidencies.size() == 0) {
            stringFinal += "- No hi ha incidències.\n";
        }
        for (String s : Incidencies) {
            stringFinal += "- Descripció Incidència: " + s + "\n";
        }
        return stringFinal;
    }
}
