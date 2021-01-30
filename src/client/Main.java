package client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Main extends Application {
    private static Socket clientSocket;

    static {
        try {
            clientSocket = new Socket("localhost", 12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Main() throws IOException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        InputStream is = clientSocket.getInputStream();
        byte[] buffer = new byte[100];
        is.read(buffer);
        System.out.println(is);
        User.stage=primaryStage;
        SceneLoader sceneLoader = new SceneLoader();
        sceneLoader.loadScene(SceneLoader.SCENE.START);
    }

    public static Socket sock(){
        return clientSocket;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
