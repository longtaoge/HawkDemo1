package org.xiangbalao.hawkdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.SqliteStorage;

import model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private RelativeLayout content_main;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        String databasesPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/hawkDome5.db";

        Hawk.init(this).
                setStorage(new SqliteStorage(MainActivity.this, databasesPath))
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.JSON_STRING)
                .build();


        Hawk.put("abc", 123);
        Hawk.put("abc2", "123");

        User user = new User("ZHANGSHAG", 10);
        Hawk.put("USER", user);

        Log.i(MainActivity.class.getSimpleName(), String.valueOf(Hawk.get("abc")));


        Log.i(MainActivity.class.getSimpleName(), String.valueOf(Hawk.get("abc2")));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        content_main = (RelativeLayout) findViewById(R.id.content_main);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:

                break;
        }
    }


}
