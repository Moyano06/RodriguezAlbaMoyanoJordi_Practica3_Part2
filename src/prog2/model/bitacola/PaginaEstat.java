package prog2.model.bitacola;

public class PaginaEstat extends PaginaBitacola{

    private float insercioBarres;    //recordem que ha de ser un percentatge
    private float outputReactor;
    private float outputSisRefrigeracio;
    private float outputGeneradorVapor;
    private float outputTurbina;

    public PaginaEstat(int dia, float insercioBarres, float outputReactor, float outputSisRefrigeracio,
                       float outputGeneradorVapor, float outputTurbina) {
        super(dia);
        this.insercioBarres = insercioBarres;
        this.outputReactor = outputReactor;
        this.outputSisRefrigeracio = outputSisRefrigeracio;
        this.outputGeneradorVapor = outputGeneradorVapor;
        this.outputTurbina = outputTurbina;
    }

    public float getInsercioBarres() {
        return insercioBarres;
    }

    public float getOutputReactor() {
        return outputReactor;
    }

    public float getOutputSisRefrigeracio() {
        return outputSisRefrigeracio;
    }

    public float getOutputGeneradorVapor() {
        return outputGeneradorVapor;
    }

    public float getOutputTurbina() {
        return outputTurbina;
    }

    public void setInsercioBarres(float insercioBarres) {
        this.insercioBarres = insercioBarres;
    }

    public void setOutputReactor(float outputReactor) {
        this.outputReactor = outputReactor;
    }

    public void setOutputSisRefrigeracio(float outputSisRefrigeracio) {
        this.outputSisRefrigeracio = outputSisRefrigeracio;
    }

    public void setOutputGeneradorVapor(float outputGeneradorVapor) {
        this.outputGeneradorVapor = outputGeneradorVapor;
    }

    public void setOutputTurbina(float outputTurbina) {
        this.outputTurbina = outputTurbina;
    }

    public String toString() {
        return "# Pàgina Estat\n" +
                "- Dia: " + super.getDia() + "\n" +
                "- Inserció Barres: " + insercioBarres + " %\n" +
                "- Output Reactor: " + outputReactor + " Graus\n" +
                "- Output Sistema de Refrigeració: " + outputSisRefrigeracio + " Graus\n" +
                "- Output Generador de Vapor: " + outputGeneradorVapor + " Graus\n" +
                "- Output prog2.model.components.Turbina: " + outputTurbina + " Unitats de Potència\n";
    }
}
