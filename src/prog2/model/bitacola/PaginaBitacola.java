package prog2.model.bitacola;

public abstract class PaginaBitacola {

    private int dia;

    public PaginaBitacola(int dia) {
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public abstract String toString();
}
