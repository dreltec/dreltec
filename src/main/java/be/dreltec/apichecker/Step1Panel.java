package be.dreltec.apichecker;

import be.dreltec.gui.GridPanel;
import be.dreltec.gui.SmallWebButton;

import javax.swing.*;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.apichecker.Main">Peter Geuens</a>
 */

public class Step1Panel extends GridPanel
{

  public Step1Panel()
  {
    createComponents();
    layoutComponents();
  }

  public void createComponents()
  {
  }

  public void layoutComponents()
  {
    startGrid();
    addRow(getLabel("Auto:") , getTextArea("", 5, 50), new SmallWebButton("\\/"));
    addRow(getLabel("Api:" ) , getTextField(50)      , new SmallWebButton("?" ));
    addRow(getLabel("vCode:"), getTextField(50)      , new SmallWebButton("?"));
    setFills(GridBagConstraints.EAST);
    addRow(new SmallWebButton("Parse"));
    addFillerRow();
    addFillerCol();
  }

  @SuppressWarnings("MagicNumber")
  public static void main( String[] args )
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {

        Step1Panel demo = new Step1Panel();
        JFrame frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(demo);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        Rectangle rectangle = frame.getBounds();
        rectangle.setSize(screenWidth, screenHeight);
        frame.setBounds(0, 0, screenWidth, screenHeight);
        frame.setSize(screenWidth, screenHeight);
//        getFrame().doLayout();
//        getFrame().validate();
//        updateUI();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }
}