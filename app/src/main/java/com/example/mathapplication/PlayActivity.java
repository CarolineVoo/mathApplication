package com.example.mathapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Random;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class PlayActivity extends AppCompatActivity {
    TextView setText;           //TextView til tekstfeltet der man svarer, uten verdi
    TextView setPoints;         //TextView til tekstfeltet der poengen vises
    TextView setTask;           //TextView til tekstfeltet som viser hvilken oppgave
    String textAnswer;          //String til tekstfeltet der man svarer, med tom string verdi
    boolean gamePlay = true;    //Boolean til hele spillet, der den starter med true og kjører
    String[] arrayCalculation;  //Array til å sette inn ulike regenstykker
    String[] arrayAnswer;       //Array til å sette inn svarene til regnestykkene.
    Random randomIndex;         //Variabel som gir tilfeldig tall
    Integer n;                  //Gir begrenset tall som skal være random.
    Integer currentAnswer;      //Integer som gir svaret til regnestykke fra tilfeldig svar fra arrayAnswer
    Integer myAnswer;           //Integer som spillerern svarer
    Integer playerPoints;       //Integer som viser spillerens poeng
    Integer thisTask;           //Integer som viser hvilken oppgave spilleren er på



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        setTask = (TextView)findViewById(R.id.textThisTask);
        thisTask = 1;

        //Setter opp variabler for tekstfelt som gir effekt dersom den endrer seg
        setText = (TextView)findViewById(R.id.answer);
        setText.addTextChangedListener(answerTextWatcher);

        //Setter opp poeng 0 dersom aktiviteten starter
        setPoints = (TextView)findViewById(R.id.textPlayerPoints);
        playerPoints = 0;

        textAnswer = ""; //Starter med at tekstfeltet er tom

        randomIndex = new Random();  //Setter tilfeldige tall nå aktiviteten starter

        randomCalc(); //Kjører funksjonen der det inneholder tilfeldige tall;

    }


    /**************************************
     * activity start functions
     **************************************/

    //Start på aktiviteten dersom siden starter, RANDOM regnestykke kommer.
    public void randomCalc(){
        arrayCalculation = getResources().getStringArray(R.array.calculation);
        TextView calcQuestion = (TextView)findViewById(R.id.textCalculation);
        n = randomIndex.nextInt(25);
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

    // Funksjon som setter inn nummer-verdi i TextView og validerer med tall 0 først
    public void setNumber(int tall){
        if(gamePlay){
            String num = String.valueOf(tall);
            textAnswer = textAnswer + num;
            TextView answer = (TextView)findViewById(R.id.answer);

            String firstNum = textAnswer.substring(0,1);
            int zeroFirst = Integer.valueOf(firstNum);

            if(zeroFirst == 0){
                textAnswer = num;
                answer.setText(textAnswer);
                Log.d("VALGT TALL1", textAnswer);
                Log.d("DANGER", "null FØRST!");
            }else{
                answer.setText(textAnswer);
                Log.d("VALGT TALL2", textAnswer);
            }
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

    public void resetTextCalc(){
        gamePlay = true;
        textAnswer = "";
        TextView answer = (TextView)findViewById(R.id.answer);
        answer.setText(textAnswer);
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
        resetTextCalc();
        Log.d("UPDATE", "SLETTET TALL");
    }


    //Button som bekrefter ditt svar
    public void buttonAnswer(View v){
        arrayAnswer = getResources().getStringArray(R.array.correctAnswer);
        currentAnswer = Integer.valueOf(arrayAnswer[n]);
        myAnswer = Integer.valueOf(textAnswer);

        if(currentAnswer == myAnswer){
            correctAnswer();
            nextTask();
            randomCalc();
            resetTextCalc();
            Log.d("FASIT", "Riktig!");
            Toast.makeText(this, "RIKTIG SVAR", Toast.LENGTH_SHORT).show();

        }else{
            nextTask();
            randomCalc();
            resetTextCalc();
            Log.d("FASIT", "Feil!");
            Toast.makeText(this, "FEIL SVAR", Toast.LENGTH_SHORT).show();

        }

        Log.d("DITT SVAR", String.valueOf(myAnswer));
        Log.d("FASIT SVAR", String.valueOf(currentAnswer));
    }


    /**************************************
     *  Function RESULT of the game
     **************************************/

    public void correctAnswer(){
        playerPoints = playerPoints + 1;
        String points = String.valueOf(playerPoints);
        setPoints.setText(points);

        Log.d("Poeng", points);
    }

    public void nextTask(){
        thisTask = thisTask + 1;
        String task = String.valueOf(thisTask);
        setTask.setText(task);
    }

}
