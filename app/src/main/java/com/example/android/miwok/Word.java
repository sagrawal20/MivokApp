package com.example.android.miwok;

import java.lang.reflect.Modifier;

public class Word {

    private String mDefaultTranslation;
    private String mMivokTranslation;
    private int mImageID = NO_IMAGE_PROVIDED;
    public static final int NO_IMAGE_PROVIDED = -1;

    public Word(String DefaultTranslation, String MivokTranslation, int imageID){
        mDefaultTranslation = DefaultTranslation;
        mMivokTranslation = MivokTranslation;
        mImageID=imageID;
    }
    public Word(String DefaultTranslation, String MivokTranslation){
        mDefaultTranslation = DefaultTranslation;
        mMivokTranslation = MivokTranslation;
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
    public boolean hasImage(){
        return mImageID != NO_IMAGE_PROVIDED;
    }

}
