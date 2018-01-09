package yuriymyronovych.ethwalletinfo.data;

import java.math.BigDecimal;

/**
 * Created by ymyronov on 08/01/2018.
 */

public class AccountBalanceResponse {
    private int status;
    private String message;
    private BigDecimal result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountBalanceResponse that = (AccountBalanceResponse) o;

        if (status != that.status) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return result != null ? result.equals(that.result) : that.result == null;
    }

    @Override
    public int hashCode() {
        int result1 = status;
        result1 = 31 * result1 + (message != null ? message.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
