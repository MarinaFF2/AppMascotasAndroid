package com.example.appmascotas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.appmascotas.email.JavaMail;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class ContactActivity extends AppCompatActivity {

    private static EditText edtNombre ;
    private static EditText edtDestinatario;
    private static EditText edtMensaje;

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
        edtNombre =(EditText)findViewById(R.id.editNombre);
        edtDestinatario =(EditText)findViewById(R.id.editEmail);
        edtMensaje =(EditText)findViewById(R.id.editMensaje);

        JavaMail javaMail = new JavaMail(ContactActivity.this,edtDestinatario.getText().toString().trim(),edtNombre.getText().toString(),edtMensaje.getText().toString());

        new SendEmail().execute(javaMail.getMessege());
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
    public class SendEmail extends AsyncTask<Message,String,String> {
        //Inicializamos proceso de dialogo
        private ProgressDialog mProgressDialog;

        @Override
        protected String doInBackground(Message... messages) {
            try {
                //cuando tiene exito
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                //cuando ocurre un error
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //termina el proceso de dialogo
            mProgressDialog.dismiss();
            if(s.equals("Success")){ //cuando tiene exito
                //iniciamos el alert dialogo
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Mail send successfully.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //limpiamos todos los editText
                        edtNombre.setText("");
                        edtDestinatario.setText("");
                        edtMensaje.setText("");

                    }
                });
                //mostramos el alert dialog
                builder.show();
            }else{//cuando ocurre un error
                Toast.makeText(ContactActivity.this,"Something went wrong ?",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //creamos y mostramos el proceso de dialogo
            mProgressDialog = ProgressDialog.show(ContactActivity.this,"Please wait","Sending mail...",true,false);
        }
    }
}