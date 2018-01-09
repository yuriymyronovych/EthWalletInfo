package yuriymyronovych.ethwalletinfo;

import java.math.BigDecimal;

/**
 * Created by ymyronov on 08/01/2018.
 */

public class Utils {
    private static final BigDecimal weiToEthRatio = new BigDecimal(1).scaleByPowerOfTen(18); //10^18

    public static BigDecimal weiToEth(BigDecimal wei) {
        return wei.divide(weiToEthRatio);
    }
}
