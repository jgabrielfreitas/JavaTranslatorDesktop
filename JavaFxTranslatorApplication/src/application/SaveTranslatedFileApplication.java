package application;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.google.api.translate.Language;

import file.FileManager;

public class SaveTranslatedFileApplication extends ProcessingApplication {

	Stage primaryStage;
	FileManager fileManager;
	String textTranslated;
	TextArea originalTextArea;
	TextArea translatedTextArea;
	Button saveTranslated;
	Language languageTranslated;
	
	public SaveTranslatedFileApplication(Stage primaryStage,
			FileManager fileManager, String textTranslated,
			Language languageTranslated) {
		this.primaryStage = primaryStage;
		this.fileManager = fileManager;
		this.textTranslated = textTranslated;
		this.languageTranslated = languageTranslated;
	}
	
	public void start(Stage primaryStage) throws Exception {
		super.showRootLayout(this.primaryStage, "save_translated_file.fxml");
		this.primaryStage.setTitle("Finalizar");
	}
	
	protected void populeViews(AnchorPane rootLayout) {
		
		originalTextArea   = (TextArea) rootLayout.lookup("#original_edit_text");
		translatedTextArea = (TextArea) rootLayout.lookup("#translated_edit_text");
		saveTranslated 	   = (Button)   rootLayout.lookup("#save_button");
		
		originalTextArea.setText(fileManager.loadFileByPath());
		translatedTextArea.setText(textTranslated);
		
		saveTranslated.setOnAction(e->{
			
			try {

				fileManager.createFile(textTranslated, languageTranslated);
				MainApplication main = new MainApplication();
				main.start(new Stage());
				this.primaryStage.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
	}

}
