package infnet.gads.jpfaria.tp3design;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSingup;
    Button btnSubmit;

    EditText user;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.fieldUsername);
        password = (EditText) findViewById(R.id.fieldPassword);

    }

    public void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    public void btnSub(View v) {
        Intent ListActivity = new Intent(this, LinearActivity.class);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        String login = user.getText().toString();
        String pass = password.getText().toString();

        if (login.equals("admin")&&pass.equals("admin")){
            alert("Login realizado com sucesso");
            startActivity(ListActivity);

        }else{
            alert("Login ou senha incorretos.");
        }

    }

    public void btnSingUp(View v){
        Intent RegisterActivity = new Intent(this, TableActivity.class);
        btnSingup = (Button) findViewById(R.id.btnSingup);
        startActivity(RegisterActivity);
    }
}
