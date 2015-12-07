package be.dreltec.gui;

import java.awt.Insets;

/**
 * @author <a href="mailto:pgeuens@uzbrussel.be?Subject=be.dreltec.gui.GridPanelXYSwitchedConstraints">Peter Geuens</a>
 */
public class GridPanelXYSwitchedConstraints extends GridPanelConstraints
{

  public GridPanelXYSwitchedConstraints()
  {
    super();
  }

  private Insets switchInsets( Insets insets )
  {
    //int top, int left, int bottom, int right
    //noinspection SuspiciousNameCombination
    return new Insets(insets.left, insets.bottom, insets.right, insets.top);
  }

  @Override
  public void setGxGy( int gridx, int gridy )
  {
    super.setGxGy(gridy, gridx);
    insets = switchInsets(insets);
  }

  @Override
  public void setGxGyGwGh( int gridx, int gridy, int gridwidth, int gridheight )
  {
    super.setGxGyGwGh(gridy, gridx, gridheight, gridwidth);
    insets = switchInsets(insets);
  }

  @Override
  public void setGxGyGwGhWxWy( int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty )
  {
    super.setGxGyGwGhWxWy(gridy, gridx, gridheight, gridwidth, weighty, weightx);
    insets = switchInsets(insets);
  }

  @Override
  public void setGxGyGwGhWxWyFillAnchor( int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill, int anchor )
  {
    super.setGxGyGwGhWxWyFillAnchor(gridy, gridx, gridheight, gridwidth, weighty, weightx, fill, anchor);
    insets = switchInsets(insets);
  }

  @Override
  public void setGxGyGwGhWxWyFillAnchorInsets( int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill, int anchor, Insets insets )
  {
    super.setGxGyGwGhWxWyFillAnchorInsets(gridy, gridx, gridheight, gridwidth, weighty, weightx, fill, anchor, switchInsets(insets));
  }

  @Override
  public void setGxGyGwGhWxWyFillAnchorPadding( int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill, int anchor, int padding )
  {
    super.setGxGyGwGhWxWyFillAnchorPadding(gridy, gridx, gridheight, gridwidth, weighty, weightx, fill, anchor, padding);
    insets = switchInsets(insets);
  }

  @Override
  public void setGxGyWxWy( int gridx, int gridy, double weightx, double weighty )
  {
    super.setGxGyWxWy(gridy, gridx, weighty, weightx);
    insets = switchInsets(insets);
  }

  @Override
  public void setOnlyAnchor( int anchor )
  {
    super.setOnlyAnchor(anchor);
  }

  @Override
  public void setOnlyFill( int fill )
  {
    super.setOnlyFill(fill);
  }

  @Override
  public void setOnlyGxGy( int gridx, int gridy )
  {
    super.setOnlyGxGy(gridy, gridx);
  }

  @Override
  public void setOnlyGxGyAnchor( int gridx, int gridy, int anchor )
  {
    super.setOnlyGxGyAnchor(gridy, gridx, anchor);
  }

  @Override
  public void setOnlyGxGyGwGh( int gridx, int gridy, int gridwidth, int gridheight )
  {
    super.setOnlyGxGyGwGh(gridy, gridx, gridheight, gridwidth);
  }

  @Override
  public void setOnlyGxGyWxWy( int gridx, int gridy, double weightx, double weighty )
  {
    super.setOnlyGxGyWxWy(gridy, gridx, weighty, weightx);
  }

  @Override
  public void setOnlyInsets( Insets insets )
  {
    super.setOnlyInsets(switchInsets(insets));
  }

  @Override
  public void setOnlyPadding( int paddingX, int paddingY )
  {
    //noinspection SuspiciousNameCombination
    super.setOnlyPadding(paddingY, paddingX);
  }

}