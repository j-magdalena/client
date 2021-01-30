package client;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class StartMenu {
    public TextField login;
    public TextField passwd;
    private final Socket s = Main.sock();

    SceneLoader sceneLoader = new SceneLoader();
    public void goLogin(MouseEvent mouseEvent) throws IOException, InterruptedException {

        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String serverMessage;

            String loginin = "Login\n";
            os.write(loginin.getBytes());
            serverMessage = reader.readLine();
            System.out.println(serverMessage);

            String msg = login.getText() + "\n";
            os.write(msg.getBytes());
            serverMessage = reader.readLine();
            System.out.println(serverMessage);

            String msg2 = passwd.getText() + "\n";
            os.write(msg2.getBytes());


            serverMessage = reader.readLine();
            System.out.println(serverMessage);
        if(!serverMessage.equals("Logged in")){
            ShowAlert.alert("Wrong login info","Please input correct login info or register a new account.");
        }
        else{
            sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
        }

    }

    public void goRegister(MouseEvent mouseEvent) { sceneLoader.loadScene(SceneLoader.SCENE.REGISTER);
    }
}
