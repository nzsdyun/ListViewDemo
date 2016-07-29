package com.example.chenkun.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.common.adapter.CommonAdapter;
import com.common.adapter.MyAdapter;
import com.common.adapter.ViewHolder;
import com.common.adapter.com.common.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        listViewTest();
//        listViewCommonAdapterTest();
    }

    private List<Person> initDatas() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 1000; i++) {
            persons.add(new Person(R.mipmap.ic_launcher, "sky" + i));
        }
        return persons;
    }

    private void initViews() {
        mListView = (ListView) findViewById(R.id.list_view);
    }

    /**
     * test the traditional practice of ListView
     */
    public void listViewTest() {
        List<Person> persons = initDatas();
        mListView.setAdapter(new MyAdapter(this, persons, R.layout.list_item));
    }

    /**
     * test common Adapter ListView
     */
    public void listViewCommonAdapterTest() {
        List<Person> persons = initDatas();
        mListView.setAdapter(new CommonAdapter<Person>(this, persons, R.layout.list_item) {
            @Override
            public void convert(ViewHolder viewHolder, Person item, int position) {
                viewHolder.setText(R.id.name, item.getName());
                viewHolder.setImageResource(R.id.head, item.getHeadId());
            }
        });
    }
}
