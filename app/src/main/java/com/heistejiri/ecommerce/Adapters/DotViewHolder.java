package com.heistejiri.ecommerce.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.heistejiri.ecommerce.R;


/**
 * Created by Android
 */
public class DotViewHolder extends RecyclerView.ViewHolder {

    ImageView dotImageView;

    public DotViewHolder(final Context context, View itemView) {
        super(itemView);
        dotImageView = (ImageView) itemView.findViewById(R.id.dotImageView);

    }
}
