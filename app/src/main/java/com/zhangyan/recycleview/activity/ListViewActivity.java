package com.zhangyan.recycleview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zhangyan.recycleview.R;
import com.zhangyan.recycleview.adapter.ListViewAdapter;
import com.zhangyan.recycleview.http.Service;
import com.zhangyan.recycleview.http.Tngou;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListViewActivity extends AppCompatActivity {
    private ListView mListView;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_viewctivity);
        mListView = (ListView) findViewById(R.id.list_view_main);
        adapter = new ListViewAdapter(this, new ArrayList<Tngou.Cook>());
        mListView.setAdapter(adapter);

        getCook();
    }

    private void getCook() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service server = retrofit.create(Service.class);
        Call<Tngou> call = server.getList(0, 1, 20);
        call.enqueue(new Callback<Tngou>() {
            @Override
            public void onResponse(Call<Tngou> call, Response<Tngou> response) {
                final List<Tngou.Cook> list = response.body().getList();
                adapter.addAll(list);

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        Tngou.Cook cook = list.get(position);
//                        if (position == 0){
//                            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
//                        }
//                        if (position == 1){
//                            Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
//                        }

                        Toast.makeText(getApplicationContext(),cook.getName(),Toast.LENGTH_SHORT).show();


                    }
                });
            }

            @Override
            public void onFailure(Call<Tngou> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
