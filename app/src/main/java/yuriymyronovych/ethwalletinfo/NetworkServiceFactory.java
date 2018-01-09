package yuriymyronovych.ethwalletinfo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yuriymyronovych.ethwalletinfo.service.IEtherscanService;
import yuriymyronovych.ethwalletinfo.service.IShapeShiftService;

/**
 * Created by ymyronov on 08/01/2018.
 */

public class NetworkServiceFactory {
    public static IEtherscanService provideEtherscanService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ETHERSCAN_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build();

        return retrofit.create(IEtherscanService.class);
    }

    public static IShapeShiftService provideShapeShiftService() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SHAPESHIFT_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build();

        return retrofit.create(IShapeShiftService.class);
    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }
}
