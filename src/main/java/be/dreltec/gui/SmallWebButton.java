package be.dreltec.gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.gui.SmallWebButton">Peter Geuens</a>
 */
public class SmallWebButton extends JButton
{
  public SmallWebButton()
  {
    super();
    setSmallLook();
  }

  public SmallWebButton( Action action )
  {
    super(action);
    setSmallLook();
  }

  public SmallWebButton( Icon icon )
  {
    super(icon);
    setSmallLook();
  }

  public SmallWebButton( String string, Icon icon )
  {
    super(string, icon);
    setSmallLook();
  }

  public SmallWebButton( String text )
  {
    super(text);
    setSmallLook();
  }

  public SmallWebButton( String text, ActionListener actionListener )
  {
    super(text);
    setSmallLook();
    addActionListener(actionListener);
  }

  public SmallWebButton( String text, String actionCommand )
  {
    super(text);
    this.setActionCommand(actionCommand);
    setSmallLook();
  }



  public void setSmallLook()
  {
    Insets horzmargin = new Insets(0, 0, 0, 0);
    setMargin(horzmargin);
//    setBackground(Color.white);
//    setBorder(new WebBorder(1, 1, 1, 1));
  }
}
