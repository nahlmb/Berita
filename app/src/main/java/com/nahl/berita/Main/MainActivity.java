package com.nahl.berita.Main;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.nahl.berita.Model.News;
import com.nahl.berita.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity implements MainContract.View {
    Dialog loading;
    RecyclerView recyclerViewCountryHeadlines, recyclerViewCategoryHeadlines, recyclerViewFavoritTopic;
    CardView cardTellUsTopic, cardFavTopic;
    EditText edtTellUsTopic, edtSearch;
    Button btnTellUsTopic, btnFeedSetting;
    TextView txtFavTopic, txtHelloUser, txtDate, txtCategoryHeadlines, txtCountryHeadlines;
    ImageView iconSearch;
    MainPresenter presenter = new MainPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        final SharedPreferences.Editor editUserInfo = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
        String country = userInfo.getString("countryId", "");
        final String category = userInfo.getString("categoryFavorit", "");

        txtCategoryHeadlines = findViewById(R.id.txt_country_headlines);
        txtCountryHeadlines = findViewById(R.id.txt_category_headlines);
        txtCountryHeadlines.setText(userInfo.getString("countryName", "") + " Headlines");
        txtCategoryHeadlines.setText(userInfo.getString("categoryFavorit", "") + " Headlines");
        presenter.getCountryTopHeadline(country, category);
        presenter.getCetogoryTopHeadline(category);

        //submit favorit topic with query
        cardTellUsTopic = findViewById(R.id.card_tell_us_topic);
        cardFavTopic = findViewById(R.id.crd_fav_topic_list);
        txtFavTopic = findViewById(R.id.txt_news_about_topic);
        btnTellUsTopic = findViewById(R.id.btn_tell_us_topic);
        edtTellUsTopic = findViewById(R.id.edt_tell_us_topic);
        setFavoritTopic();


        //show userName and date
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        String userName = userInfo.getString("userName", "");
        txtDate = findViewById(R.id.date);
        txtHelloUser = findViewById(R.id.helloUser);

        showUserNameAndDate(userName, formattedDate);

        //edt search
        edtSearch = findViewById(R.id.edt_search_main);
        iconSearch = findViewById(R.id.searchIcon);
        iconSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "next up feature", Toast.LENGTH_SHORT).show();
            }
        });

        //feed setting
        btnFeedSetting = findViewById(R.id.btn_feed_setting);
        btnFeedSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "next up feature", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void showLoading() {
        loading = new Dialog(this);
        loading.setContentView(R.layout.loading_layout);
        loading.setCancelable(false);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if(!loading.isShowing()){
            loading.show();
        }
    }

    @Override
    public void hideLoading() {
        if(loading == null){
            loading = new Dialog(this);
            loading.setContentView(R.layout.loading_layout);
            loading.setCancelable(false);
            loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            loading.dismiss();
        }else {
            loading.dismiss();
        }
    }



    @Override
    public void showUserNameAndDate(String userName, String date) {
        txtHelloUser.setText("Hello!" + "\n" + userName);
        txtDate.setText(date);


    }

    @Override
    public void showCountryTopHeadline(List<News.Article> articlesItems) {
        RvAdapterTopHeadlines rvAdapterCountryHeadlines = new RvAdapterTopHeadlines(this, articlesItems);
        recyclerViewCountryHeadlines = findViewById(R.id.rv_country_headlines);
        recyclerViewCountryHeadlines.setAdapter(rvAdapterCountryHeadlines);
        recyclerViewCountryHeadlines.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCountryHeadlines.setNestedScrollingEnabled(true);
        rvAdapterCountryHeadlines.notifyDataSetChanged();
    }

    @Override
    public void showCategoryTopHeadline(List<News.Article> articlesItems) {
        RvAdapterTopHeadlines rvAdapterCountryHeadlines = new RvAdapterTopHeadlines(this, articlesItems);
        recyclerViewCategoryHeadlines = findViewById(R.id.rv_category_headlines);
        recyclerViewCategoryHeadlines.setAdapter(rvAdapterCountryHeadlines);
        recyclerViewCategoryHeadlines.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategoryHeadlines.setNestedScrollingEnabled(true);
        rvAdapterCountryHeadlines.notifyDataSetChanged();
    }

    @Override
    public void showTopicUserLike(List<News.Article> articlesItems) {
        RvAdapterEverythingHeadlines rvAdapterEverythingHeadlines = new RvAdapterEverythingHeadlines(this, articlesItems);
        recyclerViewFavoritTopic = findViewById(R.id.rv_fav_topic);
        recyclerViewFavoritTopic.setAdapter(rvAdapterEverythingHeadlines);
        recyclerViewFavoritTopic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewFavoritTopic.setNestedScrollingEnabled(true);
        rvAdapterEverythingHeadlines.notifyDataSetChanged();

    }

    @Override
    public void setFavoritTopic() {
        SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editUserInfo = getSharedPreferences("userInfo", MODE_PRIVATE).edit();

        btnTellUsTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
                SharedPreferences.Editor editUserInfo = getSharedPreferences("userInfo", MODE_PRIVATE).edit();

                if(!edtTellUsTopic.getText().toString().equals("") || !edtTellUsTopic.getText().toString().equals(null)){
                    editUserInfo.putString("topic",edtTellUsTopic.getText().toString());
                    editUserInfo.commit();
                    presenter.getTopicUserLike(edtTellUsTopic.getText().toString());
                    cardFavTopic.setVisibility(View.VISIBLE);
                    cardTellUsTopic.setVisibility(View.GONE);
                    txtFavTopic.setText("News about " + userInfo.getString("topic", ""));
                    onRestart();
                }

            }
        });

        String topic = userInfo.getString("topic", "");
        if(topic.equals(null)||topic.equals("")){
            cardFavTopic.setVisibility(View.GONE);
            cardTellUsTopic.setVisibility(View.VISIBLE);
        }else {
            cardTellUsTopic.setVisibility(View.GONE);
            cardFavTopic.setVisibility(View.VISIBLE);
            txtFavTopic.setText("News about " + topic);
            presenter.getTopicUserLike(topic);
        }

    }
}
