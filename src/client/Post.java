package client;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Post {
    private final Socket s = Main.sock();
    public TextField tag_name;
    public TextField mess;
    SceneLoader sceneLoader = new SceneLoader();
    public void post(MouseEvent mouseEvent) throws IOException {
        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String newm = "New message\n";
        os.write(newm.getBytes());
        String serverMessage = reader.readLine();
        System.out.println(serverMessage);

        os.write(tag_name.getText().getBytes());
        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        os.write(mess.getText().getBytes());
        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        if(serverMessage.equals("Message created")) {
            ShowAlert.alert("Message posted!", "Message" + mess.getText() + " posted!");
        }
        else{
            ShowAlert.alert("Something went wrong", "Message" + mess.getText() + " not posted, make sure tour tag name was correct");
        }

    }

    public void back(MouseEvent mouseEvent) {sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
    }
}
