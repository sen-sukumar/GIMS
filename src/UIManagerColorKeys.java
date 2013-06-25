import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 * Generate a list of UIManager color keys.
 */
public class UIManagerColorKeys
{
  public static void main(String[] args) throws Exception
  {
      UIDefaults ui = UIManager.getLookAndFeel().getDefaults();
 
for ( Object o : ui.keySet() ){
	System.out.println(o.toString());
}

  }
}