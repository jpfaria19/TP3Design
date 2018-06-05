package infnet.gads.jpfaria.tp3design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LinearActivity extends AppCompatActivity {

    private TextView emailTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        emailTxt = findViewById(R.id.txtEmail);
        emailTxt.setText(getIntent().getExtras().getString("Email"));
    }


}
