package com.example.appmascotas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.appmascotas.email.JavaMailAPI;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        addMenu();
        Button send = (Button) this.findViewById(R.id.buttonSend);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail(){
        EditText nombre =(EditText)findViewById(R.id.editNombre);
        EditText destinatario =(EditText)findViewById(R.id.editEmail);
        EditText mensaje =(EditText)findViewById(R.id.editMensaje);

        JavaMailAPI javaMailAPI = new JavaMailAPI(this,destinatario.getText().toString().trim(),nombre.getText().toString(),mensaje.getText().toString());

        javaMailAPI.execute();
    }

    private void addMenu() {
        //añadimos el action bar a la activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ponemos el icono de la app
        getSupportActionBar().setIcon(R.drawable.icon_huella);

        //añadimos la accion a los items del menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}