package com.example.pengeluaranku;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ItemAction{
    FloatingActionButton btn_add;
    private RecyclerView rv_pengeluaran;
    private RecAdapter adapter;
    private SQLiteDatabase mDb;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        mDb = dbHelper.getReadableDatabase();

        btn_add = (FloatingActionButton) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this,
                        AddPengeluaran.class);
                startActivity(intent);
            }
        });
        rv_pengeluaran = (RecyclerView) findViewById(R.id.rv_pengeluaran);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new RecAdapter(this, this);
        rv_pengeluaran.setLayoutManager(layoutManager);
        rv_pengeluaran.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.swapCursor(getAllPengeluaran());
        adapter.notifyDataSetChanged();
    }

    private Cursor getAllPengeluaran(){
        return mDb.query(DatabaseContract.PengeluaranEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void deletePengeluaran(final String deletedId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus pengeluaran ini?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDb.delete(DatabaseContract.PengeluaranEntry.TABLE_NAME,
                        "_id=?", new String[]{deletedId}
                );
                adapter.swapCursor(getAllPengeluaran());
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}