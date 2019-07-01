package co.twine.bank.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.twine.bank.R;

public class DepositViewHolder extends RecyclerView.ViewHolder {

    public final TextView mLabel;
    public final TextView mstatus;
    public final TextView mAction;

    public DepositViewHolder(View view) {
        super(view);
        mLabel = view.findViewById(R.id.list_label);
        mstatus = view.findViewById(R.id.list_state);
        mAction = view.findViewById(R.id.list_action);
    }

}
