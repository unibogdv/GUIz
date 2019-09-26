package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import read.DataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class GameController implements SceneSwitch {

    @FXML
    TextField player1;
    @FXML
    TextField player2;
    @FXML
    Text score1;
    @FXML
    Text score2;
    @FXML
    Text statusText;
    @FXML
    Text questionsLeft;
    @FXML
    Text currentPlayer;
    @FXML
    Button startGame;
    @FXML
    Button buttonTrue;
    @FXML
    Button buttonFalse;
    @FXML
    Button nextRound;
    @FXML
    Button goBackFromGame;

    private ArrayList<String> questionArray;
    private ArrayList<String> answerArray;
    private ArrayList<String> levelArray;

    private int player = 0;
    private int remainingQuestions = 0;

    private int playerOneScore;
    private int playerTwoScore;

    private String playerOneName = "";
    private String playerTwoName = "";

    private String answerFromArray = "";
    private String answerButtonText = "";

    private Random randomQuestionNumber = new Random();
    private int randomQuestion = 0;

    @FXML
    public void handleGoBack(ActionEvent click) throws IOException {
        switchScene(click, "./src/main_menu.fxml");
    }

    /**
     * This method will run when the user starts the game.
     * @param event
     */
    @FXML
    private void handleStartGame(ActionEvent event) {

        // ******* DECLARE VARIABLES ********

        playerOneName = player1.getText(); //Saves the name for later use
        playerTwoName = player2.getText(); //Saves the name for later use

        if(playerOneName.equals(""))
        {
            playerOneName="Player 1";
        }
        if(playerTwoName.equals(""))
        {
            playerTwoName="Player 2";
        }

        score1.setText(playerOneName+ " ha: 0 punti");
        score2.setText(playerTwoName+ " ha: 0 punti");

        playerOneScore = 0;
        playerTwoScore = 0;

        questionArray = DataReader.readQuestions();
        answerArray = DataReader.readAnswers();
        levelArray = DataReader.readLevels();

        player = 1;

        // ****** RETRIEVE AND PRESENT FIRST QUESTION - THIS WILL ONLY BE USED TO START THE PROGRAM ******
        randomQuestion = randomQuestionNumber.nextInt(questionArray.size()); // GENERATES A RANDOM NUMBER TO PICK A RANDOM QUESTION FROM ARRAYLIST
        statusText.setText("Domanda: " + questionArray.get(randomQuestion)); // PRINTS THE RANDOM QUESTION TO THE UI

        currentPlayer.setText("Giocatore corrente: "+playerOneName);
        remainingQuestions = questionArray.size()-1;
        questionsLeft.setText(remainingQuestions + " domande mancanti");

        buttonFalse.setDisable(false);
        buttonTrue.setDisable(false);
        startGame.setDisable(true);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonAnswer(ActionEvent event) {
        Button answerButtonClicked = (Button) event.getSource();
        answerButtonText = answerButtonClicked.getText();
        answerFromArray = answerArray.get(randomQuestion); // PICKS THE ANSWER MATCHING THE QUESTION GENERATED BY THE RANDOM NUMBER

        if (answerFromArray.equals(answerButtonText)) {
            statusText.setText("Corretto! Hai guadagnato 1 punto!");
            if(player == 1){
                playerOneScore = playerOneScore+1;
                score1.setText(playerOneName+ " ha: "+playerOneScore+" punti");
            }else if(player == 2){
                playerTwoScore = playerTwoScore+1;
                score2.setText(playerTwoName+ " ha: "+playerTwoScore+" punti");
            }
        }else{
            statusText.setText("Sbagliato! Nessun punto.");
        }

        nextRound.setDisable(false);
        buttonFalse.setDisable(true);
        buttonTrue.setDisable(true);
    }
    /**
     *
     * @param event
     */
    @FXML
    private void handleButtonNext(ActionEvent event) {

        if(player == 1){
            player = 2;
            currentPlayer.setText("Giocatore corrente: "+playerTwoName);
        }else if(player == 2){
            player = 1;
            currentPlayer.setText("Giocatore corrente: "+playerOneName);
        }

        questionArray.remove(randomQuestion); // REMOVES QUESTION FROM ARRAYLIST
        answerArray.remove(randomQuestion); // REMOVES ANSWER FROM ARRAYLIST
        remainingQuestions = questionArray.size()-1;
        questionsLeft.setText(remainingQuestions + " domande mancanti");

        if(questionArray.size()!=0){
            randomQuestion = randomQuestionNumber.nextInt(questionArray.size());
            statusText.setText("Domanda: " + questionArray.get(randomQuestion)); // PRINTS QUESTION TO THE UI
            nextRound.setDisable(true);
            buttonFalse.setDisable(false);
            buttonTrue.setDisable(false);
        }else{
            if(playerOneScore>playerTwoScore){
                questionsLeft.setText("Game over. Il vincitore è: "+playerOneName);
            }else if(playerTwoScore>playerOneScore){
                questionsLeft.setText("Game over. Il vincitore è: "+playerTwoName);
            }else{
                questionsLeft.setText("La partita è finita in pareggio. ");
            }

            nextRound.setDisable(true);
            startGame.setDisable(false);
            buttonFalse.setDisable(true);
            buttonTrue.setDisable(true);
        }
    }
}
