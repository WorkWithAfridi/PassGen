/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Khond
 */
public class PasswordGenerator extends Application {
    
    @Override
    public void start(Stage stage) throws Exception{
        Parent root=FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene= new Scene(root);
        stage.setMinWidth(949);
        stage.setMaxWidth(949);
        stage.setMinHeight(535);
        stage.setMaxHeight(535);
        stage.setTitle("Passgen v1.00");
        stage.setScene(scene);
        stage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
