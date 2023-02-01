package com.eurya.luajit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;

public class LuaActivity extends Activity {
    
    private EditText code;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        code = findViewById(R.id.code);
    }
    public void run(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("code", code.getEditableText().toString());
        startActivity(intent);
    }
}
