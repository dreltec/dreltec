package be.dreltec.gui;

import be.dreltec.util.I18NUtil;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.gui.GridPanel">Peter Geuens</a>
 */
public class GridPanel extends JPanel
{

  public static final Color PRIMARY_0 = new Color(1, 64, 109);
  public static final Color PRIMARY_1 = new Color(54, 103, 153);
  public static final Color PRIMARY_2 = new Color(117, 152, 190);
  public static final Color PRIMARY_3 = new Color(180, 202, 228);
  public static final Color PRIMARY_4 = new Color(230, 242, 255);
//  public static final Color primary_0 = Color.BLACK;
//  public static final Color primary_1 = UZBrusselLook.SUPPORT_COLOR1;
//  public static final Color primary_2 = UZBrusselLook.SUPPORT_COLOR2;
//  public static final Color primary_3 = UZBrusselLook.MAIN_COLOR;
//  public static final Color primary_4 = new Color(210, 213, 58);
//  public static final Color primary_5 = new Color(254,254,208);

  public static final Color SECONDARY_1 = new Color(102, 102, 102);
  public static final Color SECONDARY_2 = new Color(153, 153, 153);
  public static final Color SECONDARY_3 = new Color(204, 204, 204);
  public static final Color SECONDARY_4 = new Color(220, 220, 220);


  public static final String YES = i18n("constants.yes");
  public static final String NO = i18n("constants.no");

  protected static final int TASK_BAR_HEIGHT = 50;
  protected static final int MAGIC_NUMBER_20 = 20;

  private GridPanel parentPanel;
  private JTabbedPane tabbedPane;
  private GridPanel currentPanel;

  protected GridPanelConstraints constraints;
  protected int gridX;
  protected int gridY;

  protected int maxGridX = -1;
  protected int maxGridY = -1;

  private double weightY;
  private double[] weightsX = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
  private int[] anchors = new int[]{GridBagConstraints.EAST, GridBagConstraints.WEST, GridBagConstraints.WEST, GridBagConstraints.WEST, GridBagConstraints.WEST, GridBagConstraints.WEST, GridBagConstraints.WEST};
  private int[] fills = new int[]{GridBagConstraints.NONE, GridBagConstraints.BOTH, GridBagConstraints.BOTH, GridBagConstraints.BOTH, GridBagConstraints.BOTH};
  private Insets insets = new Insets(0, 0, 0, 0);

  public static JComponent getDummy()
  {
    return new JPanel();
  }

  public static JLabel getLabel( String text )
  {
    return new JLabel(text);
  }

//  public static JTextComponent getTextComp()
//  {
//    return getTextComp("");
//  }

//  public static JTextComponent getTextComp( String text )
//  {
//    WebAdvancedEditorPane textPane = new WebAdvancedEditorPane("text/html", "");
//    textPane.setContentType("text/html"); // let the text pane know this is what you want
//    textPane.putClientProperty(WebAdvancedEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
//    textPane.setText(text); // showing off
//    textPane.setEditable(false); // as before
//    textPane.setBackground(null); // this is the same as a JLabel
//    textPane.setBorder(null);
//    textPane.enablePopupMenu(WebEditPopupMenu.COPY | WebEditPopupMenu.SELECTALL);
//    return textPane;
//  }

  public static JComponent getDummy( Color color )
  {
    JPanel panel = new JPanel();
    panel.setBackground(color);
    return panel;
  }

  public void startGrid( boolean switchXY )
  {
    if ( currentPanel != null )
      {
      currentPanel.startGrid(switchXY);
      return;
      }
    setLayout(new GridBagLayout());
    if ( switchXY )
      {
      constraints = new GridPanelXYSwitchedConstraints();
      }
    else
      {
      constraints = new GridPanelConstraints();
      }
    gridX = 0;
    gridY = 0;
  }

  public void startGrid()
  {
    if ( currentPanel != null )
      {
      currentPanel.startGrid();
      return;
      }
    setLayout(new GridBagLayout());
    constraints = new GridPanelConstraints();
    gridX = 0;
    gridY = 0;
  }

  public void setInsets( int top, int left, int bottom, int right )
  {

    if ( currentPanel != null )
      {
      currentPanel.setInsets(top, left, bottom, right);
      return;
      }
    insets = new Insets(top, left, bottom, right);
  }

  public void setInsets( int sides )
  {
    setInsets(sides, sides, sides, sides);
  }

  public void startTabbedPaneAsBorderLayout()
  {
    tabbedPane = new JTabbedPane();
    setLayout(new BorderLayout());
    add(tabbedPane, BorderLayout.CENTER);
  }

  public JTabbedPane makeTabbedPane()
  {
    tabbedPane = new JTabbedPane();
    return tabbedPane;
  }

  public void stopTabbedPane( boolean withFillers )
  {
    if ( withFillers && currentPanel != null )
      {
      currentPanel.addFillerRow();
      currentPanel.addFillerCol();
      }
    currentPanel = null;
  }

  public void stopTabbedPane()
  {
    stopTabbedPane(false);
  }

  public void addTab( String title )
  {
    if ( tabbedPane == null )
      {
      throw new IllegalStateException("There is not tabbed pane ready: you forgot something like startTabbedPaneAsBorderLayout() ||  demo.addRow(demo.makeTabbedPane()); ");
      }
    currentPanel = new GridPanel();
    tabbedPane.addTab(title, currentPanel);
  }

  public void addTab( String title, GridPanel panel )
  {
    if ( tabbedPane == null )
      {
      throw new IllegalStateException("There is not tabbed pane ready: you forgot something like startTabbedPaneAsBorderLayout() ||  demo.addRow(demo.makeTabbedPane()); ");
      }
    currentPanel = panel;
    tabbedPane.addTab(title, currentPanel);
  }

  public void addTabAndStartGrid( String title )
  {
    addTab(title);
    startGrid();
  }

  public void startSubPanel( Color color )
  {
    currentPanel = new GridPanel();
    currentPanel.setBackground(color);
  }

  public void startScrollPane()
  {
    JScrollPane scroll = new JScrollPane(currentPanel);
    this.add(scroll);
  }

  public void startSubPanel()
  {
    if ( currentPanel != null )
      {
      currentPanel.startSubPanel();
      return;
      }
    currentPanel = new GridPanel();
  }

  public void startSubPanelAndGrid()
  {
    if ( currentPanel != null )
      {
      currentPanel.startSubPanelAndGrid();
      return;
      }
    currentPanel = new GridPanel();
    currentPanel.startGrid();
  }

  public GridPanel stopSubPanel()
  {
    if ( currentPanel.currentPanel != null )
      {
      return currentPanel.stopSubPanel();
      }
    GridPanel finished = currentPanel;
    currentPanel = null;
    return finished;
  }

  public void setWeightsX( double... weightsX )
  {
    if ( currentPanel != null )
      {
      currentPanel.setWeightsX(weightsX);
      return;
      }
    System.arraycopy(weightsX, 0, this.weightsX, 0, weightsX.length);
  }

  public void setWeightY( double weightY )
  {
    if ( currentPanel != null )
      {
      currentPanel.setWeightY(weightY);
      return;
      }
    this.weightY = weightY;
  }

  public void setAnchors( int... anchors )
  {
    if ( currentPanel != null )
      {
      currentPanel.setAnchors(anchors);
      return;
      }
    System.arraycopy(anchors, 0, this.anchors, 0, anchors.length);
  }

  public void setFills( int... fills )
  {
    if ( currentPanel != null )
      {
      currentPanel.setFills(fills);
      return;
      }
    System.arraycopy(fills, 0, this.fills, 0, fills.length);
  }

  public int getFill( int index )
  {
    if ( currentPanel != null )
      {
      //noinspection TailRecursion
      return currentPanel.getFill(index);
      }
    if ( index < fills.length )
      return fills[index];
    else
      return GridBagConstraints.BOTH;
  }

  public int getAnchor( int index )
  {
    if ( currentPanel != null )
      {
      //noinspection TailRecursion
      return currentPanel.getAnchor(index);
      }
    if ( index < anchors.length )
      return anchors[index];
    else
      return GridBagConstraints.WEST;
  }

  public double getWeightX( int index )
  {
    if ( currentPanel != null )
      {
      //noinspection TailRecursion
      return currentPanel.getWeightX(index);
      }
    if ( index < weightsX.length )
      return weightsX[index];
    else
      return 0.0;
  }

  @SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
  private void checkConstraints()
  {
    if ( currentPanel != null )
      {
      currentPanel.checkConstraints();
      return;
      }
    if ( constraints == null )
      throw new IllegalStateException("You need to make sure the constraints are properly initialised, usually done by calling the startGRid method");
  }

  public void addRow( JComponent comp0, JComponent comp1, int spanX )
  {
    if ( currentPanel != null )
      {
      currentPanel.addRow(comp0, comp1, spanX);
      return;
      }
    checkConstraints();
    gridX = 0;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(0, gridY, 1, 1, weightsX[gridX], weightY, GridBagConstraints.BOTH, anchors[gridX], insets);
    add(comp0, constraints);
    gridX++;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(1, gridY, spanX, 1, weightsX[gridX], weightY, GridBagConstraints.BOTH, anchors[gridX], insets);
    add(comp1, constraints);
    gridX += spanX;
    gridY++;
  }

  public void addRow( String text0, String text1, String text2 )
  {
    addRow(getLabel(text0), getLabel(text1), getLabel(text2));
  }

  public void addRow( JComponent comp0, JComponent comp1, JComponent comp2 )
  {
    if ( currentPanel != null )
      {
      currentPanel.addRow(comp0, comp1, comp2);
      return;
      }
    checkConstraints();
    gridX = 0;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(0, gridY, 1, 1, weightsX[gridX], weightY, GridBagConstraints.BOTH, anchors[gridX], insets);
    add(comp0, constraints);
    gridX++;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(1, gridY, 1, 1, weightsX[gridX], weightY, GridBagConstraints.BOTH, anchors[gridX], insets);
    add(comp1, constraints);
    gridX++;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(2, gridY, 1, 1, weightsX[gridX], weightY, GridBagConstraints.BOTH, anchors[gridX], insets);
    add(comp2, constraints);
    gridX++;
    gridY++;
  }

  public void addRow( JComponent comp0, JComponent comp1, JComponent comp2, JComponent comp3, JComponent comp4 )
  {
    addRow(comp0, 1, comp1, comp2, comp3, comp4);
  }

  public void addRow( JComponent comp0, int spanX, JComponent comp1, JComponent comp2, JComponent comp3, JComponent comp4 )
  {
    if ( currentPanel != null )
      {
      currentPanel.addRow(comp0, spanX, comp1, comp2, comp3, comp4);
      return;
      }
    checkConstraints();
    gridX = 0;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, spanX, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    add(comp0, constraints);
    gridX += spanX;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, 1, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    add(comp1, constraints);
    gridX++;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, 1, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    add(comp2, constraints);
    gridX++;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, 1, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    add(comp3, constraints);
    gridX++;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, 1, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    add(comp4, constraints);
    gridX++;
    gridY++;
  }

  public void addRow( JComponent comp0, JComponent comp1, JComponent comp2, JComponent comp3 )
  {
    addRow(comp0, 1, comp1, comp2, comp3);
  }

  public void addRow( JComponent comp0, int spanX, JComponent comp1, JComponent comp2, JComponent comp3 )
  {
    if ( currentPanel != null )
      {
      currentPanel.addRow(comp0, spanX, comp1, comp2, comp3);
      return;
      }
    checkConstraints();
    gridX = 0;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, spanX, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    add(comp0, constraints);
    gridX += spanX;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, 1, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    add(comp1, constraints);
    gridX++;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, 1, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    add(comp2, constraints);
    gridX++;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, 1, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    add(comp3, constraints);
    gridX++;
    gridY++;
  }

  public void addRow( JComponent left, JComponent right )
  {
    if ( currentPanel != null )
      {
      currentPanel.addRow(left, right);
      return;
      }
    checkConstraints();
    gridX = 0;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, 1, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    gridX++;
    add(left, constraints);
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, 1, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    add(right, constraints);
    gridX++;
    gridY++;
  }

  public void addRow( JComponent comp )
  {
    if ( currentPanel != null )
      {
      currentPanel.addRow(comp);
      return;
      }
    checkConstraints();
    gridX = 0;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, 1, 1, weightsX[gridX], weightY, fills[gridX], anchors[gridX], insets);
    gridX++;
    add(comp, constraints);
    gridY++;
  }

  public void addComp( JComponent comp, int gX, int gY, int anchor )
  {
    this.addComp(comp, gX, gY, 1, 1, anchor);
  }

  public void addComp( JComponent comp, int gX, int gY, int spanX, int spanY, int anchor )
  {
    this.addComp(comp, gX, gY, spanX, spanY, anchor, getFill(gridX));
  }

  public void addComp( JComponent comp, int gX, int gY, int spanX, int spanY, int anchor, int fill )
  {
    if ( currentPanel != null )
      {
      currentPanel.addComp(comp, gX, gY, spanX, spanY, anchor, fill);
      return;
      }
    checkConstraints();
    this.gridX = gX;
    this.gridY = gY;
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, gridY, spanX, spanY, getWeightX(gridX), weightY, fill, anchor, insets);
    add(comp, constraints);
    this.gridX = gridX+spanX;
    this.gridY = gridY+spanY;
    if ( this.gridX > maxGridX )
      maxGridX = this.gridX;
    if ( this.gridY > maxGridY )
      maxGridY = this.gridY;
  }

  public void addRow( JComponent left, int span )
  {
    if ( currentPanel != null )
      {
      currentPanel.addRow(left, span);
      return;
      }
    checkConstraints();
    constraints.setGxGyGwGhWxWyFillAnchorInsets(0, gridY, span, 1, weightsX[0], weightY, GridBagConstraints.BOTH, anchors[gridX], insets);
    gridX = span+1;
    add(left, constraints);
    gridY++;
  }

  public JComponent addSeperatorRow( Color color )
  {
    if ( currentPanel != null )
      {
      //noinspection TailRecursion
      return currentPanel.addSeperatorRow(color);
      }
    checkConstraints();
    constraints.setGxGyGwGhWxWyFillAnchorInsets(0, gridY, gridX, 1, 0.0, weightY, GridBagConstraints.BOTH, anchors[gridX], insets);
    JComponent comp = getDummy();
    comp.setBackground(color);
    add(comp, constraints);
    gridY++;
    return comp;
  }

  public JComponent addSeperatorRow()
  {
    if ( currentPanel != null )
      {
      //noinspection TailRecursion
      return currentPanel.addSeperatorRow();
      }
    return addSeperatorRow(SECONDARY_4);
  }

  public void addSeperatorCol( Color color )
  {
    if ( currentPanel != null )
      {
      currentPanel.addSeperatorCol(color);
      return;
      }
    checkConstraints();
    constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, 0, 1, gridY, 0.0, 0.0, GridBagConstraints.BOTH, GridBagConstraints.CENTER, insets);
    JComponent comp = getDummy();
    comp.setBackground(color);
    add(comp, constraints);
    gridX++;
  }

  public void addSeperatorCol()
  {
    if ( currentPanel != null )
      {
      currentPanel.addSeperatorCol();
      return;
      }
    addSeperatorCol(SECONDARY_4);
  }

  public void addFillerRow()
  {
    addFillerRow(null);
  }

  public void addFillerRow( Color color )
  {
    if ( color != null )
      addFillerComp(getDummy(color));
    else
      addFillerComp(getDummy());
  }

  public void addFillerComp( JComponent comp )
  {
    if ( currentPanel != null )
      {
      currentPanel.addFillerRow();
      return;
      }
    checkConstraints();
    if ( maxGridY == -1 )
      {
      constraints.setGxGyGwGhWxWyFillAnchorInsets(0, gridY, gridX, 1, 0.0, 1.0, GridBagConstraints.BOTH, getAnchor(gridX), insets);
      }
    else
      {
      constraints.setGxGyGwGhWxWyFillAnchorInsets(0, maxGridY, gridX, 1, 0.0, 1.0, GridBagConstraints.BOTH, getAnchor(gridX), insets);
      }
    add(comp, constraints);

    gridY++;
  }

  public void addFillerCol()
  {
    if ( currentPanel != null )
      {
      currentPanel.addFillerCol();
      return;
      }
    checkConstraints();
    if ( maxGridY == -1 )
      {
      constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, 0, 1, gridY, 1.0, 0.0, GridBagConstraints.BOTH, getAnchor(gridX), insets);
      }
    else
      {
      constraints.setGxGyGwGhWxWyFillAnchorInsets(gridX, 0, 1, maxGridY+1, 1.0, 0.0, GridBagConstraints.BOTH, getAnchor(gridX), insets);
      }
//    JComponent dummy = getDummy();
//    dummy.setBackground(Color.green);
//    add(dummy, constraints);
    add(getDummy(), constraints);
    gridX++;
  }

  public void addScrollPane( JComponent component )
  {
    if ( currentPanel != null )
      {
      currentPanel.addScrollPane(component);
      return;
      }
    checkConstraints();
    if ( maxGridY == -1 )
      {
      constraints.setGxGyGwGhWxWyFillAnchorInsets(0, gridY, gridX, 1, 0.0, 1.0, GridBagConstraints.BOTH, getAnchor(gridX), insets);
      }
    else
      {
      constraints.setGxGyGwGhWxWyFillAnchorInsets(0, maxGridY, gridX, 1, 0.0, 1.0, GridBagConstraints.BOTH, getAnchor(gridX), insets);
      }
    add(new JScrollPane(component), constraints);
    gridY++;
  }

  public static String i18n( String key, Object... messageParameters )
  {
    return I18NUtil.getString(key, messageParameters);
  }

  protected JTextField getTextField( String value )
  {
    return new JTextField(value);
  }

  protected JTextField getTextField( int columns )
  {
    return new JTextField(columns);
  }

  protected JTextField getTextField( String initialValue, int columns )
  {
//    JTextField field = new WebTextField(columns);
//    field.setText(initialValue);
//    return
    return new JTextField(initialValue, columns);
  }

  public static JTextArea getTextArea( String text, int rows, int columns )
  {
    return new JTextArea(text, rows, columns);
  }


  @SuppressWarnings("MagicNumber")
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

    JFrame frame = new JFrame();
    Container cp = frame.getContentPane();
    cp.add(demo2());
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setLocation(500, 500);
    frame.setVisible(true);


    JFrame frame2 = new JFrame();
    Container cp2 = frame2.getContentPane();
    cp2.add(demo1());
    frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame2.setSize(500, 500);
    frame2.setLocation(0, 500);
    frame2.setVisible(true);
  }

  private static GridPanel demo1()
  {
    GridPanel demo = new GridPanel();
    demo.startGrid();
    demo.addRow(getLabel("header component"));
    demo.setWeightsX(1.0);
    demo.setWeightY(1.0);
    demo.setFills(GridBagConstraints.BOTH);
    demo.addRow(demo.makeTabbedPane());
    demo.setWeightY(0.0);
    demo.addTab("tab1");
    demo.startGrid();
    demo.addRow(getLabel("row1_1"));
    demo.addRow(getLabel("row1_2"));
    demo.addFillerRow();
    demo.addTab("tab2");
    demo.startGrid();
    demo.addRow(getLabel("row2_1"));
    demo.addRow(getLabel("row2_2"));
    demo.addFillerRow();
    demo.stopTabbedPane();
    demo.addRow(getLabel("footer component"));
    return demo;
  }

  private static GridPanel demo2()
  {
    GridPanel demo = new GridPanel();
    demo.startGrid();
    demo.addRow(getLabel("header component"), getLabel("header 2"));
    demo.startSubPanel(Color.cyan);
    demo.startGrid(true);
    demo.addRow(getLabel("sub1"));
    demo.addRow(getLabel("sub2"));
    demo.addRow(getLabel("sub3"));
    demo.addFillerRow();
    demo.addRow(demo.stopSubPanel(), 2);
    demo.addFillerRow();
    return demo;
  }

}