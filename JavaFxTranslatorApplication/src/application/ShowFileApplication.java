package application;

import com.google.api.translate.Language;

import file.FileManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowFileApplication extends MainApplication {
	
    TextArea fileOutPut;
    Stage primaryStage;
    FileManager fileManager;
    protected static Button translate;
    Button saveFile;
    Button languageButton;
    Text textYouCanEdit;
    static Language languadeToTranslate = null;
    
    public ShowFileApplication() {
	}
	
	public ShowFileApplication(Stage primaryStage, FileManager fileManager) {
		this.primaryStage = primaryStage;
		this.fileManager = fileManager;
	}

	public void start(Stage primaryStage) throws Exception {
		super.showRootLayout(primaryStage, "show_file_application.fxml");
	}
	
	protected void populeViews(AnchorPane rootLayout) {
		
		fileOutPut 	   = (TextArea) rootLayout.lookup("#file_output_edit_text");
		textYouCanEdit = (Text)   rootLayout.lookup("#edit_text");
		saveFile   	   = (Button) rootLayout.lookup("#save_button");
		translate  	   = (Button) rootLayout.lookup("#translate_button");
		languageButton = (Button) rootLayout.lookup("#language_button");
		fileOutPut.setText(fileManager.loadFileByPath());
		textYouCanEdit.setText("Caso queira editar seu arquivo, basta escrever ou apagar o que desejar" + newline + "e em seguida aperte o botao \"Salvar\".");
		
		languageButton.setOnAction(e->{
			
			LanguageSelectorApplication selector = new LanguageSelectorApplication(new Stage(), null);
			try {
				selector.start(new Stage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
		
		saveFile.setOnAction(e->{
			
			if (fileManager.saveEditedFile(fileOutPut.getText()) == true) {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Edição");
				alert.setContentText("Seu arquivo foi editado com sucesso!");
				alert.show();
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Ooops...");
				alert.setContentText("Desculpe, este arquivo não pode ser editado.");
				alert.show();
			}
		});
		
		translate.setOnAction(e -> {

			try {
				
				ProcessingApplication processing = new ProcessingApplication(primaryStage, fileManager, fileOutPut.getText(), languadeToTranslate);
				processing.start(primaryStage);
				
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
	}

}
