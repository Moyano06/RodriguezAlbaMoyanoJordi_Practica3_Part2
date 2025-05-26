package prog2.adaptador;

import prog2.model.Dades;
import prog2.model.components.BombaRefrigerant;
import prog2.vista.CentralUBException;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Adaptador {
    private Dades dades;
    public Adaptador() throws CentralUBException {
        this.dades = new Dades();
    }

    public void guardaDades(String camiDesti) throws CentralUBException{
        this.dades = new Dades();
        File fitxer=new File(camiDesti);
        // Obrim un Stream de sortida cap al fitxer
        try {
            FileOutputStream fout= new FileOutputStream(fitxer);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(dades);
            oos.close();
            fout.close();
        } catch (Exception e) {
            throw new CentralUBException("Error a l'obrir el fitxer.");
        }
    }

    public void carregaDades(String camiOrigen)throws CentralUBException{
        this.dades = new Dades();
        File fitxer=new File(camiOrigen);
        // Obrim un Stream d’entrada des del fitxer
        try {
            FileInputStream fin=new FileInputStream(fitxer);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Dades dades = (Dades)ois.readObject();
            ois.close();
            fin.close();
            System.out.println(dades.mostraBitacola().toString());
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new CentralUBException("Error a l'obrir el fitxer.");
        }
    }

    public String finalitzaDia(float demandaPotencia) throws CentralUBException{
        return this.dades.finalitzaDia(demandaPotencia).toString();
    }

    public float getInsersioBarres() {
        return dades.getInsercioBarres();
    }

    public void setInsersioBarres(float insersioBarres) throws CentralUBException{
        this.dades.setInsercioBarres(insersioBarres);
    }

    public void activaReactor() throws CentralUBException {
        dades.activaReactor();
    }

    public void desactivaReactor() {
        dades.desactivaReactor();
    }

    public String mostraEstatReactor() {
        // Ha de mostrar si el reactor esta activat i la seva temperatura
        return "L'estat del reactor és:\nActivat = " + dades.mostraReactor().getActivat() + "\nTemperatura = " +
                dades.mostraReactor().getTemperaturaActual() + " graus Celsius\n";
    }

    public void activaTotesBombes() throws CentralUBException {
        dades.mostraSistemaRefrigeracio().activa();
    }

    public void desactivaTotesBombes() {
        dades.mostraSistemaRefrigeracio().desactiva();
    }

    public void activaBomba(int id) throws CentralUBException {
        dades.activaBomba(id);
    }

    public void desactivaBomba(int id) throws CentralUBException {
        dades.desactivaBomba(id);
    }

    public String mostraEstatBombes() {
        String bombes = "L'estat de les bombes refrigerants és:\n";
        for (BombaRefrigerant b : dades.mostraSistemaRefrigeracio().getBombaRefrigerants()) {
            bombes += b.toString() + "\n";
        }
        return bombes;
    }

    public String mostraEstatCentral() {
        return dades.mostraEstat().toString();
    }

    public String mostraBitacola() {
        return dades.mostraBitacola().toString();
    }

    public String mostraTotesIncidencies() {
        return dades.mostraIncidencies().toString();
    }

    public String demandaSatisfeta() {
        Pattern pattern = Pattern.compile("- Demanda de Potència Satisfeta:.*?%\\n");
        Matcher matcher = pattern.matcher(dades.mostraBitacola().toString());

        if (matcher.find()) {
            return matcher.group();
        } else {
            return "No s'han trobat pagines a la bitàcola\n";
        }
    }

    public String mostraEstatDemandaActual(float demandaPotencia) {
        float potenciaGenerada = dades.calculaPotencia();
        float percentatge = (demandaPotencia > 0) ? (potenciaGenerada / demandaPotencia) * 100 : 0;

        return "Demanda: " + demandaPotencia + " kW\n" +
                "Potència: " + potenciaGenerada + " kW\n" +
                "Percentatge: " + (potenciaGenerada / demandaPotencia) * 100 + "%\n";
    }
    public int getDia(){
        return dades.getDia();
    }
    public float getGuanysAcumulats(){
        return dades.getGuanysAcumulats();
    }
}
