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

    private String difficultyLevel = DataReader.readDifficulty();
    private int amountOfQuestions = Integer.parseInt(DataReader.readAmountOfQuestions());
    private boolean pointsForDifficulty = Boolean.parseBoolean(DataReader.readPoints());

    private Random randomQuestionNumber = new Random();
    private int randomQuestion = 0;

    @FXML
    public void handleGoBack(ActionEvent click) throws IOException {
        switchScene(click, "src/main_menu.fxml");
    }

    @FXML
    private void handleStartGame() {

        // Memorizza il nome dei giocatori
        playerOneName = player1.getText();
        playerTwoName = player2.getText();

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

        // Popola gli Array sfruttando il DataReader per la lettura dei file.
        questionArray = DataReader.readQuestions(); // Contiene tutte le domande.
        answerArray = DataReader.readAnswers(); // Contiene tutte le risposte.
        levelArray = DataReader.readLevels(); // Contiene tutti i livelli di difficoltà.
        pointArray = DataReader.readLevels(); // Questo Array verrà manipolato per assegnare i punti in base al livello.

        // Imposta il livello di difficoltà delle domande in base a quanto definito nelle "Impostazioni".
        // Scansiona il levelArray dal fondo e rimuove tutte ciò che è diverso rispetto alla difficoltà prescelta,
        // poi rimuove gli elementi con lo stesso indice anche dagli altri array, per tenerli tutti "allineati".
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

        // Imposta il numero di domande a cui rispondere, a condizione che il valore sia
        // diverso da zero e che il numero inserito non sia più grande della dimensione dell'array.
        // La scansione parte sempre dal fondo dell'array ed il ciclo eliminerà da tutti gli array tante
        // domande fino a raggiungere il valore scelto dall'utente.
        if (amountOfQuestions != 0 && questionArray.size() > amountOfQuestions) {
            for (int i = questionArray.size() - 1; i >= amountOfQuestions; i--){
                questionArray.remove(i);
                answerArray.remove(i);
                levelArray.remove(i);
                pointArray.remove(i);
            }
        }

        // Modifica il contenuto di pointArray, popolato con i vari "Facile, Media, Difficile",
        // assegnando un valore da 1 a 3 a seconda della difficoltà della domanda. Se l'utente non
        // avrà selezionato l'opzione punti bonus "Si" nelle "Impostazioni" allora il pointArray verrà
        // settato con i tutti i valori a 1.
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

        player = 1; // Inizia il primo giocatore, ovviamente. Ready Player One ;-)

        // Gestisce e mostra la prima domanda, questa parte di codice verrà utilizzata solo per avviare il programma,
        // dopodichè i turni verranno gestiti da handleButtonNext, fino alla proclamazione del vincitore (o del pareggio).
        randomQuestion = randomQuestionNumber.nextInt(questionArray.size()); // Genera un numero casuale per estrarre una domanda a caso.
        statusText.setText(questionArray.get(randomQuestion) + "\nVERO o FALSO?"); // Mostra la domanda a cui rispondere.

        questionDifficulty.setText("Domanda: " + levelArray.get(randomQuestion)); // Mostra la difficoltà della domanda.

        currentPlayer.setText("Giocatore corrente: " + playerOneName);
        remainingQuestions = questionArray.size() - 1; // Aggiorna il contatore con il numero di domande mancanti,
        questionsLeft.setText(remainingQuestions + " domande mancanti"); // e lo mostra a video settandolo .setText

        buttonFalse.setDisable(false);
        buttonTrue.setDisable(false);
        startGame.setDisable(true);
    }

    @FXML // Gestisce il funzionamento dei tasti per le risposte, i turni di risposta, vari messaggi di stato.
    private void handleButtonAnswer(ActionEvent event) {

        Button answerButtonClicked = (Button) event.getSource();
        answerButtonText = answerButtonClicked.getText();

        // Recupera il valore della domanda generata a caso con Random
        // in modo che vi sia corrispondenza con la risposta.
        // Il metodo .get(randomQuestion) è stato sfruttato all'interno del
        // programma in varie parti al fine di tenere tutti i risultati estratti
        // dagli array allineati e coerenti fra loro.
        answerFromArray = answerArray.get(randomQuestion);

        int point = Integer.parseInt(pointArray.get(randomQuestion)); // Assegna alla variabile point il punteggio.

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
                statusText.setText("Sbagliato! Nessun punto!");
            }

        // Dopo ogni turno di domande i tasti vengono abilitati/disabilitati per il turno successivo.
        nextRound.setDisable(false);
        buttonFalse.setDisable(true);
        buttonTrue.setDisable(true);
    }

    @FXML // Gestisce il funzionamento del tasto "prossima domanda", il giocatore corrente, vari messaggi di stato, gli array, etc.
    private void handleButtonNext() {

        if (player == 1) {
            player = 2;
            currentPlayer.setText("Giocatore corrente: " + playerTwoName);
        } else if (player == 2){
            player = 1;
            currentPlayer.setText("Giocatore corrente: " + playerOneName);
        }

        questionArray.remove(randomQuestion); // Rimuove la domanda a cui si è già risposto dall'array.
        answerArray.remove(randomQuestion); // Rimuove la risposta a fini di allineamento degli array.
        levelArray.remove(randomQuestion); // Rimuove il livello a fini di allineamento degli array.
        pointArray.remove(randomQuestion); // Rimuove il punto a  fini di allineamento degli array.
        remainingQuestions = questionArray.size() - 1; // Aggiorna il contatore con il numero di domande mancanti,
        questionsLeft.setText(remainingQuestions + " domande mancanti"); // e lo mostra a video settandolo .setText

        if (questionArray.size() != 0) {
            randomQuestion = randomQuestionNumber.nextInt(questionArray.size());
            statusText.setText(questionArray.get(randomQuestion) + "\nVERO o FALSO?"); // Mostra la domanda a cui rispondere.
            questionDifficulty.setText("Domanda: " + levelArray.get(randomQuestion)); // Mostra la difficoltà della domanda.
            nextRound.setDisable(true);
            buttonFalse.setDisable(false);
            buttonTrue.setDisable(false);
        } else {
            if (playerOneScore > playerTwoScore) {
                questionsLeft.setText("Game Over. Vince: " + playerOneName);
            } else if (playerTwoScore > playerOneScore){
                questionsLeft.setText("Game Over. Vince: " + playerTwoName);
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
