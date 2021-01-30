package client;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.Socket;

public class Unsubscribe {
    private final Socket s = Main.sock();
    public ListView<String> tag_names;
    public TextField to_unsub;
    SceneLoader sceneLoader = new SceneLoader();

    public void unsubscribe(MouseEvent mouseEvent) {
    }

    public void back(MouseEvent mouseEvent) {sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
    }
}
