package com.example.sharedpreferences_missionandroid121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MESSAGE_ID ="message_pref";
    private EditText enterMessage;
    private TextView savedMessage;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterMessage = findViewById(R.id.enterMessage_editText);
        savedMessage = findViewById(R.id.savedMessage_textView);
        saveBtn = findViewById(R.id.save_button);

        saveBtn.setOnClickListener(this);

        SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
        String messageIs = getSharedData.getString("message","default Text");
        if(!messageIs.isEmpty()){
            savedMessage.setText(messageIs);
        }
    }

    @Override
    public void onClick(View view) {

        String message = enterMessage.getText().toString().trim();
        if(!message.isEmpty()){
            savedMessage.setText(message);

            SharedPreferences  sharedPreferences = getSharedPreferences(MESSAGE_ID,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("message", message);
            Toast.makeText(getApplicationContext(),"New Message Saved",Toast.LENGTH_SHORT).show();
            editor.apply();
        }
        else{
            Toast.makeText(getApplicationContext(), "Enter your message", Toast.LENGTH_SHORT).show();
        }
    }
}
