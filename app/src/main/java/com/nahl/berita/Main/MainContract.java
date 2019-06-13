package com.nahl.berita.Main;

import com.nahl.berita.Model.News;

import java.util.List;

public interface MainContract {

    interface View {
        void showLoading();
        void hideLoading();
        void showFragmentList(Boolean everyThing, Boolean headLines, String params1, String shortBy);
        void showUserNameAndDate(String userName, String date);
        void showCountryTopHeadline(List<News.Article> articlesItems);
        void showCategoryTopHeadline(List<News.Article> articlesItems);
        void showTopicUserLike(List<News.Article> articlesItems);
        void setFavoritTopic();
        void notYetMessage();
    }

    interface Presenter {
        void getCountryTopHeadline(String countryId, String categoryFavorit);
        void getCetogoryTopHeadline(String category);
        void getTopicUserLike(String query);
    }
}
