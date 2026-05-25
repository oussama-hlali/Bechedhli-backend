package app.bechedhli.solar.exceptions;

public class ContratNotFoundException extends RuntimeException {
    public ContratNotFoundException(Long id) {
        super("Contrat not found with id: " + id);
    }
}