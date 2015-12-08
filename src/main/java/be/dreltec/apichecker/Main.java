package be.dreltec.apichecker;

import be.dreltec.gui.GridPanel;
import be.dreltec.gui.MyInternalFrame;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.apichecker.Main">Peter Geuens</a>
 */

public class Main extends JFrame implements ActionListener
{

  private JDesktopPane desktop;

  public Main()
  {
    super("Main");

    //Make the big window be indented 50 pixels from each edge
    //of the screen.
    int inset = 50;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(inset, inset,
              screenSize.width-inset*2,
              screenSize.height-inset*2);

    //Set up the GUI.
    desktop = new JDesktopPane(); //a specialized layered pane
    setJMenuBar(createMenuBar());
    createFrame(new CheckPanel()); //create first "window"
    setContentPane(desktop);

    //Make dragging a little faster but perhaps uglier.
    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
  }

  protected JMenuBar createMenuBar()
  {
    JMenuBar menuBar = new JMenuBar();

    //Set up the lone menu.
    JMenu menu = new JMenu("Document");
    menu.setMnemonic(KeyEvent.VK_D);
    menuBar.add(menu);

    //Set up the first menu item.
    JMenuItem menuItem = new JMenuItem("New");
    menuItem.setMnemonic(KeyEvent.VK_N);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_N, ActionEvent.ALT_MASK));
    menuItem.setActionCommand("new");
    menuItem.addActionListener(this);
    menu.add(menuItem);

    //Set up the second menu item.
    menuItem = new JMenuItem("Quit");
    menuItem.setMnemonic(KeyEvent.VK_Q);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_Q, ActionEvent.ALT_MASK));
    menuItem.setActionCommand("quit");
    menuItem.addActionListener(this);
    menu.add(menuItem);

    //gathers the open windows
    windowsMenu = new JMenu("Windows");
    windowsMenu.setMnemonic(KeyEvent.VK_W);
    menuBar.add(windowsMenu);




    return menuBar;
  }
  private JMenu windowsMenu;

  //React to menu selections.
  public void actionPerformed( ActionEvent e )
  {
    if ( "new".equals(e.getActionCommand()) )
      { //new
      createFrame(new CheckPanel());
      }
    else
      { //quit
      System.exit(0);
      }
  }

  //Create a new internal frame.
  protected void createFrame(JPanel panel)
  {
    final MyInternalFrame frame = new MyInternalFrame();
    frame.setVisible(true); //necessary as of 1.3
    desktop.add(frame);
    final JMenuItem menuItem = new JMenuItem(frame.getTitle());
    windowsMenu.add(menuItem);
    frame.addPropertyChangeListener(new PropertyChangeListener()
    {
      @Override
      public void propertyChange( PropertyChangeEvent evt )
      {
        if ( evt.getPropertyName().equals(JInternalFrame.TITLE_PROPERTY))
          menuItem.setText((String) evt.getNewValue());
      }
    });
    menuItem.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed( ActionEvent e )
      {
        try
          {
          frame.setSelected(true);
          }
        catch (PropertyVetoException e1)
          {
          e1.printStackTrace();
          }
      }
    });
    try
      {
      frame.setSelected(true);
      frame.getContentPane().add(panel);
      }
    catch (java.beans.PropertyVetoException e)
      {
      }
  }

  /**
   * Create the GUI and show it.  For thread safety,
   * this method should be invoked from the
   * event-dispatching thread.
   */
  private static void createAndShowGUI()
  {
    //Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    Main frame = new Main();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    //Display the window.
    frame.setVisible(true);
  }

  public static void main( String[] args )
  {
    try
      {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
    catch (Exception e)
      {
      e.printStackTrace();
      }
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        createAndShowGUI();
      }
    });
  }


}