package co.twine.bank.network;

import co.twine.bank.model.DepositsBaseResponse;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Class to fetch Deposits List's
 */
public interface DepositsApi {

    @GET("nexus65432/a5aa2ff64afc9bcb1fa163ed5f9714b7/raw/1ca43d40fb36dfb5278e3c7b489e5423e1bab5bc/test")
    Single<DepositsBaseResponse> getDeposits();
}
