package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NumbersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumbersFragment extends Fragment {

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NumbersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumbersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumbersFragment newInstance(String param1, String param2) {
        NumbersFragment fragment = new NumbersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
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
        WordAdapter items = new WordAdapter(getActivity(), numbers, R.color.category_numbers);
        ListView listView = rootView.findViewById(R.id.listView);
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
                    song = MediaPlayer.create(getActivity(), word.getAudioID());
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


        return rootView;
    }

    @Override
    public void onStop() {
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