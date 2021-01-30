package client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneLoader {

        public enum SCENE{
            START, MAIN_MENU, REGISTER, NEW_TAG, GET_TAGS, POST, UNSUB
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
                case NEW_TAG -> { file = "newtag.fxml";//create tag
                }
                case GET_TAGS -> { file = "Gettags.fxml";//explore new tags
                }
                case POST -> { file = "post.fxml";
                }
                case UNSUB -> { file = "unsubscribe.fxml";
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
            } catch (IOException e) {
                e.printStackTrace();
            }

            User.stage.setTitle("Publish-subscribe service");
            User.stage.setScene(new Scene(root, 600, 400));
            User.stage.show();
        }

}
