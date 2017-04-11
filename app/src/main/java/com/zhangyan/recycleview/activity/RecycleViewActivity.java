package com.zhangyan.recycleview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zhangyan.recycleview.adapter.RecycleViewAdapter;
import com.zhangyan.recycleview.R;
import com.zhangyan.recycleview.http.Service;
import com.zhangyan.recycleview.http.Tngou;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecycleViewActivity extends AppCompatActivity {

    private List<Tngou.Cook> list = new ArrayList<>();
    private RecycleViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //不设置为ListView界面  设置为横向混动
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //瀑布流设置
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(list);
        recyclerView.setAdapter(adapter);
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
                List<Tngou.Cook> list = response.body().getList();
                adapter.addAll(list);
            }

            @Override
            public void onFailure(Call<Tngou> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

}
