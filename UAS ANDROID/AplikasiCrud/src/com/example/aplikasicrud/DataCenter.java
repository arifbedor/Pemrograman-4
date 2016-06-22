package com.example.aplikasicrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataCenter extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "crud.db";
	private static final int DATABASE_VERSION = 1;

	public DataCenter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table biodata(kode integer primary key, nama text null, tgl text null, jk text null, alamat text null);";
		Log.d("Data", "onCreate: " + sql);
		db.execSQL(sql);
		sql = "INSERT INTO biodata (kode, nama, tgl, jk, alamat) VALUES ('1255', 'Ghazali', '1992-10-11', 'Laki-laki','Grong-grong');";
		db.execSQL(sql);
		sql = "INSERT INTO biodata (kode, nama, tgl, jk, alamat) VALUES ('1156', 'Andre', '1994-06-14', 'Laki-laki','Kembang Tanjong');";
		db.execSQL(sql);
		sql = "INSERT INTO biodata (kode, nama, tgl, jk, alamat) VALUES ('1357', 'Diska', '1992-07-04', 'Perempuan','Sigli');";
		db.execSQL(sql);
		sql = "INSERT INTO biodata (kode, nama, tgl, jk, alamat) VALUES ('1456', 'Intan', '1992-06-14', 'Perempuan','Caleue');";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
