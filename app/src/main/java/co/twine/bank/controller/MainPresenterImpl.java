package co.twine.bank.controller;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.twine.bank.listener.MainPresenter;
import co.twine.bank.listener.MainView;
import co.twine.bank.model.Deposits;
import co.twine.bank.model.DepositsBaseResponse;
import co.twine.bank.network.RetrofitService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * This class implements the logic to fetch data from server in background and update UI
 */
public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = "MainPresenterImpl";

    private CompositeDisposable mCompositeDisposable = null;
    private MainView mMainView;

    public MainPresenterImpl(@NonNull MainView view) {
        this.mMainView = view;
    }

    @Override
    public void onAttach() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void prepareToExit() {
        mCompositeDisposable.dispose();
    }

    @Override
    public void onDetach() {
        mCompositeDisposable = null;
    }

    @Override
    public void getDepositsList() {
        Log.d(TAG, "getDepositsList");
        mCompositeDisposable.add(RetrofitService.getInstance().fetchFromBank()
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(fetchDepositTasksList()));
    }

    private DisposableSingleObserver<DepositsBaseResponse> fetchDepositTasksList() {
        return new DisposableSingleObserver<DepositsBaseResponse>() {
            @Override
            public void onSuccess(DepositsBaseResponse value) {
                Log.d(TAG, "onSuccess ");

                if (value != null && value.getResults() != null) {
                    List<Deposits> mDepositsList = new ArrayList<>();
                    for (Deposits deposits : value.getResults().getDeposits()) {
                        // process if we need
                        mDepositsList.add(deposits);
                    }
                    mMainView.addItemsToList(mDepositsList);

                    if (!TextUtils.isEmpty(value.getTotalAmount())) {
                        mMainView.updateMonthlyTotal(value.getTotalAmount());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError ");
                mMainView.showEmptyList();
                //ToDo: This might be network error so differentiate Network error and Api Error
            }
        };
    }

}
