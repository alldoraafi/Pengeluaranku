package com.example.pengeluaranku;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by AR-Laptop on 11/26/2017.
 */

public class PengeluaranViewHolder extends RecyclerView.ViewHolder {
    public Button btn_delete;
    public TextView tv_nama, tv_deskripsi, tv_harga;
    public PengeluaranViewHolder(View itemView) {
        super(itemView);
        tv_nama=(TextView) itemView.findViewById(R.id.tv_nama);
        tv_deskripsi=(TextView) itemView.findViewById(R.id.tv_deskripsi);
        tv_harga=(TextView) itemView.findViewById(R.id.tv_harga);
        btn_delete =(Button) itemView.findViewById(R.id.btn_delete);
    }
}
