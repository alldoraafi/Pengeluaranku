package com.example.pengeluaranku;

/**
 * Created by AR-Laptop on 11/26/2017.
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPengeluaran extends AppCompatActivity {

    private Button btn_simpan;
    private EditText edt_nama, edt_deskripsi, edt_harga;
    private View rootView;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pengeluaran);
        rootView = (View) findViewById(R.id.rootView);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Add Pengeluaran Activity");
        }

        dbHelper = new DatabaseHelper(this);
        mDb = dbHelper.getWritableDatabase();

        edt_nama = (EditText) findViewById(R.id.nama);
        edt_deskripsi = (EditText) findViewById(R.id.deskripsi);
        edt_harga = (EditText) findViewById(R.id.harga);
        btn_simpan = (Button) findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDatabase();
            }
        });
    }

    private void saveToDatabase() {
        String nama = edt_nama.getText().toString().trim();
        String deskripsi = edt_deskripsi.getText().toString().trim();
        String harga = edt_harga.getText().toString().trim();

        if(!TextUtils.isEmpty(nama)&&!TextUtils.isEmpty(deskripsi)&&!TextUtils.isEmpty(harga)){
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseContract.PengeluaranEntry.COLUMN_NAMA, nama);
            contentValues.put(DatabaseContract.PengeluaranEntry.COLUMN_DESKRIPSI, deskripsi);
            contentValues.put(DatabaseContract.PengeluaranEntry.COLUMN_HARGA, harga);

            long result = mDb.insert(DatabaseContract.PengeluaranEntry.TABLE_NAME, null, contentValues);
            if (result>0){
                finish();
            }else {
                Snackbar snackbar = Snackbar.make(rootView, "Gagal", Snackbar.LENGTH_LONG);
                snackbar.show();
            }

        }else {
            Snackbar snackbar = Snackbar.make(rootView, "Silahkan isi form terlebih dahulu", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
