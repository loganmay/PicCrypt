/**
 * Hide messages inside of picture by slightly modifying the alpha value of certain pixels.
 * 
 * @author Logan May
 * 
 */
import java.util.*;
import java.io.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.*;


public class PicCrypt {
  
  public static BufferedImage getBufferedImage(String path) {
    
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      System.out.println("Could not read path");
    }
    return img;
  }
  
  public static JFrame makeFrame(String title) {
    JFrame frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    return frame;
  }
  
  public static void main(String args[]) {
    
    // Initialize JFrame
    JFrame frame = makeFrame("Image");
    
    // Initialize BufferedImage for original picture
    BufferedImage original = getBufferedImage("matrix.jpg");
    
    // Get it's height and width
    int width = original.getWidth();
    int height = original.getHeight();
    
    // Initialize a buffered image to hold the picture containing the message
    BufferedImage containsMessage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
    
    // ----
    containsMessage.getGraphics().drawImage(original, 0, 0, null);
    
    // Get a string and turn it into an array of characters
    String input = "ATTACKATDAWN";
    char[] message = new char[input.length()];
    for (int i = 0; i < input.length(); i++) {
      message[i] = input.charAt(i);
    }
    
    
    // For each pixel in the first row of the picture, subtract the (int) value of the corresponding char from the
    // message array.  Effectively, we're storing the value of the char in the alpha value of that pixel
    for (int x = 0; x < input.length(); x++) {
      int y = 0;
      int color = original.getRGB(x,y);
      int alphaLess = (color & 0x00ffffff);
      
      int alpha = (color & 0xff000000) >>> 24;
      alpha -= (int) message[x];
      
      int newalpha = (alpha << 24) | alphaLess;
      containsMessage.setRGB(x,y,newalpha);
    }
    
    // Go through the pixels in the first row, get the (int) value of the char hidden in the alpha value of the pixel
    // and turn it back into a string
    StringBuilder sb = new StringBuilder();
    for (int x = 0; x < input.length(); x++) {
      int y = 0;
      int color = containsMessage.getRGB(x,y);
      int alpha = (color & 0xff000000) >>> 24;
      int charValue = 255 - alpha;
      sb.append((char) charValue);
    }
    // Print the string
    System.out.println(sb.toString());

    // Now prove that you can barely see a difference in the picture by printing the picture to the JFrame
    
    // Initialize ImageIcon
    ImageIcon icon = new ImageIcon(containsMessage);
    
    // Initialize JLabel from icon
    JLabel label = new JLabel(icon);
    
    // Add label to frame
    frame.add(label);
    
    // Show frame
    frame.pack();
    
  }
}