package client;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Mainmenu implements Initializable {
    private final Socket s = Main.sock();
    public ListView<String> messages_list;
    public ListView<String> feed_view;
    public ListView<String> all_tags;
    public TextField create_tag;
    public TextField post_tag;
    public TextField post_message;
    public TextField to_subscribe;
    public TextField to_unsubscribe;
    public Label welcome;
    SceneLoader sceneLoader = new SceneLoader();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        String u = StartMenu.getUsername();
        welcome.setText("Hello "+u+"!");
        try {
            feed_view.getItems().clear();
            OutputStream os = s.getOutputStream();
            String msg = "Get feed\n";
            os.write(msg.getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String names;
            names = reader.readLine();
            String [] test = names.split("\t");
            for(int i=1; i<=Integer.parseInt(test[0]); i++) {
                feed_view.getItems().add(test[i]);
            } }catch (IOException e){ System.out.println("error"); }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            all_tags.getItems().clear();
            OutputStream os = s.getOutputStream();
            String msg = "Get tags\n";
            os.write(msg.getBytes());

            String names;
            names = reader.readLine();
            String [] test = names.split("\t");
            for(int i=1; i<=Integer.parseInt(test[0]); i++) {
                all_tags.getItems().add(test[i]);
            }}catch (IOException e){ System.out.println("error"); }

    }
    public void post(MouseEvent mouseEvent) throws IOException {
        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String u = StartMenu.getUsername();

        String newm = "New message\n";
        os.write(newm.getBytes());
        String serverMessage = reader.readLine();
        System.out.println(serverMessage);

        os.write((post_tag.getText()+"\t").getBytes());
        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        os.write((u+": "+post_message.getText()+"\t").getBytes());
        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        if(serverMessage.equals("Message created")) {
            ShowAlert.alert("Message posted!", "Message  " + post_message.getText() + " posted!");
        }
        else{
            ShowAlert.alert("Something went wrong", "Message " + post_message.getText() + " not posted, make sure tour tag name was correct or if you have permission");
        }
        sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
    }

    public void createTag(MouseEvent mouseEvent) throws IOException {
        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String newt = "New tag\n";
        os.write(newt.getBytes());
        String serverMessage = reader.readLine();
        System.out.println(serverMessage);

        os.write((create_tag.getText()+ "\t").getBytes());
        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        if(serverMessage.equals("tag created")){
            ShowAlert.alert("New tag created","You have successfully created a new tag!");
        }else{
            ShowAlert.alert("Tag not created","Tag already exists, choose different name");
        }
        sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
    }

    public void subscribe(MouseEvent mouseEvent) throws IOException {
        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String sub = "Subscribe\n";
        os.write(sub.getBytes());

        String serverMessage = reader.readLine();
        System.out.println(serverMessage);

        sub = to_subscribe.getText()+"\t";
        os.write(sub.getBytes());

        serverMessage = reader.readLine();
        System.out.println(serverMessage);
        if(serverMessage.equals("Subscribtion succesfull")){
            ShowAlert.alert(serverMessage, "You have successfully subscribed a tag");
        }else{
            ShowAlert.alert(serverMessage, "You typed unexisting tag or one that you already subscribe");
        }
        sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
    }

    public void unsubscribe(MouseEvent mouseEvent) throws IOException {
        OutputStream os = null;
        os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String sub = "Unsubscribe\n";
        os.write(sub.getBytes());
        String serverMessage = reader.readLine();
        System.out.println(serverMessage);

        sub = to_unsubscribe.getText()+"\t";
        os.write(sub.getBytes());
        serverMessage = reader.readLine();
        System.out.println(serverMessage);

        if(serverMessage.equals("Unubscribtion succesfull")){
            ShowAlert.alert(serverMessage, "You have successfully unsubscribed a tag");
        }else{
            ShowAlert.alert(serverMessage, "You typed unexisting tag or one that you don't subscribe");
        }
        sceneLoader.loadScene(SceneLoader.SCENE.MAIN_MENU);
    }
    public void logout(MouseEvent mouseEvent) throws IOException {
        OutputStream os = s.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String loginin = "Logout\n";
        os.write(loginin.getBytes());
        String serverMessage = reader.readLine();
        System.out.println(serverMessage);
        sceneLoader.loadScene(SceneLoader.SCENE.START);
        System.exit(0);
    }

    public void showfromview(MouseEvent mouseEvent) throws IOException {

        messages_list.getItems().clear();
        OutputStream os = s.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String feed = "Load tag\n";
        os.write(feed.getBytes());

        String serverMessage = reader.readLine();
        System.out.println(serverMessage);
        String load = feed_view.getSelectionModel().getSelectedItem() + "\t";
        os.write(load.getBytes());

        String mess = reader.readLine();
        String [] test = mess.split("\t");
        System.out.println(serverMessage);
        for(int i=1; i<=Integer.parseInt(test[0]); i++) {
            messages_list.getItems().add(test[i]);
        }

    }
}

