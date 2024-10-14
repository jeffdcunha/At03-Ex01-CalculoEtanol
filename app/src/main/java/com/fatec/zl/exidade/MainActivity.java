package com.fatec.zl.exidade;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;

/*
 *@author:Jefferson Dantas da Cunha
 *@RA: 1110482323044
 */
public class MainActivity extends AppCompatActivity {

    private EditText etDia;
    private EditText etMes;
    private EditText etAno;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etDia = findViewById(R.id.etDia);
        etDia.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        etMes = findViewById(R.id.etMes);
        etMes.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        etAno = findViewById(R.id.etAno);
        etAno.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        Button btn = findViewById(R.id.btnCalcular);

        btn.setOnClickListener(op -> Calcular());
    }

    private void Calcular(){
        int dia = Integer.parseInt(etDia.getText().toString());
        int mes = Integer.parseInt(etMes.getText().toString());
        int ano = Integer.parseInt(etAno.getText().toString());
        Calendar c = Calendar.getInstance();

        int anos = c.get(Calendar.YEAR) - ano;
        int meses = c.get(Calendar.MONTH) - mes;
        int dias = c.get(Calendar.DAY_OF_MONTH) - dia;

        // Ajuste para quando o mês ou dia são negativos (necessário "emprestar" meses ou dias)
        if (dias < 0) {
            meses--;
            dias += diasNoMes(mes, ano);  // Empresta dias do mês anterior
        }

        if (meses < 0) {
            anos--;
            meses += 12;  // Empresta meses do ano anterior
        }

        String msg = getString(R.string.s_mensagem) + " " + anos + " anos " + meses + " meses e " + dias + " dias.";
        tvRes.setText(msg);

    }

    // Método para verificar se um ano é bissexto
    private boolean isBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }

    // Método para obter o número de dias em um determinado mês
    private int diasNoMes(int mes, int ano) {
        switch (mes) {
            case 1: return 31;  // Janeiro
            case 2: return isBissexto(ano) ? 29 : 28;  // Fevereiro
            case 3: return 31;  // Março
            case 4: return 30;  // Abril
            case 5: return 31;  // Maio
            case 6: return 30;  // Junho
            case 7: return 31;  // Julho
            case 8: return 31;  // Agosto
            case 9: return 30;  // Setembro
            case 10: return 31;  // Outubro
            case 11: return 30;  // Novembro
            case 12: return 31;  // Dezembro
            default: return 0;
        }
    }
}