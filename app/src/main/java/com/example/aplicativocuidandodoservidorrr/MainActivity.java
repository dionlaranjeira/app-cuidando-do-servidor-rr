package com.example.aplicativocuidandodoservidorrr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText campoCPF;
    EditText campoNome;
    EditText campoDataNasc;
    RadioGroup radioGroupSexo;
    Spinner spinnerRacas;

    Button btnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoCPF = findViewById(R.id.editTextCPF);
        campoNome = findViewById(R.id.editTextNomeCompleto);
        campoDataNasc = findViewById(R.id.editTextDataNasc);
        radioGroupSexo = findViewById(R.id.radioGroupSexo);
        spinnerRacas = findViewById(R.id.spinnerRaca);
        btnCadastrar = findViewById(R.id.buttonCadastrar);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = pegaDadosServidor(campoCPF);
                String nome = pegaDadosServidor(campoNome);
                String dataNasc = pegaDadosServidor(campoDataNasc);
            }
        });

    }



    public String pegaDadosServidor(EditText campo){
        String valorDigitado = null;
        if(campo.getText().toString().isEmpty()){
            Toast.makeText(this,"CAMPO NÃO PREENCHIDO", Toast.LENGTH_SHORT).show();
        }else {
            valorDigitado =  String.valueOf(campo.getText());
        }
        return  valorDigitado;

    }


}
