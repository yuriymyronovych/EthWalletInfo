package yuriymyronovych.ethwalletinfo.screen.details;

import java.math.BigDecimal;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import yuriymyronovych.ethwalletinfo.Constants;
import yuriymyronovych.ethwalletinfo.NetworkServiceFactory;
import yuriymyronovych.ethwalletinfo.Utils;
import yuriymyronovych.ethwalletinfo.service.IEtherscanService;
import yuriymyronovych.ethwalletinfo.service.IShapeShiftService;

/**
 * Created by ymyronov on 09/01/2018.
 */

public class DetailsActivityPresenter {
    private IEtherscanService etherscanService;
    private IShapeShiftService shapeShiftService;

    public DetailsActivityPresenter() {
        etherscanService = NetworkServiceFactory.provideEtherscanService();
        shapeShiftService = NetworkServiceFactory.provideShapeShiftService();
    }

    public Observable<DetailsInfoModel> getTokenDetails() {
        return Observable.zip(
                etherscanService.getAccountTokenBalance(Constants.ETH_ACCOUNT, Constants.GNT_ADDRESS),
                etherscanService.getAccountTokenBalance(Constants.ETH_ACCOUNT, Constants.REP_ADDRESS),
                etherscanService.getAccountTokenBalance(Constants.ETH_ACCOUNT, Constants.OMG_ADDRESS),
                shapeShiftService.getMarketInfo(Constants.PAIR_GNT_ETH),
                shapeShiftService.getMarketInfo(Constants.PAIR_REP_ETH),
                shapeShiftService.getMarketInfo(Constants.PAIR_OMG_ETH),

                (gnt, rep, omg, gntPrice, repPrice, omgPrice) -> {
                    BigDecimal gntNumber = Utils.weiToEth(gnt.getResult());
                    BigDecimal repNumber = Utils.weiToEth(rep.getResult());
                    BigDecimal omgNumber = Utils.weiToEth(omg.getResult());

                    BigDecimal gntEth = gntNumber.multiply(gntPrice.getRate());
                    BigDecimal repEth = repNumber.multiply(repPrice.getRate());
                    BigDecimal omgEth = omgNumber.multiply(omgPrice.getRate());

                    DetailsInfoModel info = new DetailsInfoModel();
                    info.setGntNumber(gntNumber);
                    info.setRepNumber(repNumber);
                    info.setOmgNumber(omgNumber);
                    info.setGntEth(gntEth);
                    info.setRepEth(repEth);
                    info.setOmgEth(omgEth);

                    return info;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
