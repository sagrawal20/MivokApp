package com.example.android.miwok;

import java.lang.reflect.Modifier;

public class Word {

    private String mDefaultTranslation;
    private String mMivokTranslation;
    private int mAudioID;
    private int mImageID = NO_IMAGE_PROVIDED;
    public static final int NO_IMAGE_PROVIDED = -1;

    public Word(String DefaultTranslation, String MivokTranslation, int imageID, int audioID){
        mDefaultTranslation = DefaultTranslation;
        mMivokTranslation = MivokTranslation;
        mImageID=imageID;
        mAudioID = audioID;

    }
    public Word(String DefaultTranslation, String MivokTranslation, int audioID){
        mDefaultTranslation = DefaultTranslation;
        mMivokTranslation = MivokTranslation;
        mAudioID = audioID;
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

    public int getAudioID(){
        return mAudioID;
    }
    public boolean hasImage(){
        return mImageID != NO_IMAGE_PROVIDED;
    }

}
