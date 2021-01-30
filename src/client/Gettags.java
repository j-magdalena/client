package client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Gettags implements Initializable {
    private final Socket s = Main.sock();
    public TextField to_sub;
    public ListView <String> tag_names;
    @FXML

    SceneLoader sceneLoader = new SceneLoader();
    OutputStream os = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            os = s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String msg = "Get tags\n";
        try {
            os.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int tag;
        String tag_num = null;
        try {
            tag_num = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(tag_num);
        tag = Integer.parseInt(tag_num);

        for(int i=0; i<tag; i++) {
            String names = null;
            try {
                names = reader.readLine();
            } catch (IOException e) { e.printStackTrace(); }
            System.out.println(names);
            tag_names.getItems().add(names);
        }

    }

    public void back(MouseEvent mouseEvent) {
        sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
    }


    public void subscribe(MouseEvent mouseEvent) throws IOException {
        os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String sub = "Subscribe\n";
        os.write(sub.getBytes());
        String serverMessage = reader.readLine();
        System.out.println(serverMessage);

        sub = to_sub.getText() + "\n";
        os.write(sub.getBytes());
        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        //sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);

    }
}