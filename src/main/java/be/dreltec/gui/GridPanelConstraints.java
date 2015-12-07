package be.dreltec.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.gui.GridPanelConstraints">Peter Geuens</a>
 */
public class GridPanelConstraints extends GridBagConstraints
{
  public static final Insets ZERO_ZERO_INSETS = new Insets(0, 0, 0, 0);
  public static final Insets ZERO_TWO_INSETS = new Insets(0, 2, 0, 2);

  public static final int DEFAULT_GRID_WIDTH = 1;
  public static final int DEFAULT_GRID_HEIGTH = 1;
  public static final double DEFAULT_WEIGHT_X = 1.0;
  public static final double DEFAULT_WEIGHT_Y = 1.0;
  public static final int DEFAULT_FILL = GridBagConstraints.BOTH;
  public static final int DEFAULT_ANCHOR = GridBagConstraints.CENTER;
  public static final int DEFAULT_SPACING_X = 4;
  public static final int DEFAULT_SPACING_Y = 4;
  public static final Insets DEFAULT_INSETS = ZERO_TWO_INSETS;

  public GridPanelConstraints()
  {
    this.gridwidth = DEFAULT_GRID_WIDTH;
    this.gridheight = DEFAULT_GRID_HEIGTH;
    this.weightx = DEFAULT_WEIGHT_X;
    this.weighty = DEFAULT_WEIGHT_Y;
    this.fill = DEFAULT_FILL;
    this.anchor = DEFAULT_ANCHOR;
    this.ipadx = DEFAULT_SPACING_X;
    this.ipady = DEFAULT_SPACING_Y;
    this.insets = DEFAULT_INSETS;
  }

  /**
   * Sets the grid x en y value for the constraints and all the other values to there default values.<p/>
   *
   * @param gridx the grid x value
   * @param gridy the grid y value
   */
  public void setGxGy( int gridx, int gridy )
  {
    this.gridx = gridx;
    this.gridy = gridy;

    this.gridwidth = DEFAULT_GRID_WIDTH;
    this.gridheight = DEFAULT_GRID_HEIGTH;
    this.weightx = DEFAULT_WEIGHT_X;
    this.weighty = DEFAULT_WEIGHT_Y;
    this.fill = DEFAULT_FILL;
    this.anchor = DEFAULT_ANCHOR;
    this.ipadx = DEFAULT_SPACING_X;
    this.ipady = DEFAULT_SPACING_Y;
    this.insets = DEFAULT_INSETS;
  }

  public void setGxGyGwGh( int gridx, int gridy,
                           int gridwidth, int gridheight )
  {
    this.gridx = gridx;
    this.gridy = gridy;
    this.gridwidth = gridwidth;
    this.gridheight = gridheight;

    this.weightx = DEFAULT_WEIGHT_X;
    this.weighty = DEFAULT_WEIGHT_Y;
    this.fill = DEFAULT_FILL;
    this.anchor = DEFAULT_ANCHOR;
    this.ipadx = DEFAULT_SPACING_X;
    this.ipady = DEFAULT_SPACING_Y;
    this.insets = DEFAULT_INSETS;
  }

  public void setGxGyWxWy( int gridx, int gridy,
                           double weightx, double weighty )
  {
    this.gridx = gridx;
    this.gridy = gridy;
    this.weightx = weightx;
    this.weighty = weighty;

    this.gridwidth = DEFAULT_GRID_WIDTH;
    this.gridheight = DEFAULT_GRID_HEIGTH;
    this.fill = DEFAULT_FILL;
    this.anchor = DEFAULT_ANCHOR;
    this.ipadx = DEFAULT_SPACING_X;
    this.ipady = DEFAULT_SPACING_Y;
    this.insets = DEFAULT_INSETS;
  }

  public void setGxGyGwGhWxWy( int gridx, int gridy,
                               int gridwidth, int gridheight,
                               double weightx, double weighty )
  {
    this.gridx = gridx;
    this.gridy = gridy;
    this.gridwidth = gridwidth;
    this.gridheight = gridheight;
    this.weightx = weightx;
    this.weighty = weighty;

    this.fill = DEFAULT_FILL;
    this.anchor = DEFAULT_ANCHOR;
    this.ipadx = DEFAULT_SPACING_X;
    this.ipady = DEFAULT_SPACING_Y;
    this.insets = DEFAULT_INSETS;
  }

  public void setGxGyGwGhWxWyFillAnchor( int gridx, int gridy,
                                         int gridwidth, int gridheight,
                                         double weightx, double weighty,
                                         int fill, int anchor )
  {
    this.gridx = gridx;
    this.gridy = gridy;
    this.gridwidth = gridwidth;
    this.gridheight = gridheight;
    this.weightx = weightx;
    this.weighty = weighty;
    this.fill = fill;
    this.anchor = anchor;

    this.ipadx = DEFAULT_SPACING_X;
    this.ipady = DEFAULT_SPACING_Y;
    this.insets = DEFAULT_INSETS;
  }

  public void setGxGyGwGhWxWyFillAnchorPadding( int gridx, int gridy,
                                                int gridwidth, int gridheight,
                                                double weightx, double weighty,
                                                int fill, int anchor, int padding )
  {
    this.gridx = gridx;
    this.gridy = gridy;
    this.gridwidth = gridwidth;
    this.gridheight = gridheight;
    this.weightx = weightx;
    this.weighty = weighty;
    this.fill = fill;
    this.anchor = anchor;

    this.ipadx = padding;
    this.ipady = padding;
    this.insets = DEFAULT_INSETS;
  }

  public void setGxGyGwGhWxWyFillAnchorInsets( int gridx, int gridy,
                                               int gridwidth, int gridheight,
                                               double weightx, double weighty,
                                               int fill, int anchor, Insets insets )
  {
    this.gridx = gridx;
    this.gridy = gridy;
    this.gridwidth = gridwidth;
    this.gridheight = gridheight;
    this.weightx = weightx;
    this.weighty = weighty;
    this.fill = fill;
    this.anchor = anchor;

    this.ipadx = DEFAULT_SPACING_X;
    this.ipady = DEFAULT_SPACING_Y;
    this.insets = insets;
  }

  /**
   * Sets the grid x en y value for the constraints and does not change the other values.<p/>
   *
   * @param gridx the grid x value
   * @param gridy the grid y value
   */
  public void setOnlyGxGy( int gridx, int gridy )
  {
    this.gridx = gridx;
    this.gridy = gridy;
  }

  public void setOnlyGxGyAnchor( int gridx, int gridy, int anchor )
  {
    this.gridx = gridx;
    this.gridy = gridy;
    this.anchor = anchor;
  }

  public void setOnlyGxGyGwGh( int gridx, int gridy,
                               int gridwidth, int gridheight )
  {
    this.gridx = gridx;
    this.gridy = gridy;
    this.gridwidth = gridwidth;
    this.gridheight = gridheight;

  }

  public void setOnlyGxGyWxWy( int gridx, int gridy, double weightx, double weighty )
  {
    this.gridx = gridx;
    this.gridy = gridy;
    this.weightx = weightx;
    this.weighty = weighty;
  }

  public void setOnlyFill( int fill )
  {
    this.fill = fill;
  }

  public void setOnlyAnchor( int anchor )
  {
    this.anchor = anchor;
  }

  public void setOnlyInsets( Insets insets )
  {
    this.insets = insets;
  }

  public void setOnlyPadding( int paddingX, int paddingY )
  {
    this.ipadx = paddingX;
    this.ipady = paddingY;
  }

}
