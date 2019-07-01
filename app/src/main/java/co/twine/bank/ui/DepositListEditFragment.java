package co.twine.bank.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import co.twine.bank.R;
import co.twine.bank.adapter.DepositItemsRecyclerViewAdapter;
import co.twine.bank.controller.MainPresenterImpl;
import co.twine.bank.listener.MainView;
import co.twine.bank.model.Deposits;

public class DepositListEditFragment extends Fragment implements MainView {

    private DepositItemsRecyclerViewAdapter mDepositItemsRecyclerViewAdapter;

    private RecyclerView recyclerView;
    private TextView mStatus;
    private LinearLayout mDepositsHeader;
    private TextView mMonthlyTotal;
    private MainPresenterImpl mMainPresenterImpl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.recurring_deposits_edit_view,
                container,
                false);

        mDepositsHeader = rootView.findViewById(R.id.header);
        mMonthlyTotal = rootView.findViewById(R.id.header_value);
        mStatus = rootView.findViewById(R.id.view_status);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        mMainPresenterImpl = new MainPresenterImpl(this);
        setupRecyclerView(recyclerView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mMainPresenterImpl.onAttach();
        prepareForNewList();
        mMainPresenterImpl.getDepositsList();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMainPresenterImpl.prepareToExit();
        mMainPresenterImpl.onDetach();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        DividerItemDecoration divider = new DividerItemDecoration(
                recyclerView.getContext(),
                DividerItemDecoration.VERTICAL
        );
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.list_divider));
        recyclerView.addItemDecoration(divider);
        mDepositItemsRecyclerViewAdapter = new DepositItemsRecyclerViewAdapter(getContext(), null);
        recyclerView.setAdapter(mDepositItemsRecyclerViewAdapter);
    }

    @Override
    public void addItemsToList(@NonNull List<Deposits> results) {
        if (results != null && results.size() > 0) {
            mDepositsHeader.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            mStatus.setVisibility(View.GONE);
            mDepositItemsRecyclerViewAdapter.setDepositsItems(results);
            mDepositItemsRecyclerViewAdapter.notifyDataSetChanged();
        } else {
            recyclerView.setVisibility(View.GONE);
            mStatus.setVisibility(View.VISIBLE);
            mStatus.setText(R.string.loading_status_error);
        }
    }

    @Override
    public void showEmptyList() {
        mDepositsHeader.setVisibility(View.GONE);
        mStatus.setVisibility(View.VISIBLE);
        mStatus.setText(R.string.no_results);
    }

    @Override
    public void prepareForNewList() {
        mStatus.setVisibility(View.VISIBLE);
        mStatus.setText(R.string.loading_status);
    }

    @Override
    public void updateMonthlyTotal(String amount) {
        if (!TextUtils.isEmpty(amount)) {
            mDepositsHeader.setVisibility(View.VISIBLE);
            mMonthlyTotal.setText(amount);
        }
    }
}
