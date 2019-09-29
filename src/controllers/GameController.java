package controllers;

import handler.DataReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
    Text questionDifficulty;
    @FXML
    Text levelDifficulty;
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
    private ArrayList<String> pointArray;

    private int player = 0;
    private int remainingQuestions = 0;

    private int playerOneScore;
    private int playerTwoScore;

    private String playerOneName = "";
    private String playerTwoName = "";

    private String answerFromArray = "";
    private String answerButtonText = "";

    private String difficultyLevel = "MIX";
    private int amountOfQuestions = 4;
    private boolean pointsForDifficulty = true;

    private Random randomQuestionNumber = new Random();
    private int randomQuestion = 0;

    @FXML
    public void handleGoBack(ActionEvent click) throws IOException {
        switchScene(click, "./src/main_menu.fxml");
    }

    @FXML
    private void handleStartGame(ActionEvent event) {

        // ******* DECLARE VARIABLES ********

        playerOneName = player1.getText(); //Saves the name for later use
        playerTwoName = player2.getText(); //Saves the name for later use

        if (playerOneName.equals("")) {
            playerOneName = "Player 1";
        }
        if (playerTwoName.equals("")) {
            playerTwoName = "Player 2";
        }

        score1.setText(playerOneName + " ha: 0 punti");
        score2.setText(playerTwoName + " ha: 0 punti");

        levelDifficulty.setText("Livello: " + difficultyLevel);

        playerOneScore = 0;
        playerTwoScore = 0;

        questionArray = DataReader.readQuestions();
        answerArray = DataReader.readAnswers();
        levelArray = DataReader.readLevels();
        pointArray = DataReader.readLevels();

        // Sets the difficulty level of Questions
        if (!difficultyLevel.equalsIgnoreCase("Mix")) {
            if (difficultyLevel.equalsIgnoreCase("Facile")) {
                for (int i = levelArray.size() - 1; i >= 0; i--) {
                    if (!levelArray.get(i).equalsIgnoreCase("Facile")) {
                        questionArray.remove(i);
                        answerArray.remove(i);
                        levelArray.remove(i);
                        pointArray.remove(i);
                    }
                }
            }
            if (difficultyLevel.equalsIgnoreCase("Media")) {
                for (int i = levelArray.size() - 1; i >= 0; i--) {
                    if (!levelArray.get(i).equalsIgnoreCase("Media")) {
                        questionArray.remove(i);
                        answerArray.remove(i);
                        levelArray.remove(i);
                        pointArray.remove(i);
                    }
                }
            }
            if (difficultyLevel.equalsIgnoreCase("Difficile")) {
                for (int i = levelArray.size() - 1; i >= 0; i--) {
                    if (!levelArray.get(i).equalsIgnoreCase("Difficile")) {
                        questionArray.remove(i);
                        answerArray.remove(i);
                        levelArray.remove(i);
                        pointArray.remove(i);
                    }
                }
            }
        }

        // Sets the amount of Questions
        if (amountOfQuestions != 0 && questionArray.size() > amountOfQuestions) {
            for (int i = questionArray.size()-1; i >= amountOfQuestions; i--){
                questionArray.remove(i);
                answerArray.remove(i);
                levelArray.remove(i);
                pointArray.remove(i);
            }
        }

        // Modifies the array pointArray with the amount of points based on the Questions difficulty
        if (pointsForDifficulty == true) {
            for (int i = pointArray.size() - 1; i >= 0; i--) {
                if (pointArray.get(i).equalsIgnoreCase("FACILE")) {
                    pointArray.set(i, "1");
                } else if (levelArray.get(i).equalsIgnoreCase("MEDIA")) {
                    pointArray.set(i, "2");
                } else if (levelArray.get(i).equalsIgnoreCase("DIFFICILE")) {
                    pointArray.set(i, "3");
                }
            }
        } else
            for (int i = pointArray.size() - 1; i >= 0; i--) {
                pointArray.set(i, "1");
        }


        player = 1;

        // ****** RETRIEVE AND PRESENT FIRST QUESTION - THIS WILL ONLY BE USED TO START THE PROGRAM ******
        randomQuestion = randomQuestionNumber.nextInt(questionArray.size()); // GENERATES A RANDOM NUMBER TO PICK A RANDOM QUESTION FROM ARRAYLIST
        statusText.setText("Domanda: " + questionArray.get(randomQuestion)); // PRINTS THE RANDOM QUESTION TO THE UI

        questionDifficulty.setText("Domanda: " + levelArray.get(randomQuestion)); // This works fine

        currentPlayer.setText("Giocatore corrente: "+playerOneName);
        remainingQuestions = questionArray.size()-1;
        questionsLeft.setText(remainingQuestions + " domande mancanti");

        buttonFalse.setDisable(false);
        buttonTrue.setDisable(false);
        startGame.setDisable(true);
    }

    @FXML
    private void handleButtonAnswer(ActionEvent event) {
        Button answerButtonClicked = (Button) event.getSource();
        answerButtonText = answerButtonClicked.getText();
        answerFromArray = answerArray.get(randomQuestion); // PICKS THE ANSWER MATCHING THE QUESTION GENERATED BY THE RANDOM NUMBER

        int point = Integer.parseInt(pointArray.get(randomQuestion));

            if (answerFromArray.equals(answerButtonText)) {
                statusText.setText("Corretto! Hai guadagnato +"+ point +" !");
                if (player == 1) {
                    playerOneScore = playerOneScore + point;
                    score1.setText(playerOneName + " ha: " + playerOneScore + " punti");
                } else if (player == 2) {
                    playerTwoScore = playerTwoScore + point;
                    score2.setText(playerTwoName + " ha: " + playerTwoScore + " punti");
                }
            } else {
                statusText.setText("Sbagliato! Nessun punto.");
            }

        nextRound.setDisable(false);
        buttonFalse.setDisable(true);
        buttonTrue.setDisable(true);
    }

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
        levelArray.remove(randomQuestion); // REMOVES LEVEL FROM ARRAYLIST
        pointArray.remove(randomQuestion); // REMOVES POINT FROM ARRAYLIST
        remainingQuestions = questionArray.size()-1;
        questionsLeft.setText(remainingQuestions + " domande mancanti");

        if (questionArray.size()!=0) {
            randomQuestion = randomQuestionNumber.nextInt(questionArray.size());
            statusText.setText("Domanda: " + questionArray.get(randomQuestion)); // PRINTS QUESTION TO THE UI
            questionDifficulty.setText("Domanda: " +levelArray.get(randomQuestion)); // Works! Print difficulty
            nextRound.setDisable(true);
            buttonFalse.setDisable(false);
            buttonTrue.setDisable(false);
        } else {
            if (playerOneScore > playerTwoScore) {
                questionsLeft.setText("Game Over. Ha vinto: "+ playerOneName);
            } else if (playerTwoScore > playerOneScore){
                questionsLeft.setText("Game Over. Ha vinto: "+ playerTwoName);
            } else {
                questionsLeft.setText("La partita è finita in pareggio! ");
            }

            nextRound.setDisable(true);
            startGame.setDisable(false);
            buttonFalse.setDisable(true);
            buttonTrue.setDisable(true);
        }
    }
}
