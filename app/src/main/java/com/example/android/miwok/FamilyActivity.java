package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<Word> family = new ArrayList<>();
        family.add(new Word("father", "әpә", R.drawable.father));
        family.add(new Word("mother", "әṭa", R.drawable.mother));
        family.add(new Word("son", "angsi", R.drawable.son));
        family.add(new Word("daughter", "tune", R.drawable.daughter));
        family.add(new Word("older brother", "taachi", R.drawable.olderbrother));
        family.add(new Word("younger brother", "chalitti", R.drawable.youngerbrother));
        family.add(new Word("older sister", "teṭe", R.drawable.oldersister));
        family.add(new Word("younger sister", "kolliti", R.drawable.youngersister));
        family.add(new Word("grandmother", "ama", R.drawable.grandfather));
        family.add(new Word("grandfather", "paapa", R.drawable.grandmother));

        WordAdapter items = new WordAdapter(this, family, R.color.category_family);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(items);

    }
}