package client;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Unsubscribe {
    private final Socket s = Main.sock();
    public ListView<String> tag_feed = new ListView<>();
    public TextField to_unsub;
    SceneLoader sceneLoader = new SceneLoader();

    public void start(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.R)
            try {

            tag_feed.getItems().clear();
            OutputStream os = s.getOutputStream();
            String msg = "Get feed\n";
            os.write(msg.getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String forint = reader.readLine();
            int num = Integer.parseInt(forint);
            String names;
            names = reader.readLine();// = reader.readLine();
            tag_feed.getItems().add(names);

        }catch (IOException e){ System.out.println("error"); }

    }


    public void unsubscribe(MouseEvent mouseEvent) throws IOException {
        OutputStream os = null;
        os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String sub = "Unsubscribe\n";
        os.write(sub.getBytes());
        String serverMessage = reader.readLine();
        System.out.println(serverMessage);

        sub = to_unsub.getText()+"\n";

        os.write(sub.getBytes());
        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        if(serverMessage.equals("Unubscribtion succesfull")){
            ShowAlert.alert(serverMessage, "You have successfully unsubscribed a tag");
        }else{
            ShowAlert.alert(serverMessage, "You typed unexisting tag or one that you don't subscribe");
        }

    }

    public void back(MouseEvent mouseEvent) {sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
    }

}
