package yuriymyronovych.ethwalletinfo.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import yuriymyronovych.ethwalletinfo.data.MarketInfoResponse;

/**
 * Created by ymyronov on 08/01/2018.
 */

public interface IShapeShiftService {
    @GET("marketinfo/{pair}")
    Observable<MarketInfoResponse> getMarketInfo(@Path("pair") String pair);
}
