package com.example.android.miwok;

public class Word {

    private String mDefaultTranslation;
    private String mMivokTranslation;
    private int mImageID;

    public Word(String DefaultTranslation, String MivokTranslation, int imageID){
        mDefaultTranslation = DefaultTranslation;
        mMivokTranslation = MivokTranslation;
        mImageID=imageID;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getMivokTranslation(){
        return mMivokTranslation;
    }

    public int getImageID(){
        return mImageID;
    }

}
