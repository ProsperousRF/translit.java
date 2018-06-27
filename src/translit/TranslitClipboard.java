package translit;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

class TranslitClipboard {

  //Get text from system clipboard
  static String getClipBoard(){
    try {
      return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
      e.printStackTrace();
    }
    return "";
  }

  //insert text to clipboard
  static void setClipboard(String clipboardText){
    StringSelection selection = new StringSelection(clipboardText);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(selection, selection);
  }
}
