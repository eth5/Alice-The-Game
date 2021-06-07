package com.mygdx.game.ext.core.group.presets;

import com.mygdx.game.ext.core.drawing.view.CoordinateGrid;
import com.mygdx.game.ext.core.group.Group;

public class Layer extends Group
{
 private CoordinateGrid coordinateGrid;

 public Layer(CoordinateGrid coordinateGrid)
 {
  setCoordinateGrid(coordinateGrid);
 }

 public void setCoordinateGrid(CoordinateGrid coordinateGrid)
 {
  this.coordinateGrid = coordinateGrid;
 }

 public CoordinateGrid getCoordinateGrid()
 {
  return coordinateGrid;
 }
}
