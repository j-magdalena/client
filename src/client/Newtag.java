package client;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Newtag {
    private final Socket s = Main.sock();
    public TextField name;
    SceneLoader sceneLoader = new SceneLoader();
    public void create(MouseEvent mouseEvent) throws IOException {

        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String newt = "New tag\n";
        os.write(newt.getBytes());
        String serverMessage = reader.readLine();
        System.out.println(serverMessage);

        os.write((name.getText()+ "\n").getBytes());
        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        if(serverMessage.equals("tag created")){
            ShowAlert.alert("New tag created","You have successfully created a new tag!");
        }else{
            ShowAlert.alert("Tag not created","Tag already exists, choose different name");
        }



    }

    public void back(MouseEvent mouseEvent) {sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
    }
}
