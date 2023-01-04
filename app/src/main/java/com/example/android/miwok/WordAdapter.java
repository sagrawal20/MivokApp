package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceID;
    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceID) {
        super(context,0, words);
        mColorResourceID = colorResourceID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView textViewFirst = listItemView.findViewById(R.id.firstTV);
        textViewFirst.setText(currentWord.getMivokTranslation());

        TextView textViewSecond = listItemView.findViewById(R.id.secondTV);
        textViewSecond.setText(currentWord.getDefaultTranslation());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        if(currentWord.hasImage()){
            iconView.setImageResource(currentWord.getImageID());
            iconView.setVisibility(View.VISIBLE);
        }else {
            iconView.setVisibility(View.GONE);
        }
        View textContainer = listItemView.findViewById(R.id.layoutBack);
        int color = ContextCompat.getColor(getContext(), mColorResourceID);
        textContainer.setBackgroundResource(mColorResourceID);

        ImageView playImage = (ImageView) listItemView.findViewById(R.id.second_list_item_icon);
        playImage.setBackgroundResource(mColorResourceID);

        return listItemView;
    }
    
}
