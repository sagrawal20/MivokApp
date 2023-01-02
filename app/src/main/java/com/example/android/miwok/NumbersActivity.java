package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        ArrayList<Word> numbers = new ArrayList<>();
        numbers.add(new Word("one", "lutti", R.drawable.one));
        numbers.add(new Word("two", "otiiko", R.drawable.two));
        numbers.add(new Word("three", "tolookosu", R.drawable.three));
        numbers.add(new Word("four", "oyyisa", R.drawable.four));
        numbers.add(new Word("five", "massokka", R.drawable.five));
        numbers.add(new Word("six", "temmokka", R.drawable.six));
        numbers.add(new Word("seven", "kenekaku", R.drawable.seven));
        numbers.add(new Word("eight", "kawinta", R.drawable.eight));
        numbers.add(new Word("nine", "wo’e", R.drawable.nine));
        numbers.add(new Word("ten", "na’aacha", R.drawable.ten));
//        TextView tv = new TextView(this, null, 0, R.style.CategoryStyle);
//        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        tv.setTextColor(0xFF040000);
//        tv.setBackgroundColor(0xFFFFD9A9);
        WordAdapter items = new WordAdapter(this, numbers);
        ListView listView = findViewById(R.id.numbersListView);
        listView.setAdapter(items);

//        for(int i=0;i<numbers.size();i++){
//            TextView tv = new TextView(this, null, 0, R.style.CategoryStyle);
//            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            tv.setText(numbers.get(i));
//            tv.setTextColor(0xFF040000);
//            tv.setBackgroundColor(0xFFFFD9A9);
//            linearLayout.addView(tv);
//        }
    }
}