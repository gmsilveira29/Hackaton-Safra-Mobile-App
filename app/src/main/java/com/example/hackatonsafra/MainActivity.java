package com.example.hackatonsafra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


import com.tyagiabhinav.dialogflowchatlibrary.Chatbot;
import com.tyagiabhinav.dialogflowchatlibrary.ChatbotActivity;
import com.tyagiabhinav.dialogflowchatlibrary.ChatbotSettings;
import com.tyagiabhinav.dialogflowchatlibrary.DialogflowCredentials;

import java.util.UUID;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Button chatFab = findViewById(R.id.chatFab);
        chatFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChatbot(view);
            }
        });
    }

    public void openChatbot(View view) {
        // fornecer o JSON da credencial do Google do Dialogflow salvo na pasta RAW
        DialogflowCredentials.getInstance().setInputStream(getResources().openRawResource(R.raw.i));

        ChatbotSettings.getInstance().setChatbot( new Chatbot.ChatbotBuilder()
                .setDoAutoWelcome(false) // Verdadeiro por padrão, falso se você não quiser que o Bot cumprimente o usuário automaticamente. O agente Dialogflow deve ter uma intenção de welcome para lidar com isso
                .setChatBotAvatar(getDrawable(R.drawable.safira_avatar)) // avatar do bot
                .setChatUserAvatar(getDrawable(R.drawable.user_avatar)) // avatar usuário
                .setShowMic(true) // habilitar inserção de voz
                .build());
        Intent intent = new Intent(MainActivity.this, ChatbotActivity.class);
        Bundle bundle = new Bundle();

        //fornecer um UUID para sua sessão com o agente Dialogflow
        bundle.putString(ChatbotActivity.SESSION_ID, UUID.randomUUID().toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
