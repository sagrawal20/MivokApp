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
 * Use the {@link FamilyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FamilyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FamilyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FamilyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FamilyFragment newInstance(String param1, String param2) {
        FamilyFragment fragment = new FamilyFragment();
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
        ArrayList<Word> family = new ArrayList<>();
        family.add(new Word("father", "әpә", R.drawable.father, R.raw.family_father));
        family.add(new Word("mother", "әṭa", R.drawable.mother, R.raw.family_mother));
        family.add(new Word("son", "angsi", R.drawable.son, R.raw.family_son));
        family.add(new Word("daughter", "tune", R.drawable.daughter, R.raw.family_daughter));
        family.add(new Word("older brother", "taachi", R.drawable.olderbrother, R.raw.family_older_brother));
        family.add(new Word("younger brother", "chalitti", R.drawable.youngerbrother, R.raw.family_younger_brother));
        family.add(new Word("older sister", "teṭe", R.drawable.oldersister, R.raw.family_older_sister));
        family.add(new Word("younger sister", "kolliti", R.drawable.youngersister, R.raw.family_younger_sister));
        family.add(new Word("grandmother", "ama", R.drawable.grandmother, R.raw.family_grandmother));
        family.add(new Word("grandfather", "paapa", R.drawable.grandfather, R.raw.family_grandfather));

        WordAdapter items = new WordAdapter(getActivity(), family, R.color.category_family);
        ListView listView = rootView.findViewById(R.id.listView);
        listView.setAdapter(items);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = family.get(position);
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