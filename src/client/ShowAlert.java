package client;
import javafx.scene.control.Alert;

public class ShowAlert {
    public static void alert(String m1, String m2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(m1);
        alert.setHeaderText(m2);
        alert.setContentText("");
        alert.showAndWait();
    }
}
