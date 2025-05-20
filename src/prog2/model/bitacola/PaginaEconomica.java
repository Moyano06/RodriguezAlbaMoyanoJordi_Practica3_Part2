package prog2.model.bitacola;

public class PaginaEconomica extends PaginaBitacola {

    private float potenciaDemanda;
    private float potenciaGenerada;
    private float potenciaSatisfeta;    //recordem que ha de ser un percentatge
    private float beneficis;
    private float penalitzacio;
    private float costOperatiu;
    private float guanysAcumulats;

    public PaginaEconomica(int dia, float potenciaDemanda, float potenciaGenerada) {
        super(dia);
        this.potenciaDemanda = potenciaDemanda;
        this.potenciaGenerada = potenciaGenerada;
    }

    public float getPotenciaDemanda() {
        return potenciaDemanda;
    }

    public float getPotenciaGenerada() {
        return potenciaGenerada;
    }

    public float getPotenciaSatisfeta() {
        return potenciaSatisfeta;
    }

    public float getBeneficis() {
        return beneficis;
    }

    public float getPenalitzacio() {
        return penalitzacio;
    }

    public float getCostOperatiu() {
        return costOperatiu;
    }

    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    public void setPotenciaDemanda(float potenciaDemanda) {
        this.potenciaDemanda = potenciaDemanda;
    }

    public void setPotenciaGenerada(float potenciaGenerada) {
        this.potenciaGenerada = potenciaGenerada;
    }

    public void setPotenciaSatisfeta(float potenciaSatisfeta) {
        this.potenciaSatisfeta = potenciaSatisfeta;
    }

    public void setBeneficis(float beneficis) {
        this.beneficis = beneficis;
    }

    public void setPenalitzacio(float penalitzacio) {
        this.penalitzacio = penalitzacio;
    }

    public void setCostOperatiu(float costOperatiu) {
        this.costOperatiu = costOperatiu;
    }

    public void setGuanysAcumulats(float guanysAcumulats) {
        this.guanysAcumulats = guanysAcumulats;
    }

    public String toString() {
        return "# Pàgina Econòmica\n" +
                "- Dia: " + super.getDia() + "\n" +
                "- Demanda de Potència: " + potenciaDemanda + "\n" +
                "- Potència Generada: " + potenciaGenerada + "\n" +
                "- Demanda de Potència Satisfeta: " + potenciaSatisfeta + " %\n" +
                "- Beneficis: " + beneficis + " Unitats Econòmiques\n" +
                "- Penalització Excés Producció: " + penalitzacio + " Unitats Econòmiques\n" +
                "- Cost Operatiu: " + costOperatiu + " Unitats Econòmiques\n" +
                "- Guanys acumulats: " + guanysAcumulats + " Unitats Econòmiques\n";
    }
}
