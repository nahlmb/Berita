package com.nahl.berita.Detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nahl.berita.R;

public class NewsDetailActivity extends AppCompatActivity implements NewsDetailContract.View {
    ImageView imgNews;
    TextView txtTitle, txtContent, txtSource, txtAuthorAndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        supportPostponeEnterTransition();

        imgNews = findViewById(R.id.img_news_detail);
        txtTitle = findViewById(R.id.txt_news_detail_title);
        txtContent = findViewById(R.id.txt_news_content);
        txtSource = findViewById(R.id.txt_news_media);
        txtAuthorAndDate = findViewById(R.id.txt_author_and_date);
        setTextAndImage();



    }

    @Override
    public void setTextAndImage() {
        Bundle extra = getIntent().getExtras();

        String imgUrl = extra.getString("IMAGE_URL");
        txtTitle.setText(extra.getString("NEWS_TITLE"));
        txtContent.setText(extra.getString("CONTENT"));
        imgNews.setTransitionName(extra.getString("EXTRA_IMAGE_TRANSITION_NAME"));
        txtSource.setText(extra.getString("SOURCE"));
        txtAuthorAndDate.setText(extra.getString("AUTHOR_AND_DATE"));

        supportStartPostponedEnterTransition();
        Glide.with(this).load(imgUrl).into(imgNews);
    }
}
