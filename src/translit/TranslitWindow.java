package translit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class TranslitWindow implements ActionListener {

  //Text areas access to
  private JTextArea originalTextArea;
  private JTextArea translitTextArea;

  //Constructor
  TranslitWindow() {

    //Set main window parameters
    JFrame frame = new JFrame("Translit");
    frame.setSize(500, 350);
    frame.setLocation(300, 300);
    frame.setLayout(new GridLayout(7, 1));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(true);

    // Set frame logo
    try {
      URL resource = frame.getClass()
          .getResource("/icons8-one-way-road-sign-48.png");
//      System.out.println(resource);
      if (resource != null) {
        BufferedImage image = ImageIO.read(resource);
        frame.setIconImage(image);
      }
    } catch (HeadlessException | IOException e) {
      e.printStackTrace();
    } //End Try-Catch

    //Set fonts
    Font headerFont = new Font("Arial Black", Font.BOLD, 21);
    Font textFieldFont = new Font("monospace", Font.PLAIN, 18);

    //Set original clipboard field
    originalTextArea = new JTextArea(1, 50);
    originalTextArea.setFont(textFieldFont);

    //Set translit field
    translitTextArea = new JTextArea(1, 50);
    translitTextArea.setFont(textFieldFont);

    //Create labels
    JLabel headerLabel = new JLabel("Translit from the clipboard", JLabel.CENTER);
    headerLabel.setFont(headerFont);
    JLabel originalTextLabel = new JLabel("Original Text", JLabel.CENTER);
    JLabel translitTextLabel = new JLabel("Translit text", JLabel.CENTER);

    //Set buttons
    JButton getClipboardButton = new JButton("Get clipboard");
    getClipboardButton.addActionListener(this);
    getClipboardButton.setActionCommand("Get");

    JButton translitButton = new JButton("Translit");
    translitButton.setActionCommand("Translit");
    translitButton.addActionListener(this);

    JButton clearButton = new JButton("Clear all fields");
    clearButton.setActionCommand("Clear");
    clearButton.addActionListener(this);

    JButton copyToClipboardButton = new JButton("Copy to clipboard");
    copyToClipboardButton.addActionListener(this);
    copyToClipboardButton.setActionCommand("Copy");

    //Create a panel
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());

    //Add buttons to panel
    controlPanel.add(getClipboardButton);
    controlPanel.add(translitButton);
    controlPanel.add(clearButton);

    //Add the panel to the main Frame
    frame.add(headerLabel);
    frame.add(originalTextLabel);
    frame.add(originalTextArea);
    frame.add(BorderLayout.CENTER, controlPanel);
    frame.add(translitTextLabel);
    frame.add(translitTextArea);
    frame.add(copyToClipboardButton);

    //Set frame visible
    frame.setVisible(true);


  } // End of Constructor

  //Copy string from the clipboard to originalTextArea
  private void setOriginalTextArea(String textFromClipboard) {
    originalTextArea.setText("");
    originalTextArea.insert(textFromClipboard, 0);
  }

  //clear all text areas
  private void clearTextAreas() {
    originalTextArea.setText("");
    translitTextArea.setText("");
  }

  //Getting the clipboard
  private String getClipboard(){
    return TranslitClipboard.getClipBoard();
  }

  private void setTranslitTextArea(){
    translitTextArea.setText("This function not ready yet");
  }

  //Get content of translit area
  private String getTranslitText(){
    return translitTextArea.getText();
  }

  //Copy text to clipboard
  private void copyToClipboard(){
    TranslitClipboard.setClipboard(getTranslitText());
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    //Read the action command
    String actionCommand = e.getActionCommand();
    switch (actionCommand) {
      case "Clear":
        clearTextAreas();
        break;
      case "Get":
        setOriginalTextArea(getClipboard());
        break;
      case "Translit":
        setTranslitTextArea();
        break;
      case "Copy":
        copyToClipboard();
        break;
    }
  }
}
