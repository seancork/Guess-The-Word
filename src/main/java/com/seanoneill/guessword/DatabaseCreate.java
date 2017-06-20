package com.seanoneill.guessword;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseCreate extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1; //used if needed to upgrade database
	private static final String DATABASE_NAME = "guessTheWord"; // Name for database
	private static final String TABLE_QUEST = "pictureTable"; //table name

	//Colums for the database table
	private static final String COL_ID = "id";
	private static final String COL_QUESTION = "question"; // Questions
	private static final String COL_CORRECT_ANS = "CorrectAns"; //correct answer

	private SQLiteDatabase mcqDB; //  database
	public DatabaseCreate(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		mcqDB=db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_QUESTION
				+ " TEXT, " + COL_CORRECT_ANS + " TEXT)";
		db.execSQL(sql);
		addQuestions();
	}
	// Questions for the database
	private void addQuestions() {
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Question q1 = new Question("beach", "beach");

		this.addQuestion(q1);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		Question q2 = new Question("Eiffel Tower", "Eiffel Tower");

		this.addQuestion(q2);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		Question q3 = new Question("forrest", "forrest");
		this.addQuestion(q3);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		Question q4 = new Question("school", "school");
		this.addQuestion(q4);

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		Question q5 =  new Question("house", "house");
		this.addQuestion(q5);
	}

	// If database needs to be upgraded eg new tables, colums
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		onCreate(db);
	}

	// Adding new question to the database table
	public void addQuestion(Question quest) {
		ContentValues values = new ContentValues();
		values.put(COL_QUESTION, quest.getQUESTION());
		values.put(COL_CORRECT_ANS, quest.getCORRECT_ANSWER());

		mcqDB.insert(TABLE_QUEST, null, values);
	}

	public List<Question> getAllQuestions(){
		List<Question> quesList = new ArrayList<Question>();
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		mcqDB = this.getReadableDatabase();
		Cursor cursor = mcqDB.rawQuery(selectQuery, null);

		 // Find where Question, answers and correct answers are in table
		if (cursor.moveToFirst()) {
			do{
				Question quest = new Question();
			    quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setCORRECT_ANSWER(cursor.getString(2));
				quesList.add(quest);
			}while (cursor.moveToNext());
		}
		return quesList;
	}
}
