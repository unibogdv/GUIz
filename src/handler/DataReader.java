package handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Legge il contenuto dei vari file di testo che contengono, domande, risposte, livello di difficoltà,
// punteggio di ciascuna domanda, il numero di domande a cui rispondere.
// Le informazioni verranno elaborate nel GameController.

public class DataReader {

	private static final String QUESTIONS_PATH = "src/database/questions.txt";
	private static final String ANSWERS_PATH = "src/database/answers.txt";
	private	static final String LEVELS_PATH = "src/database/levels.txt";
	private static final String DIFFICULTY_PATH = "src/database/difficulty.txt";
	private static final String AMOUNT_OF_QUESTIONS_PATH = "src/database/amount.txt";
	private static final String POINTS_PATH = "src/database/points.txt";

	private static final File questionsFile = new File(QUESTIONS_PATH);
	private static final File answersFile = new File(ANSWERS_PATH);
	private static final File levelsFile = new File(LEVELS_PATH);
	private static final File difficultyFile = new File(DIFFICULTY_PATH);
	private static final File amountOfQuestionsFile = new File(AMOUNT_OF_QUESTIONS_PATH);
	private static final File pointsFile = new File(POINTS_PATH);

	public static ArrayList<String> readQuestions() { return getStrings(questionsFile); }
	public static ArrayList<String> readAnswers() { return getStrings(answersFile); }
	public static ArrayList<String> readLevels(){ return getStrings(levelsFile); }
	public static String readDifficulty(){
		return readLine(difficultyFile);
	}
	public static String readAmountOfQuestions(){
		return readLine(amountOfQuestionsFile);
	}
	public static String readPoints(){
		return readLine(pointsFile);
	}


	private static ArrayList<String> getStrings(File questionsFile) {
		ArrayList<String> questions = new ArrayList<>();

		try(Scanner reader = new Scanner(questionsFile)) {
			while(reader.hasNextLine()) {
				String q = reader.nextLine();
				questions.add(q);
			}
		} catch(IOException ex) {
			System.out.println(ex);
		}
		return questions;
	}


	private static String readLine(File file) {
		String value = "";
		try(Scanner reader = new Scanner(file)) {
			String line = reader.nextLine();
			value = line;
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return value;
	}
}
