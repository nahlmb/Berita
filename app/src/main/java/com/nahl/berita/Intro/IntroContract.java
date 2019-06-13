package com.nahl.berita.Intro;

public interface IntroContract {

    interface ViewSplash {
        void checkIsFirstTimeOpen();
        void goToMainActivity();
        void goToIntroActivity();

    }

    interface ViewIntro {
        void saveData(String userName, String countryName, String contryId, String favoritCategory);
    }
}
