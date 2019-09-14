package com.example.mathapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class PlayActivity extends AppCompatActivity {
    TextView setAnswer;         //TextView til tekstfeltet der svarerene kommer opp dersom spilleren svarer
    TextView setPoints;         //TextView til tekstfeltet der poengen vises
    TextView setTask;           //TextView til tekstfeltet som viser hvilken oppgave
    TextView setNumTask;
    TextView calcQuestion;      //TextView til tekstfeltet der oppgaven som vises
    Button btnAnswer;           //Button for dersom spilleren bekredter svaret sitt
    String textAnswer;          //String til tekstfeltet der man svarer, med tom string verdi
    boolean gamePlay;           //Boolean til hele spillet, der den starter med true og kjører
    List<String> arrayCalculation; //ArrayList til å sette inn ulike regenstykker
    List<String> arrayAnswer;       //ArrayList til å sette inn svarene til regnestykkene.
    List<Integer> noRepeatNum;  //ArrayList som setter inn antall oppgaver og sletter oppgavene
    Random randomIndex;         //Variabel som gir tilfeldig tall
    Integer n;                  //Gir begrenset tall som skal være random.
    Integer currentAnswer;      //Integer som gir svaret til regnestykke fra tilfeldig svar fra arrayAnswer
    Integer myAnswer;           //Integer som spillerern svarer
    Integer playerPoints;       //Integer som viser spillerens poeng
    Integer thisTask;           //Integer som viser hvilken oppgave spilleren er på
    Integer sizeTask;           //Integer som viser antall oppgaver
    Integer getNumTask;

    SharedPreferences dataSave;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        gamePlay = true;

        arrayCalculation = Arrays.asList(getResources().getStringArray(R.array.calculation));
        calcQuestion = (TextView)findViewById(R.id.textCalculation);

        btnAnswer = (Button)findViewById(R.id.btnAnswer);
        setAnswer = (TextView)findViewById(R.id.answer);

        setNumTask = (TextView)findViewById(R.id.textNumberOfTask);
        setTask = (TextView)findViewById(R.id.textThisTask);
        thisTask = 1;

        //Setter opp variabler for tekstfelt som gir effekt dersom den endrer seg
        setAnswer.addTextChangedListener(answerTextWatcher);

        //Setter opp poeng 0 dersom aktiviteten starter
        setPoints = (TextView)findViewById(R.id.textPlayerPoints);
        playerPoints = 0;

        textAnswer = ""; //Starter med at tekstfeltet er tom


        dataSave = getSharedPreferences("saveGame", Context.MODE_PRIVATE);
        getNumTask = dataSave.getInt("numTask", 0);
        setNumTask.setText(String.valueOf(getNumTask));


        randomIndex = new Random();  //Setter tilfeldige tall nå aktiviteten starter

        numOfCalc();  //Funksjon som sletter repeterte tall i matteoppgaven

        randomCalc(); //Kjører funksjonen der det inneholder tilfeldige tall;

    }




    /**************************************
     * activity start functions
     **************************************/

    //Start på aktiviteten dersom siden starter, RANDOM regnestykke kommer.
    public void randomCalc(){
        n = randomIndex.nextInt(noRepeatNum.size());
        calcQuestion.setText(arrayCalculation.get(n));
        Log.d("NY OPPGAVE:", "Oppgave nr."+ n + ", " + arrayCalculation.get(n));

        Log.d("Oppgave igjen:", String.valueOf(noRepeatNum.size()));

        for(int i = 0; i < noRepeatNum.size(); i++){
            Log.d("TALL", String.valueOf(noRepeatNum.get(i)));
        }

    }

    //Funksjon som setter inn antall oppgaver i array slik at man kan slette seinere
    public void numOfCalc(){
        sizeTask = arrayCalculation.size();
        noRepeatNum = new ArrayList<Integer>(sizeTask);
        for(int i = 0; i < sizeTask; i++){
            noRepeatNum.add(i);
        }
    }


    //TextWatcher som gjør endringer på knappene dersom tekstfeltene endrer seg
    private TextWatcher answerTextWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            String checkInput = setAnswer.getText().toString();

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

            String firstNum = textAnswer.substring(0,1);
            int zeroFirst = Integer.valueOf(firstNum);

            if(zeroFirst == 0){
                textAnswer = num;
                setAnswer.setText(textAnswer);
                Log.d("VALGT TALL1", textAnswer);
                Log.d("DANGER", "null FØRST!");
            }else{
                setAnswer.setText(textAnswer);
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

    //Funksjon som pusser bort svarene til spilleren
    public void resetTextCalc(){
        gamePlay = true;
        textAnswer = "";
        setAnswer.setText(textAnswer);
    }



    /**************************************
     * Different buttonClicks on APP
    **************************************/

    //Forskjellige buttons til å svare på regnestykker
    public void buttonClickNum(View v){
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
        arrayAnswer = Arrays.asList(getResources().getStringArray(R.array.correctAnswer));
        currentAnswer = Integer.valueOf(arrayAnswer.get(n));
        myAnswer = Integer.valueOf(textAnswer);

        if(currentAnswer == myAnswer){
            correctAnswer();
            nextTask();
            noRepeatNum.remove(n);
            randomCalc();
            resetTextCalc();
            Log.d("FASIT", "Riktig!");
            Toast.makeText(this, "RIKTIG SVAR", Toast.LENGTH_SHORT).show();

        }else{
            nextTask();
            noRepeatNum.remove(n);
            randomCalc();
            resetTextCalc();
            Log.d("FASIT", "Feil!");
            Toast.makeText(this, "FEIL SVAR", Toast.LENGTH_SHORT).show();

        }

        Log.d("DITT SVAR", String.valueOf(myAnswer));
        Log.d("FASIT SVAR", String.valueOf(currentAnswer));
    }


    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);
        builder.setMessage("Alt lagret spill vil bli borte. Er du sikker på at du vil avslutte?");
        builder.setCancelable(true);
        builder.setNegativeButton("Nei", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    /**************************************
     *  Function RESULT of the game
     **************************************/

    //Funksjon som gir spilleren poeng dersom svaret er korrekt
    public void correctAnswer(){
        playerPoints = playerPoints + 1;
        String points = String.valueOf(playerPoints);
        setPoints.setText(points);

        Log.d("Poeng", points);
    }

    //Funksjon som viser oversikt over en ny oppgave
    public void nextTask(){
        thisTask = thisTask + 1;
        String task = String.valueOf(thisTask);
        setTask.setText(task);
    }


    /**************************************
     * save stations
     **************************************/

    /*
    public void onPause(){
        super.onPause();
        SharedPreferences.Editor editor = playerSave.edit();
        editor.putString("Calcis", calcQuestion.getText().toString());
        editor.putString("Answer", setAnswer.getText().toString());
        editor.commit();
        Log.d("Status", "PAUSE");
    }

    public void onResume(){
        super.onResume();
        playerSave = getSharedPreferences("saveGame", Context.MODE_PRIVATE);
        String saveCalc = playerSave.getString("Calcis", "");
        String saveAnswer = playerSave.getString("Answer", "");
        calcQuestion.setText(saveCalc);
        setAnswer.setText(saveAnswer);
        Log.d("Status", "FORTSETTER");
    }

         */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("playerAnswer", textAnswer);
        outState.putString("currentAnswer", setAnswer.getText().toString());
        outState.putString("currentCalc", calcQuestion.getText().toString());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textAnswer = savedInstanceState.getString("currentAnswer");
        setAnswer.setText(savedInstanceState.getString("playerAnswer"));
        calcQuestion.setText(savedInstanceState.getString("currentCalc"));

    }
}
