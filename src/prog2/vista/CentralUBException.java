package prog2.vista;

public class CentralUBException extends Exception{

    public CentralUBException(String message) {
        super(message);
    }

    public CentralUBException() {
        super("S'ha produit un error.");
    }

}