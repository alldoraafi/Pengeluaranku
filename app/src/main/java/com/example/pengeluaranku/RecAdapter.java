package com.example.pengeluaranku;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by AR-Laptop on 11/26/2017.
 */

public class RecAdapter extends RecyclerView.Adapter<PengeluaranViewHolder> {
    private Cursor mCursor;
    private Context mContext;
    ItemAction mItemAction;

    public RecAdapter(Context mContext, ItemAction mItemAction) {
        this.mContext = mContext;
        this.mItemAction = mItemAction;
    }

    public void swapCursor(Cursor mCursor) {
        this.mCursor = mCursor;
    }

    @Override
    public PengeluaranViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pengeluaran_item, parent, false);
        return new PengeluaranViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PengeluaranViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        int namaColumnIndex = mCursor.getColumnIndex(DatabaseContract.PengeluaranEntry.COLUMN_NAMA);
        int deskripsiColumnIndex = mCursor.getColumnIndex(DatabaseContract.PengeluaranEntry.COLUMN_DESKRIPSI);
        int hargaColumnIndex = mCursor.getColumnIndex(DatabaseContract.PengeluaranEntry.COLUMN_HARGA);
        int idColumnIndex = mCursor.getColumnIndex(DatabaseContract.PengeluaranEntry._ID);

        final String id = mCursor.getString(idColumnIndex);
        String nama = mCursor.getString(namaColumnIndex);
        String deskripsi = mCursor.getString(deskripsiColumnIndex);
        String harga = mCursor.getString(hargaColumnIndex);

        holder.tv_nama.setText(nama);
        holder.tv_deskripsi.setText(deskripsi);
        holder.tv_harga.setText(harga);
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemAction.deletePengeluaran(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mCursor == null)return 0;
        return mCursor.getCount();
    }
}
