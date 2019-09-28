package handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Initialize the content of amount.txt, difficulty.txt, points.txt

public class DataWriter {

	private static final String DIFFICULTY_PATH = "./src/database/difficulty.txt";
	private static final String AMOUNT_OF_QUESTIONS_PATH = "./src/database/amount.txt";
	private static final String POINTS_PATH = "./src/database/points.txt";

	private static final File difficultyFile = new File(DIFFICULTY_PATH);
	private static final File amountOfQuestionsFile = new File(AMOUNT_OF_QUESTIONS_PATH);
	private static final File pointsFile = new File(POINTS_PATH);

	PrintWriter outputStream = null;
	 {
		 try {
			 outputStream = new PrintWriter(difficultyFile);
		 } catch (FileNotFoundException ex) {
			 ex.printStackTrace();
		 }
		 outputStream.print("Mix");
		 outputStream.close();

		try {
			outputStream = new PrintWriter(amountOfQuestionsFile);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		outputStream.print("3");
		outputStream.close();

		 try {
			 outputStream = new PrintWriter(pointsFile);
		 } catch (FileNotFoundException ex) {
			 ex.printStackTrace();
		 }
		 outputStream.print("false");
		 outputStream.close();
	}
}
