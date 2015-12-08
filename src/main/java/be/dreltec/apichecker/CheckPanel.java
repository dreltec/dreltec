package be.dreltec.apichecker;

import be.dreltec.gui.GridPanel;

import javax.swing.*;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.apichecker.Main">Peter Geuens</a>
 */

public class CheckPanel extends GridPanel
{

  public CheckPanel()
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
    addRow(getLabel("header component"));
    setWeightsX(1.0);
    setWeightY(1.0);
    setFills(GridBagConstraints.BOTH);
    addRow(makeTabbedPane());
    setWeightY(0.0);
    addTab("tab1");
    startGrid();
    addRow(getLabel("row1_1"));
    addRow(getLabel("row1_2"));
    addFillerRow();
    addTab("tab2");
    startGrid();
    addRow(getLabel("row2_1"));
    addRow(getLabel("row2_2"));
    addFillerRow();
    stopTabbedPane();
    addRow(getLabel("footer component"));
  }

  @SuppressWarnings("MagicNumber")
  public static void main( String[] args )
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        JDesktopPane desktop = new JDesktopPane();

        CheckPanel demo = new CheckPanel();
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