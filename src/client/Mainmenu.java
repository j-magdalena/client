package client;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Mainmenu {//implements Initializable {
    private final Socket s = Main.sock();
    public ListView<String> messages_list;
    public TextField for_load;
    SceneLoader sceneLoader = new SceneLoader();

    public void post(MouseEvent mouseEvent) { sceneLoader.loadScene(SceneLoader.SCENE.POST);
    }

    public void createTag(MouseEvent mouseEvent) { sceneLoader.loadScene(SceneLoader.SCENE.NEW_TAG);
    }

    public void getTags(MouseEvent mouseEvent) { sceneLoader.loadScene(SceneLoader.SCENE.GET_TAGS);
    }

    public void unsubscribe(MouseEvent mouseEvent) { sceneLoader.loadScene(SceneLoader.SCENE.UNSUB);
    }
    public void logout(MouseEvent mouseEvent) throws IOException {
        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String loginin = "Logout\n";
        os.write(loginin.getBytes());
        String serverMessage = reader.readLine();
        System.out.println(serverMessage);
        sceneLoader.loadScene(SceneLoader.SCENE.START);
    }

    public void showMessages(MouseEvent mouseEvent) throws IOException {
        messages_list.getItems().clear();
        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String feed = "Load tag\n";
        os.write(feed.getBytes());
        
        String serverMessage = reader.readLine();
        System.out.println(serverMessage);
        
        String load = for_load.getText()+"\n";
        os.write(load.getBytes());

        //String forint = reader.readLine();
        // int num = Integer.parseInt(forint);

        serverMessage = reader.readLine();
        System.out.println(serverMessage);
        messages_list.getItems().add(serverMessage);

    }
}

