package com.nahl.berita.Main;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.nahl.berita.BaseApp;
import com.nahl.berita.Model.News;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getCountryTopHeadline(String countryId, String categoryFavorit) {
        view.showLoading();
        AndroidNetworking.get(BaseApp.BASE_URL_TOP_HEADLINES)
                .setPriority(Priority.HIGH)
                .addQueryParameter("country", countryId)
                .addQueryParameter("category", categoryFavorit)
                .addQueryParameter("apiKey", "d9f54d3a42e94f59bb9366189931f228")
                .build()
                .getAsObject(News.class, new ParsedRequestListener<News>() {

                    @Override
                    public void onResponse(News response) {
                        view.hideLoading();
                        view.showCountryTopHeadline(response.getArticles());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("errorNetwork",anError.getLocalizedMessage());

                    }
                });

    }

    @Override
    public void getCetogoryTopHeadline(String category) {
        AndroidNetworking.get(BaseApp.BASE_URL_TOP_HEADLINES)
                .setPriority(Priority.HIGH)
                .addQueryParameter("category", category)
                .addQueryParameter("shortBy", "popularity")
                .addQueryParameter("apiKey", "d9f54d3a42e94f59bb9366189931f228")
                .build()
                .getAsObject(News.class, new ParsedRequestListener<News>() {

                    @Override
                    public void onResponse(News response) {
                        view.showCategoryTopHeadline(response.getArticles());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("errorNetwork",anError.getLocalizedMessage());

                    }
                });

    }

    @Override
    public void getTopicUserLike(String query) {
        AndroidNetworking.get(BaseApp.BESE_URL_EVERYTHING)
                .setPriority(Priority.HIGH)
                .addQueryParameter("q", query)
                .addQueryParameter("apiKey", "d9f54d3a42e94f59bb9366189931f228")
                .build()
                .getAsObject(News.class, new ParsedRequestListener<News>() {

                    @Override
                    public void onResponse(News response) {
                        view.showTopicUserLike(response.getArticles());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("errorNetwork",anError.getLocalizedMessage());

                    }
                });

    }
}
