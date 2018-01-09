package yuriymyronovych.ethwalletinfo.screen.main;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import yuriymyronovych.ethwalletinfo.Constants;
import yuriymyronovych.ethwalletinfo.NetworkServiceFactory;
import yuriymyronovych.ethwalletinfo.Utils;
import yuriymyronovych.ethwalletinfo.service.IEtherscanService;
import yuriymyronovych.ethwalletinfo.service.IShapeShiftService;

/**
 * Created by ymyronov on 08/01/2018.
 */

public class MainActivityPresenter {
    private IEtherscanService etherscanService;
    private IShapeShiftService shapeShiftService;

    public MainActivityPresenter() {
        etherscanService = NetworkServiceFactory.provideEtherscanService();
        shapeShiftService = NetworkServiceFactory.provideShapeShiftService();
    }

    public Observable<BigDecimal> getAccountBalanceETH() {
        return etherscanService.getAccountBalance(Constants.ETH_ACCOUNT)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(response -> Utils.weiToEth(response.getResult()));
    }

    public Observable<BigDecimal> getTokenBalanceETH() {
        return Observable.zip(
                etherscanService.getAccountTokenBalance(Constants.ETH_ACCOUNT, Constants.GNT_ADDRESS),
                etherscanService.getAccountTokenBalance(Constants.ETH_ACCOUNT, Constants.REP_ADDRESS),
                etherscanService.getAccountTokenBalance(Constants.ETH_ACCOUNT, Constants.OMG_ADDRESS),
                shapeShiftService.getMarketInfo(Constants.PAIR_GNT_ETH),
                shapeShiftService.getMarketInfo(Constants.PAIR_REP_ETH),
                shapeShiftService.getMarketInfo(Constants.PAIR_OMG_ETH),

                (gnt, rep, omg, gntPrice, repPrice, omgPrice) -> {
                    BigDecimal gntEth = Utils.weiToEth(gnt.getResult()).multiply(gntPrice.getRate());
                    BigDecimal repEth = Utils.weiToEth(rep.getResult()).multiply(repPrice.getRate());
                    BigDecimal omgEth = Utils.weiToEth(omg.getResult()).multiply(omgPrice.getRate());
                    return gntEth.add(repEth).add(omgEth);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
