package yuriymyronovych.ethwalletinfo.screen.details;

import java.math.BigDecimal;

/**
 * Created by ymyronov on 09/01/2018.
 */

public class DetailsInfoModel {
    private BigDecimal gntNumber;
    private BigDecimal repNumber;
    private BigDecimal omgNumber;

    private BigDecimal gntEth;
    private BigDecimal repEth;
    private BigDecimal omgEth;

    public BigDecimal getGntNumber() {
        return gntNumber;
    }

    public void setGntNumber(BigDecimal gntNumber) {
        this.gntNumber = gntNumber;
    }

    public BigDecimal getRepNumber() {
        return repNumber;
    }

    public void setRepNumber(BigDecimal repNumber) {
        this.repNumber = repNumber;
    }

    public BigDecimal getOmgNumber() {
        return omgNumber;
    }

    public void setOmgNumber(BigDecimal omgNumber) {
        this.omgNumber = omgNumber;
    }

    public BigDecimal getGntEth() {
        return gntEth;
    }

    public void setGntEth(BigDecimal gntEth) {
        this.gntEth = gntEth;
    }

    public BigDecimal getRepEth() {
        return repEth;
    }

    public void setRepEth(BigDecimal repEth) {
        this.repEth = repEth;
    }

    public BigDecimal getOmgEth() {
        return omgEth;
    }

    public void setOmgEth(BigDecimal omgEth) {
        this.omgEth = omgEth;
    }
}
