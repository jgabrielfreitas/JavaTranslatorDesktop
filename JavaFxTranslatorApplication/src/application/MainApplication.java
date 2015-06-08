package application;

import file.FileManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApplication extends Application {
	
	// /home/jgabrielfreitas/Área de Trabalho/document.txt
	
	protected Stage primaryStage;
    private AnchorPane rootLayout;
    TextField filePath;
    Button loadFile;
    protected static String newline = System.getProperty("line.separator");
    
    public MainApplication() {
	}
    
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tradutor de arquivos");
		showRootLayout(primaryStage,"mainapplication.fxml");
	}

	protected void showRootLayout(Stage stage, String xmlName) {
		
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(xmlName));
            rootLayout = (AnchorPane) loader.load();
            stage.setResizable(false);

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
            
            populeViews(rootLayout);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	protected void populeViews(AnchorPane rootLayout) {
		
		loadFile = (Button)    rootLayout.lookup("#load_file_button");
		filePath = (TextField) rootLayout.lookup("#file_path_edit_text");
		
		loadFile.setOnAction(e->{
			
			String filePathToRead = filePath.getCharacters().toString();
				
			if(filePathToRead.length() <= 3) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Ooops...");
				alert.setContentText("O caminho de arquivo está vazio." + newline +"Por favor, escreva um caminho válido.");
				alert.show();
				return;
			}
			
			String fileAsString = null;
			
			try {
				
				FileManager fileManager = new FileManager(filePathToRead);
				fileAsString = fileManager.loadFileByPath();
				
				if(fileAsString != null) {
					ShowFileApplication showFileApplication = new ShowFileApplication(primaryStage, fileManager);
					showFileApplication.start(primaryStage);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				
			}
			
		});
	}

	public static void main(String[] args) {
        launch(args);
    }

}
