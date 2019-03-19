package UserInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Scene mainWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Polinomes");
        mainWindow = new PolinomeScene(root, 300, 400);
        primaryStage.setScene(mainWindow);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
