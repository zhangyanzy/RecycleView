package com.zhangyan.recycleview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.zhangyan.recycleview.R;


public class MainActivity extends AppCompatActivity {
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Recycle_view_item:
                intent = new Intent(getApplicationContext(), RecycleViewActivity.class);
                startActivity(intent);
                break;
            case R.id.listView_item:
                intent = new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
        return true;
    }
}

