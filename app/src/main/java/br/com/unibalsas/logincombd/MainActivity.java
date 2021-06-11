package br.com.unibalsas.logincombd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText uname, pwd;
    Button loginBtn;
    SharedPreferences pref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = (EditText)findViewById(R.id.txtName); // login
        pwd = (EditText)findViewById(R.id.txtPwd); // senha
        loginBtn = (Button)findViewById(R.id.btnLogin); // botao

        pref = getSharedPreferences("user_details",MODE_PRIVATE);

        intent = new Intent(MainActivity.this, DetalhesActivity.class);
        if(pref.contains("username") && pref.contains("password")){
            startActivity(intent);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getText().toString();
                String password = pwd.getText().toString();
                if(username.equals("admin") && password.equals("123")){
                    SharedPreferences.Editor editor = pref.edit(); // Abre o SP em modo de escrit
                    editor.putString("username",username);
                    editor.putString("password",password);
                    editor.putString("numero", "999");
                    editor.commit(); // Grava os dados
                    Toast.makeText(getApplicationContext(), "Login com sucesso",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Usuário não encontrado",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}