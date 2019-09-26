package read;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {

	private static final String QUESTIONS_PATH = "./src/database/questions.txt";
	private static final String ANSWERS_PATH = "./src/database/answers.txt";
	private	static final String LEVELS_PATH = "./src/database/levels.txt";
	private static final String ADMIN_INFO_PATH = "./src/database/answers.txt";

	private static final File questionsFile = new File(QUESTIONS_PATH);
	private static final File answersFile = new File(ANSWERS_PATH);
	private static final File levelsFile = new File(LEVELS_PATH);
	private static final File adminInfoFile = new File(ADMIN_INFO_PATH);

	public static ArrayList<String> readQuestions() {

		return getStrings(questionsFile);
	}

	public static ArrayList<String> readAnswers() {

		return getStrings(answersFile);
	}

	public static ArrayList<String> readLevels(){

		return getStrings(levelsFile);
	}

	private static ArrayList<String> getStrings(File questionsFile) {
		ArrayList<String> questions = new ArrayList<>();

		try(Scanner reader = new Scanner(questionsFile)) {
			while(reader.hasNextLine()) {
				String q = reader.nextLine();
				questions.add(q);
			}
		}catch(IOException ex) {
			System.out.println(ex);
		}

		return questions;
	}
	
}
