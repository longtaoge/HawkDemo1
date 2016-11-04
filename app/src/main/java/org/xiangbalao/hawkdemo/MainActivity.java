package org.xiangbalao.hawkdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.SqliteStorage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



        String databasesPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/hawkDome4.db";

        Hawk.init(this).
                setStorage(new SqliteStorage(MainActivity.this, databasesPath))
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.NO_ENCRYPTION)
                .build();


        Hawk.put("abc",123);
        Hawk.put("abc2","123");

        Log.i(MainActivity.class.getSimpleName(),String.valueOf(Hawk.get("abc")));


        Log.i(MainActivity.class.getSimpleName(),String.valueOf(Hawk.get("abc2")));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
