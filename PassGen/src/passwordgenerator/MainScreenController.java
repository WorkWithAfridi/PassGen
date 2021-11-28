/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordgenerator;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Khond
 */

public class MainScreenController implements Initializable {

    @FXML
    private RadioButton NumberRb;
    @FXML
    private RadioButton AlphabetRb;
    @FXML
    private Text NoticeTf;
    @FXML
    private TextField PasswordTf;
    @FXML
    private TextField LengthTf;
    @FXML
    private Text HintTf;
    @FXML
    private TextField SiteTf;
    @FXML
    private TextField UserTf;
    @FXML
    private TextArea ShowTf;
    @FXML
    private Text Notice2;
    @FXML
    private Text Notice3;
    @FXML
    private Text HintTf2;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void copyToClipBoard()
    {
        String ctc = PasswordTf.getText();
        StringSelection stringSelection = new StringSelection(ctc);
        java.awt.datatransfer.Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }
    
    private static char rndChar(){
        Random rand= new Random();
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+-=*/><.,;:'[]{}";
        char letter=alphabet.charAt(rand.nextInt(alphabet.length()));
        return letter;
    }
    
    private void Reset()
    {
        NoticeTf.setText("Notice *Must include atleast one of the \"Include\" variants and a proper Password Length*");
        PasswordTf.setText("");
        NumberRb.setSelected(false);
        AlphabetRb.setSelected(false);
        HintTf.setText("");
        Notice2.setText("");
        Notice3.setText("");
        Notice2.setText("");
        Notice3.setText("");
        LengthTf.setText("");
        ShowTf.setText("");
        SiteTf.setText("");
        UserTf.setText("");
        HintTf2.setText("");
    }
    
    @FXML
    private void GenerateBtn(ActionEvent event) throws Exception {
        NoticeTf.setText("Notice *Must include atleast one of the \\\"Include\\\" variants and a proper Password Length*");
        HintTf.setText("");
        if(LengthTf.getText().isEmpty() || parseInt(LengthTf.getText())<1 || parseInt(LengthTf.getText())>50)
        {
            NoticeTf.setText("                    **Password Length cannot be Empty, Zero or Greater than 50. :/ **"); return;
        }
        int len = parseInt(LengthTf.getText());
        int NRb=0,ARb=0;
        String password;
        
        if(NumberRb.isSelected()) { NRb=1; }
        if(AlphabetRb.isSelected()) { ARb=1; }
        if(NRb==0 && ARb==0) { NoticeTf.setText("**Please select atleast one of the \"Include\" attributes. And Try again. :) **"); return; };
        if(NRb==1 && ARb!=1)
        {
            Random rand= new Random();
            int RN=rand.nextInt(10);
            password=String.valueOf(RN);
            for(int i=1; i<len; i++)
            {
                RN = rand.nextInt(10);
                password=(password+RN);
            }
            PasswordTf.setText(password);
            HintTf.setText("HINT: Click on the generated password to copy it to clipboard. :) ");
            return;
        }
        if(ARb==1 && NRb!=1)
        {
            password=String.valueOf(rndChar());
            for(int i=1; i<len; i++)
            {
                password=password+String.valueOf(rndChar());
            }
            PasswordTf.setText(password);
            HintTf.setText("HINT: Click on the generated password to copy it to clipboard. :) ");
            return;
        }
        if(ARb==1 && NRb==1)
        {
            Random rand= new Random();
            int RN=rand.nextInt(2);
            if(RN==1)
            {
                password=String.valueOf(rand.nextInt(10));
                for(int i=1; i<len; i++)
                {
                    RN=rand.nextInt(2);
                    if(RN==1)
                    {
                        RN = rand.nextInt(10);
                        password=(password+RN);
                    }
                    if(RN==0)
                    {
                        password=password+String.valueOf(rndChar());
                    }
                }
                PasswordTf.setText(password);
                HintTf.setText("HINT: Click on the generated password to copy it to clipboard. :) ");
                return;
            } 
            if(RN==0)
            {
                password=String.valueOf(rndChar());
                for(int i=1; i<len; i++)
                {
                    RN=rand.nextInt(2);
                    if(RN==1)
                    {
                        RN = rand.nextInt(10);
                        password=(password+RN);
                    }
                    if(RN==0)
                    {
                        password=password+String.valueOf(rndChar());
                    }
                }
                PasswordTf.setText(password);
                HintTf.setText("HINT: Click on the generated password to copy it in clipboard. :) ");
            }
        }
        
    }

    @FXML
    private void ResetBtn(ActionEvent event) {
        Reset();
    }

    @FXML
    private void PasswordClick(MouseEvent event) {
        copyToClipBoard();
    } //On clicking the password, it gets copied.

    @FXML
    private void SaveBtn(ActionEvent event) throws IOException {
        Notice2.setText("");
        Notice3.setText("");
        
        if(UserTf.getText().isEmpty() || SiteTf.getText().isEmpty() || PasswordTf.getText().isEmpty())
        {
            Notice2.setText("Site, User name or Password field cannot be empty!");
            return; 
        }
        String User = UserTf.getText();
        String Site = SiteTf.getText();
        String Pass = PasswordTf.getText();
        
        File file = new File("build/data/UserData.txt");
        if(!file.exists())
        {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bf = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bf);
        
        pw.println(Site+".com");
        pw.println(User);
        pw.println(Pass);
        
        pw.close();
        fw.close();
        Notice2.setText("Password Saved! :) ");
        
    }

    @FXML
    private void SearchBtn(ActionEvent event) throws FileNotFoundException {
        if(UserTf.getText().isEmpty() || SiteTf.getText().isEmpty()  )
        {
            Notice3.setText("       Site or Username cannot be empty! ");
            return;
        }
        
        String User = UserTf.getText();
        String Site = SiteTf.getText()+".com";
        
        Scanner x;
        File file = new File("build/data/UserData.txt");
        if(!file.exists())
        {
            Notice3.setText("Please save some passwords first");
            return;
        }
        x = new Scanner(file);
        while(x.hasNextLine())
        {
            String siteF = x.nextLine();
            String userF = x.nextLine();
            String passF = x.nextLine();
            
            if(siteF.equals(Site))
            {
                if(userF.equals(User))
                {
                    ShowTf.setText("Site Name: "+siteF+"\n" +"User Name: "+userF+"\n" + "Password: "+passF);
                    PasswordTf.setText(passF);
                    HintTf2.setText("HINT: Click on password to save it in Clipboard. :) ");
                    return;
                }
                else 
                {
                    continue;
                }
            }
            
        }
        ShowTf.setText("No user found! :( ");
        
    }

    @FXML
    private void ShowTfMouseClick(MouseEvent event) {
        copyToClipBoard();
    }

    @FXML
    private void LengthOnClick(MouseEvent event) {
        //Reset();
    }
    
}
//Created by Khondakar Afridi x) 
//Follow me on youtube at https://www.youtube.com/channel/UCSddE6uoibb1ddiULSvypJg