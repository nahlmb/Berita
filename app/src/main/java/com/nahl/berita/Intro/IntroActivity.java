package com.nahl.berita.Intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.nahl.berita.Main.MainActivity;
import com.nahl.berita.R;

public class IntroActivity extends AppCompatActivity implements IntroContract.ViewIntro {
    EditText userName;
    Spinner countrySpinner, categoryLovedSpinner;
    SharedPreferences.Editor editor;
    String countrySelected, countryIdSelected, lovedCategory;
    Button btnNextFromIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        userName = findViewById(R.id.edt_nama_user);
        countrySpinner = findViewById(R.id.spinner_negara_user);
        categoryLovedSpinner = findViewById(R.id.spinner_favorit_category);


        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] countryArray = getResources().getStringArray(R.array.country);
                String[] countryArrayId = getResources().getStringArray(R.array.countryId);
                countrySelected = countryArray[position];
                countryIdSelected = countryArrayId[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        categoryLovedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] categoryArray = getResources().getStringArray(R.array.category);
                lovedCategory = categoryArray[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnNextFromIntro = findViewById(R.id.btn_next_from_intro);
        btnNextFromIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strUserName = userName.getText().toString();


                saveData(strUserName, countrySelected, countryIdSelected, lovedCategory);
                Intent goToMain = new Intent(IntroActivity.this, MainActivity.class);
                goToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                goToMain.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(goToMain);
                finish();
            }
        });


    }


    @Override
    public void saveData(String userName, String countryName, String contryId, String favoritCategory) {
        editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
        editor.putBoolean("userMasuk", true);
        editor.putString("userName", userName);
        editor.putString("countryName", countryName);
        editor.putString("countryId", contryId);
        editor.putString("categoryFavorit", favoritCategory);
        editor.commit();


    }
}
