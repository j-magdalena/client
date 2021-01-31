package client;
import javafx.stage.Stage;

public class User {
    private static SceneLoader sceneLoader;
    public static Stage stage;
    static SceneLoader getSceneLoader(){
        if(sceneLoader ==null){
            sceneLoader = new SceneLoader();
        }
        return sceneLoader;
    }
}
