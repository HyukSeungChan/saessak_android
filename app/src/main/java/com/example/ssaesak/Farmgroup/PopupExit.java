package com.example.ssaesak.Farmgroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssaesak.R;

public class PopupExit extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_farmgroup_exit);

        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra( "exit", true );
            setResult( RESULT_OK, intent );
            finish();
        });

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> {
            finish();
        });

    }

}
