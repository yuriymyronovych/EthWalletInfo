package yuriymyronovych.ethwalletinfo.screen.main;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import yuriymyronovych.ethwalletinfo.screen.details.DetailsActivity;
import yuriymyronovych.ethwalletinfo.R;
import yuriymyronovych.ethwalletinfo.ViewUtils;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_account_balance) TextView tvAccountBalance;
    @BindView(R.id.tv_token_balance) TextView tvTokenBalance;

    @BindView(R.id.btn_details) Button btnDetails;


    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MainActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainActivityPresenter();

        swipeRefreshLayout.setOnRefreshListener(() -> fetchData());
        btnDetails.setOnClickListener(click -> startActivity(new Intent(this, DetailsActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    private void fetchData() {
        swipeRefreshLayout.setRefreshing(true);
        compositeDisposable.add(
                Observable.zip(
                    presenter.getAccountBalanceETH(),
                    presenter.getTokenBalanceETH(),
                    (accountBalanceEth, tokenBalanceEth) -> {
                        onDataLoaded(accountBalanceEth, tokenBalanceEth);
                        return "";
                    })
                    .subscribe(aVoid -> {}, error -> notifyError(error)));
    }

    private void onDataLoaded(BigDecimal accountBalanceEth, BigDecimal tokenBalanceEth) {
        swipeRefreshLayout.setRefreshing(false);

        ViewUtils.formatEthPrice(tvAccountBalance, accountBalanceEth);
        ViewUtils.formatEthPrice(tvTokenBalance, tokenBalanceEth);

        if (tokenBalanceEth.doubleValue() == -1) {
            Toast.makeText(this, R.string.shape_shift_broken, Toast.LENGTH_LONG).show();
        }
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
