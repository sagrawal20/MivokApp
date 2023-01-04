package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioDeviceCallback;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer song;
    private AudioManager audioManager;


    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                song.pause();
                song.seekTo(0);
            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                song.start();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                song.pause();
                song.seekTo(0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        ArrayList<Word> numbers = new ArrayList<>();
        numbers.add(new Word("one", "lutti", R.drawable.one, R.raw.number_one));
        numbers.add(new Word("two", "otiiko", R.drawable.two, R.raw.number_two));
        numbers.add(new Word("three", "tolookosu", R.drawable.three, R.raw.number_three));
        numbers.add(new Word("four", "oyyisa", R.drawable.four, R.raw.number_four));
        numbers.add(new Word("five", "massokka", R.drawable.five, R.raw.number_five));
        numbers.add(new Word("six", "temmokka", R.drawable.six, R.raw.number_six));
        numbers.add(new Word("seven", "kenekaku", R.drawable.seven, R.raw.number_seven));
        numbers.add(new Word("eight", "kawinta", R.drawable.eight, R.raw.number_eight));
        numbers.add(new Word("nine", "wo’e", R.drawable.nine, R.raw.number_nine));
        numbers.add(new Word("ten", "na’aacha", R.drawable.ten, R.raw.number_ten));
//        TextView tv = new TextView(this, null, 0, R.style.CategoryStyle);
//        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        tv.setTextColor(0xFF040000);
//        tv.setBackgroundColor(0xFFFFD9A9);
        WordAdapter items = new WordAdapter(this, numbers, R.color.category_numbers);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(items);



//        AudioAttributes mPlaybackAttributes;
//        AudioFocusRequest mFocusRequest;
//        mPlaybackAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).
//                setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            mFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN).
//                    setAudioAttributes(mPlaybackAttributes).setAcceptsDelayedFocusGain(true).
//                    setWillPauseWhenDucked(true).setOnAudioFocusChangeListener(afChangeListener, mMyHandler).build();
//        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = numbers.get(position);
                releaseMediaPlayer();
                int result = audioManager.
                        requestAudioFocus(afChangeListener,
                                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    song = MediaPlayer.create(NumbersActivity.this, word.getAudioID());
                    song.start();
                    song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });



//        for(int i=0;i<numbers.size();i++){
//            TextView tv = new TextView(this, null, 0, R.style.CategoryStyle);
//            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            tv.setText(numbers.get(i));
//            tv.setTextColor(0xFF040000);
//            tv.setBackgroundColor(0xFFFFD9A9);
//            linearLayout.addView(tv);
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if (song != null){
            audioManager.abandonAudioFocus(afChangeListener);

            song.release();

            song = null;

        }
    }
}