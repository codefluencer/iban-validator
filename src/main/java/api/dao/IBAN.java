package api.dao;

public class IBAN {

    private String iban;
    private boolean isValid;

    public IBAN(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
