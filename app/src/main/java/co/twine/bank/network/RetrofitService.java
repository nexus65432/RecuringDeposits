package co.twine.bank.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import co.twine.bank.model.DepositsBaseResponse;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Rest interface to fetch data from server
 */
public class RetrofitService {

    private Retrofit mRetroFit;
    private DepositsApi mDepositsApi;

    private static final String DEPOSITS_API = "https://gist.githubusercontent.com/";

    private static class SingletonRetroFitServiceHelper {
        private static final RetrofitService INSTANCE = new RetrofitService();
    }

    public static RetrofitService getInstance() {
        return SingletonRetroFitServiceHelper.INSTANCE;
    }

    private RetrofitService() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setLenient()
                .create();
        mRetroFit = new Retrofit.Builder().baseUrl(DEPOSITS_API)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    public Single<DepositsBaseResponse> fetchFromBank() {
        mDepositsApi = mRetroFit.create(DepositsApi.class);
        return mDepositsApi.getDeposits();
    }
}
