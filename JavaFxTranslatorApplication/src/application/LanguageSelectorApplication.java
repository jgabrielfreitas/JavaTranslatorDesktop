package application;

import com.google.api.translate.Language;

import file.FileManager;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LanguageSelectorApplication extends ShowFileApplication {

	protected Stage primaryStage;
	ToggleGroup toggleGroup;
	RadioButton portuguese;
    RadioButton english;
    RadioButton spanish;
    RadioButton russian;
	
	public LanguageSelectorApplication(Stage primaryStage, FileManager fileManager) {
		super(primaryStage, fileManager);
	}
	
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		showRootLayout(primaryStage, "language_selector.fxml");
	}
	
	protected void populeViews(AnchorPane rootLayout) {
		
		toggleGroup = new ToggleGroup();
		
		portuguese = (RadioButton) rootLayout.lookup("#radio_portugues");
	    portuguese.setToggleGroup(toggleGroup);
	    
	    english = (RadioButton) rootLayout.lookup("#radio_ingles");
	    english.setToggleGroup(toggleGroup);
	    
	    spanish = (RadioButton) rootLayout.lookup("#radio_espanhol");
	    spanish.setToggleGroup(toggleGroup);
	    
	    russian = (RadioButton) rootLayout.lookup("#radio_russo");
	    russian.setToggleGroup(toggleGroup);
	    
	    Button closeButton = (Button) rootLayout.lookup("#close_selector");
	    
	    closeButton.setOnAction(e->{
	    	ShowFileApplication.translate.setDisable(false);
	    	ShowFileApplication.languadeToTranslate = getSelectedLanguage((RadioButton) toggleGroup.getSelectedToggle());
	    	primaryStage.close();
	    });
	}
	
	private Language getSelectedLanguage(RadioButton radioButtonSelected) {
		
		if (radioButtonSelected.equals(portuguese))
			return Language.PORTUGUESE;
		else if (radioButtonSelected.equals(english))
			return Language.ENGLISH;
		else if (radioButtonSelected.equals(spanish))
			return Language.SPANISH;
		else
			return Language.RUSSIAN;
	}
}
