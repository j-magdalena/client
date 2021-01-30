package client;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Mainmenu implements Initializable {
    private final Socket s = Main.sock();
    private ListView<String> tag_names;
    SceneLoader sceneLoader = new SceneLoader();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        OutputStream os = null;
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

        String msg = "Get feed\n";
        try {
            os.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int tags;
        String tag_num = null;
        try {
            tag_num = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(tag_num);
        tags = Integer.parseInt(tag_num);

        for(int i=0; i<tags; i++) {
            String names = null;
            try {
                names = reader.readLine();
            } catch (IOException e) { e.printStackTrace(); }
            System.out.println(names);
            tag_names.getItems().add(names);
        }

    }

    public void post(MouseEvent mouseEvent) { sceneLoader.loadScene(SceneLoader.SCENE.POST);
    }

    public void createTag(MouseEvent mouseEvent) { sceneLoader.loadScene(SceneLoader.SCENE.NEW_TAG);
    }

    public void getTags(MouseEvent mouseEvent) { sceneLoader.loadScene(SceneLoader.SCENE.GET_TAGS);
    }

    public void unsubscribe(MouseEvent mouseEvent) { sceneLoader.loadScene(SceneLoader.SCENE.UNSUB);
    }

    public void loadTags(MouseEvent mouseEvent) {
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
}
