package infnet.gads.jpfaria.tp3design;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TableActivity extends AppCompatActivity {

    private EditText txtPersonName;
    private EditText txtEmailAddress;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        txtPersonName = findViewById(R.id.fieldUsername);
        txtEmailAddress = findViewById(R.id.fieldEmail);
        txtPassword = findViewById(R.id.fieldPassword);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void btnRegisterUser_Click(View v){
        final ProgressDialog progressDialog = ProgressDialog.show(TableActivity.this, "Por favor aguarde...", "Processando", true);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress.getText().toString(), txtPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()){
                    Toast.makeText(TableActivity.this, "Registrado com sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(TableActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Log.e("ERROR", task.getException().toString());
                    Toast.makeText(TableActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void btnClear(View v){
        txtPersonName.getText().clear();
        txtPassword.getText().clear();
        txtEmailAddress.getText().clear();
    }
    
}
