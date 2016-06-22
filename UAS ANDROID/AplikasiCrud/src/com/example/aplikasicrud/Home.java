package com.example.aplikasicrud;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends Activity {

	Button login;
	Intent a;
	EditText nama, password;
	String url, success;
	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		session = new SessionManager(getApplicationContext());
		Toast.makeText(getApplicationContext(),
				"User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG)
				.show();

		nama = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.btnLogin);

		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				url = "http://192.168.43.143/Kuliah/UAS_P4/login.php?"
						+ "name=" + nama.getText().toString() + "&password="
						+ password.getText().toString();

				if (nama.getText().toString().trim().length() > 0
						&& password.getText().toString().trim().length() > 0) {
					new Masuk().execute();
				} else {
					Toast.makeText(getApplicationContext(),
							"Lengkapi username/password terlebih dahulu!",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	public class Masuk extends AsyncTask<String, String, String> {
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			pDialog = new ProgressDialog(Home.this);
			pDialog.setMessage("Loading..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			JSONParser jParser = new JSONParser();

			JSONObject json = jParser.getJSONFromUrl(url);

			try {
				success = json.getString("success");

				Log.e("error", "nilai sukses=" + success);

				JSONArray hasil = json.getJSONArray("btnLogin");

				if (success.equals("1")) {

					for (int i = 0; i < hasil.length(); i++) {

						JSONObject c = hasil.getJSONObject(i);

						String nama = c.getString("nama").trim();
						String email = c.getString("password").trim();
						session.createLoginSession(nama, email);
						Log.e("ok", " ambil data");

					}
				} else {
					Log.e("erro", "tidak bisa ambil data 0");
				}

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("erro", "tidak bisa ambil data 1");
			}

			return null;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			if (success.equals("1")) {
				a = new Intent(Home.this, MainActivity.class);
				startActivity(a);
				finish();
			} else {
				Toast.makeText(getApplicationContext(),
						"Username/password yang anda masukkan tidak valid",
						Toast.LENGTH_LONG).show();
			}

		}

	}
}
