package yuriymyronovych.ethwalletinfo.data;

import java.math.BigDecimal;

/**
 * Created by ymyronov on 08/01/2018.
 */

public class MarketInfoResponse {
    private String pair;
    private BigDecimal rate;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketInfoResponse that = (MarketInfoResponse) o;

        if (pair != null ? !pair.equals(that.pair) : that.pair != null) return false;
        return rate != null ? rate.equals(that.rate) : that.rate == null;
    }

    @Override
    public int hashCode() {
        int result = pair != null ? pair.hashCode() : 0;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }
}
