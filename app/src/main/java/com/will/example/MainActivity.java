package com.will.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.will.filesearcher.FileSearcher;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText keywordEdit = findViewById(R.id.example_keyword);
        Button button = (Button) findViewById(R.id.example_confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = keywordEdit.getText().toString().replaceAll(" ","");

                FileSearcher fileSearcher = new FileSearcher(MainActivity.this);
                fileSearcher.withKeyword(content)
                            .search(new FileSearcher.FileSearcherCallback() {
                    @Override
                    public void onSelect(List<File> files) {
                        Toast.makeText(MainActivity.this, "you have selected "+files.size()+" file(s).", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
