package com.example.pengeluaranku;

import android.provider.BaseColumns;

/**
 * Created by AR-Laptop on 11/26/2017.
 */

public class DatabaseContract {
    public static final class PengeluaranEntry implements BaseColumns{
        public static final String TABLE_NAME = "pengeluaran_table";
        public static final String COLUMN_NAMA = "nama";
        public static final String COLUMN_DESKRIPSI = "deskripsi";
        public static final String COLUMN_HARGA = "harga";
    }
}
