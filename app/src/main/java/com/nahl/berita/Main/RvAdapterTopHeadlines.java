package com.nahl.berita.Main;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nahl.berita.Detail.NewsDetailActivity;
import com.nahl.berita.Model.News;
import com.nahl.berita.R;

import java.util.List;

public class RvAdapterTopHeadlines extends RecyclerView.Adapter<RvAdapterTopHeadlines.ViewHolder> {
    private List<News.Article> news;
    private Context context;

    public RvAdapterTopHeadlines(Context context, List<News.Article> news) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_news_1, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RvAdapterTopHeadlines.ViewHolder viewHolder, final int i) {
        viewHolder.news1Title.setText(news.get(i).getTitle());
        viewHolder.news1Desc.setText(news.get(i).getDescription());
        Glide.with(context).load(news.get(i).getUrlToImage()).into(viewHolder.imgNews1);

        viewHolder.imgNews1.setTransitionName("trans" + news.get(i).getUrlToImage());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent goToDetail = new Intent(context, NewsDetailActivity.class);
                goToDetail.putExtra("NEWS_TITLE", news.get(i).getTitle());
                goToDetail.putExtra("IMAGE_URL", news.get(i).getUrlToImage());
                goToDetail.putExtra("CONTENT", news.get(i).getContent());
                goToDetail.putExtra("SOURCE", news.get(i).getSource().getName());
                goToDetail.putExtra("AUTHOR_AND_DATE", news.get(i).getAuthor() + ", " + news.get(i).getPublishedAt());
                goToDetail.putExtra("EXTRA_IMAGE_TRANSITION_NAME", viewHolder.imgNews1.getTransitionName());

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, viewHolder.imgNews1, viewHolder.imgNews1.getTransitionName());

                viewHolder.itemView.getContext().startActivity(goToDetail, options.toBundle());

            }
        });

    }

    @Override
    public int getItemCount() {

        if(news.size()<=10){
            return news.size();
        }else {
            return 10;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView news1Title, news1Desc, news1More;
        ImageView imgNews1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           news1Title = itemView.findViewById(R.id.txt_news1_title);
           news1More = itemView.findViewById(R.id.txt_read_more);
           news1Desc = itemView.findViewById(R.id.txt_news1_desc);

           imgNews1 = itemView.findViewById(R.id.img_news1_image);
        }
    }
}
