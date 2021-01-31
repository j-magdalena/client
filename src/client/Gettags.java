package client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Gettags {
    private final Socket s = Main.sock();
    public TextField to_sub;
    public ListView<String> tag_names;
    SceneLoader sceneLoader = new SceneLoader();

    public void start(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.R)
            try {
                tag_names.getItems().clear();
                OutputStream os = s.getOutputStream();
                String msg = "Get tags\n";
                os.write(msg.getBytes());
                BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String forint = reader.readLine();
                int num = Integer.parseInt(forint);
                String names;
                names = reader.readLine();// = reader.readLine();
                tag_names.getItems().add(names);
                //while((names = reader.readLine())!=null)
                //{ tag_names.getItems().add(names);}

            }catch (IOException e){ System.out.println("error"); }

    }


    public void back(MouseEvent mouseEvent) {
        sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
    }


    public void subscribe(MouseEvent mouseEvent) throws IOException {

        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String sub = "Subscribe\n";
        os.write(sub.getBytes());

        String serverMessage = reader.readLine();
        System.out.println(serverMessage);

        sub = to_sub.getText()+"\n";

        os.write(sub.getBytes());

        serverMessage = reader.readLine();
        System.out.println(serverMessage);
        if(serverMessage.equals("Subscribtion succesfull")){
            ShowAlert.alert(serverMessage, "You have successfully subscribed a tag");
        }else{
            ShowAlert.alert(serverMessage, "You typed unexisting tag or one that you already subscribe");
        }

        //sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);

    }
}