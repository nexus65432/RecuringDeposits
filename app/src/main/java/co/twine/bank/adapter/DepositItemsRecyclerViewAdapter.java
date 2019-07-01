package co.twine.bank.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.twine.bank.R;
import co.twine.bank.model.DepositViewHolder;
import co.twine.bank.model.Deposits;

public class DepositItemsRecyclerViewAdapter extends RecyclerView.Adapter<DepositViewHolder> {

    private List<Deposits> mValues;
    private Context mContext;

    public DepositItemsRecyclerViewAdapter(Context context, List<Deposits> items) {
        mValues = items;
        mContext = context;
    }

    @Override
    public DepositViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new DepositViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DepositViewHolder holder, int position) {

        final Deposits deposits = mValues.get(position);

        if (deposits != null) {
            holder.mLabel.setText(deposits.getDepositTitle());
            holder.mstatus.setText(deposits.getStatus());
            if (TextUtils.equals(deposits.getStatus(), "paused")) {
                holder.mAction.setText(mContext.getString(R.string.deposit_type));
                holder.mAction.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
            }

            holder.mAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToDo: Activate Account
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mValues != null) {
            return mValues.size();
        } else {
            return 0;
        }
    }

    public void setDepositsItems(List<Deposits> items) {
        mValues = items;
    }
}
