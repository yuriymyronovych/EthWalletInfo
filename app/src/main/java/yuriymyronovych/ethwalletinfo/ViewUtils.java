package yuriymyronovych.ethwalletinfo;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by ymyronov on 09/01/2018.
 */

public class ViewUtils {
    public static void formatEthPrice(TextView tv, BigDecimal price) {
        price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
        tv.setText(price + " " + tv.getContext().getString(R.string.ETH));
    }

    public static void formatTokenNumber(TextView tv, BigDecimal number) {
        number = number.setScale(2, BigDecimal.ROUND_HALF_UP);
        tv.setText(number.toString());
    }

    public static void displayError(Context ctx, Throwable error) {
        error.printStackTrace();
        if (error instanceof IOException) {
            Toast.makeText(ctx, R.string.network_connection_problem, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ctx, R.string.an_error_occured, Toast.LENGTH_LONG).show();
        }
    }
}
