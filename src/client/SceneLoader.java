package client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneLoader {

        public enum SCENE{
            START, MAIN_MENU, REGISTER
        }
        private String getFile(SCENE s){
            String file;
            switch (s) {
                case START -> { file = "StartMenu.fxml";
                }
                case MAIN_MENU -> { file = "mainmenu.fxml"; //get feed
                }
                case REGISTER -> { file = "register.fxml";
                }
                default -> { file = null;
                }
            }
            return file;
        }
        public void loadScene(SCENE scene)  {
            String dir = getFile(scene);

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(dir));
                User.stage.setTitle("Publish-subscribe service");
                User.stage.setScene(new Scene(root, 600, 400));
                User.stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

}
