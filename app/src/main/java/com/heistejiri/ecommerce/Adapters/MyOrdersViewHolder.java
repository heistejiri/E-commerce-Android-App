package com.heistejiri.ecommerce.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.heistejiri.ecommerce.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Android
 */
public class MyOrdersViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.orderedProductsRecyclerView)
    RecyclerView orderedProductsRecyclerView;
    @BindView(R.id.viewOrderDetails)
    TextView viewOrderDetails;
    @BindView(R.id.date)
    TextView date;


    public MyOrdersViewHolder(final Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
