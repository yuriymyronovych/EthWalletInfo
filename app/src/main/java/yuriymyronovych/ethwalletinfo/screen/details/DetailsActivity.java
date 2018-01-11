package yuriymyronovych.ethwalletinfo.screen.details;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import yuriymyronovych.ethwalletinfo.R;
import yuriymyronovych.ethwalletinfo.ViewUtils;

/**
 * Created by ymyronov on 09/01/2018.
 */

public class DetailsActivity extends AppCompatActivity {
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_gnt_number) TextView tvGntNumber;
    @BindView(R.id.tv_rep_number) TextView tvRepNumber;
    @BindView(R.id.tv_omg_number) TextView tvOmgNumber;

    @BindView(R.id.tv_gnt_eth) TextView tvGntEth;
    @BindView(R.id.tv_rep_eth) TextView tvRepEth;
    @BindView(R.id.tv_omg_eth) TextView tvOmgEth;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DetailsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        presenter = new DetailsActivityPresenter();

        swipeRefreshLayout.setOnRefreshListener(() -> fetchData());
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    private void fetchData() {
        swipeRefreshLayout.setRefreshing(true);
        compositeDisposable.add(presenter.getTokenDetails()
                .subscribe(info -> onDataLoaded(info), error -> notifyError(error)));
    }

    private void onDataLoaded(DetailsInfoModel info) {
        swipeRefreshLayout.setRefreshing(false);
        ViewUtils.formatTokenNumber(tvGntNumber, info.getGntNumber());
        ViewUtils.formatTokenNumber(tvRepNumber, info.getRepNumber());
        ViewUtils.formatTokenNumber(tvOmgNumber, info.getOmgNumber());

        ViewUtils.formatEthPrice(tvGntEth, info.getGntEth());
        ViewUtils.formatEthPrice(tvRepEth, info.getRepEth());
        ViewUtils.formatEthPrice(tvOmgEth, info.getOmgEth());
    }

    private void notifyError(Throwable error) {
        swipeRefreshLayout.setRefreshing(false);
        ViewUtils.displayError(this, error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
