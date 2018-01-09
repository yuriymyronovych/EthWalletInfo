package yuriymyronovych.ethwalletinfo.service;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yuriymyronovych.ethwalletinfo.Constants;
import yuriymyronovych.ethwalletinfo.data.AccountBalanceResponse;

/**
 * Created by ymyronov on 08/01/2018.
 */

public interface IEtherscanService {
    @GET("api?module=account&action=balance&tag=latest&apikey=" + Constants.ETHERSCAN_API_KEY)
    Observable<AccountBalanceResponse> getAccountBalance(@Query("address") String address);

    @GET("api?module=account&action=tokenbalance&tag=latest&apikey=" + Constants.ETHERSCAN_API_KEY)
    Observable<AccountBalanceResponse> getAccountTokenBalance(@Query("address") String address, @Query("contractaddress") String contractAddress);
}
