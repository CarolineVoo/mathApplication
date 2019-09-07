package com.example.mathapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;


public class PlayActivity extends AppCompatActivity {
    TextView setText;           //TextView til tekstfeltet der man svarer, uten verdi
    String textAnswer = "";     //String til tekstfeltet der man svarer, med tom string verdi
    boolean gamePlay = true;    //Boolean til hele spillet, der den starter med true og kjører
    String[] arrayCalculation;  //Array til å sette inn ulike regenstykker
    String[] arrayAnswer;       //Array til å sette inn svarene til regnestykkene.
    Integer currentAnswer;      //Integer som gir svaret til regnestykke fra tilfeldig svar fra arrayAnswer
    Integer myAnswer;           //Integer som spillerern svarer

    Random randomIndex = new Random();      //Variabel som gir tilfeldig tall
    int n = randomIndex.nextInt(25);    //Gir begrenset tall som skal være random.


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Setter opp variabler for tekstfelt som gir effekt dersom den endrer seg
        setText = (TextView)findViewById(R.id.answer);
        setText.addTextChangedListener(answerTextWatcher);

        initialise();

    }


    /**************************************
     * activity start functions
     **************************************/

    //Start på aktiviteten dersom siden starter, RANDOM regnestykke kommer.
    public void initialise(){
        arrayCalculation = getResources().getStringArray(R.array.calculation);
        TextView calcQuestion = (TextView)findViewById(R.id.textCalculation);
        calcQuestion.setText(arrayCalculation[n]);
    }


    //TextWatcher som gjør endringer på knappene dersom tekstfeltene endrer seg
    private TextWatcher answerTextWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Button btnAnswer = (Button)findViewById(R.id.btnAnswer);
            String checkInput = setText.getText().toString();

            if(checkInput.equals("")){
                btnAnswer.setEnabled(false);
            }else{
                btnAnswer.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



    /**************************************
     * Button functions
     **************************************/

    // Funksjon som setter inn nummer-verdi i TextView
    public void setNumber(int tall){
        if(gamePlay){
            String num = String.valueOf(tall);
            textAnswer = textAnswer + num;
            TextView answer = (TextView)findViewById(R.id.answer);
            answer.setText(textAnswer);
            Log.d("VALGT TALL", textAnswer);
        }
    }


    //Funksjon som validerer tekstboksen der personen setter svar
    public void maxNumber(){
        int checkNumber = textAnswer.length();
        if(checkNumber == 2) {
            Log.d("ALERT", "MAX 2 TALL");
            gamePlay = false;
        }
    }




    /**************************************
     * Different buttonClicks on APP
    **************************************/

    //Forskjellige buttons til å svare på regnestykker
    public void buttonClick(View v){
        switch (v.getId()){
            case R.id.btn1 :
                setNumber(1);
                maxNumber();
                break;
            case R.id.btn2 :
                setNumber(2);
                maxNumber();
                break;
            case R.id.btn3 :
                setNumber(3);
                maxNumber();
                break;
            case R.id.btn4 :
                setNumber(4);
                maxNumber();
                break;
            case R.id.btn5 :
                setNumber(5);
                maxNumber();
                break;
            case R.id.btn6 :
                setNumber(6);
                maxNumber();
                break;
            case R.id.btn7 :
                setNumber(7);
                maxNumber();
                break;
            case R.id.btn8 :
                setNumber(8);
                maxNumber();
                break;
            case R.id.btn9 :
                setNumber(9);
                maxNumber();
                break;
            case R.id.btn0 :
                setNumber(0);
                maxNumber();
                break;
            default:
                break;
        }
    }


    //Button som sletter tallene fra svar TextView
    public void buttonClear(View v){
        gamePlay = true;
        textAnswer = "";
        TextView answer = (TextView)findViewById(R.id.answer);
        answer.setText(textAnswer);

        Log.d("UPDATE", "SLETTET TALL");
    }



    //Button som bekrefter ditt svar
    public void buttonAnswer(View v){
        arrayAnswer = getResources().getStringArray(R.array.correctAnswer);
        currentAnswer = Integer.valueOf(arrayAnswer[n]);
        myAnswer = Integer.valueOf(textAnswer);

        if(currentAnswer == myAnswer){
            Log.d("FASIT", "Riktig!");
            Toast.makeText(this, "RIKTIG SVAR", Toast.LENGTH_SHORT).show();
        }else{
            Log.d("FASIT", "Feil!");
            Toast.makeText(this, "FEIL SVAR", Toast.LENGTH_SHORT).show();
        }

        Log.d("DITT SVAR", String.valueOf(myAnswer));
        Log.d("FASIT SVAR", String.valueOf(currentAnswer));
    }



}
