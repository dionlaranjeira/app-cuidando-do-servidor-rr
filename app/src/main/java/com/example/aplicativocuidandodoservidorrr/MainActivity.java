package com.example.aplicativocuidandodoservidorrr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    //Pego uma referencia do nosso no Firebase
    private DatabaseReference meubanco = FirebaseDatabase.getInstance().getReference();

    EditText campoCPF;
    EditText campoNome;
    EditText campoDataNasc;
    RadioGroup radioGroupSexo;
    Spinner spinnerRacas;
    RadioButton masculino;
    RadioButton feminino;
    Button btnCadastrar;
    String sexo = "Masculino";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meubanco.child("teste").setValue("olá mundo Firebase!");
        meubanco.child("teste").child("outro nó").child("mais outro nó").setValue("Exemplos nó!");

        campoCPF = findViewById(R.id.editTextCPF);
        campoNome = findViewById(R.id.editTextNomeCompleto);
        campoDataNasc = findViewById(R.id.editTextDataNasc);
        radioGroupSexo = findViewById(R.id.radioGroupSexo);
        spinnerRacas = findViewById(R.id.spinnerRaca);
        btnCadastrar = findViewById(R.id.buttonCadastrar);

        radioGroupSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButtonMasculino){
                    sexo = "Masculino";
                }else{
                    sexo = "Feminino";
                }
            }
        });


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = pegaDadosServidor(campoCPF);
                String nome = pegaDadosServidor(campoNome);
                String dataNasc = pegaDadosServidor(campoDataNasc);

                String raca;

                String racas [] = {"Parda","Branca","Negra","Indígena"};
                int posicao = spinnerRacas.getSelectedItemPosition();
                String racaSelecionada = racas[posicao];

                Servidor novoServidor = new Servidor();

                novoServidor.setCpf(cpf);
                novoServidor.setNomeCompleto(nome);
                novoServidor.setDataNasc(dataNasc);
                novoServidor.setRaca(racaSelecionada);
                novoServidor.setSexo(sexo);

                meubanco.child(novoServidor.getCpf()).setValue(novoServidor);

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
