import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Driver extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Stocks Now");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setResizable(false);
        primaryStage.show();


        //Sometime cause slow down....  Once the user clicks (X), all threads will be killed
        primaryStage.setOnHiding(event -> Platform.runLater(() -> {
            System.out.println("Application Closed by click to Close Button(X)");
            System.exit(0);
        }));



    }

    public static void main(String[] args){
        launch(args);

    }



}
