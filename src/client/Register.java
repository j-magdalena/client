package client;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Register {
    public TextField login;
    public TextField pwd1;
    SceneLoader sceneLoader = new SceneLoader();
    private final Socket s = Main.sock();
    public void register(MouseEvent mouseEvent) throws IOException {

        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String serverMessage;

        String loginin = "Register\n";
        os.write(loginin.getBytes());

        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        String msg = login.getText() + "\n";
        os.write(msg.getBytes());

        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        String msg2 = pwd1.getText() + "\n";
        os.write(msg2.getBytes());

        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        if(serverMessage.equals("Account created")){
            ShowAlert.alert("Account created","You can log in now.");
            sceneLoader.loadScene(SceneLoader.SCENE.START);

        }

    }

    public void back(MouseEvent mouseEvent) { sceneLoader.loadScene(SceneLoader.SCENE.START);
    }
}
