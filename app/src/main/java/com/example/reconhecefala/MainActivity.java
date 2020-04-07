package com.example.reconhecefala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String mensagem;
    TextView txt;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.texto);

    }

    /*
        MOSTRAR ENTRADA DE DIALOGO DO GOOGLE SPEECH
      */
    private void promptSpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "DIGA ALGO");

        try{
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(),
                    "Desculpe, dispositivo sem este recurso",
                    Toast.LENGTH_LONG).show();
        }

    }


    /*
        RECEBE ENTRADA DO GOOGLE SPEECH
     */

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE_SPEECH_INPUT){
            if(resultCode == RESULT_OK && null != data){
                ArrayList<String> resultado;
                resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                mensagem = resultado.get(0);

                txt.setText(mensagem);
            }
        }
    }

    public void falar(View view) {
        promptSpeechInput();
    }
}
