package infnet.gads.jpfaria.tp3design;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btnSingup;
    Button btnSubmit;
    Button btnReset;

    EditText user;
    EditText password;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.fieldUseremail);
        password = findViewById(R.id.fieldPassword);
        btnReset = findViewById(R.id.btnResetPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            // Forget Password
            public void onClick(View v) {
                String email = user.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Digite seu e-mail", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Instruções para redefinir sua senha foram enviadas para seu e-mail por favor.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Falha ao enviar e-mail de redefinição!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }

    public void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void btnSub(View v) {
        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Por favor aguarde...", "Processando", true);
        (firebaseAuth.signInWithEmailAndPassword(user.getText().toString(), password.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, LinearActivity.class);
                            intent.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                            startActivity(intent);
                        } else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

        /*Intent ListActivity = new Intent(this, LinearActivity.class);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        String login = user.getText().toString();
        String pass = password.getText().toString();

        if (login.equals("admin")&&pass.equals("admin")){
            alert("Login realizado com sucesso");
            startActivity(ListActivity);

        }else{
            alert("Login ou senha incorretos.");
        }*/


    }


    public void btnSingUp(View v) {
        Intent RegisterActivity = new Intent(this, TableActivity.class);
        btnSingup = (Button) findViewById(R.id.btnSingup);
        startActivity(RegisterActivity);
    }
}
