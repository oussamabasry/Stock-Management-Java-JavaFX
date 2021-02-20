package DAO;

import java.io.Serializable;

public class ConfirmationResponse implements Serializable {
    private boolean existenceAccount = false;
    private boolean valideAmount = false;

    public boolean isExistenceAccount() {
        return existenceAccount;
    }

    public boolean isValideAmount() {
        return valideAmount;
    }

    public void setExistenceAccount(boolean existenceAccount) {
        this.existenceAccount = existenceAccount;
    }

    public void setValideAmount(boolean valideAmount) {
        this.valideAmount = valideAmount;
    }
}
