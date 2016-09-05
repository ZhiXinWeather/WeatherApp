package com.example.haiyuan1995.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haiyuan 1995 on 2016/9/5.
 */

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.id_search_toolbar)
    Toolbar idSearchToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//让toolbar覆盖到状态栏上

        initToolbar();
    }

    private void initToolbar() {
        idSearchToolbar.setTitle("");
        idSearchToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_24dp);
        setSupportActionBar(idSearchToolbar);

        idSearchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
