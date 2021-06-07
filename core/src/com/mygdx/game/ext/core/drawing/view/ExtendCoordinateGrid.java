package com.mygdx.game.ext.core.drawing.view;

/**
 * Standart implementation of coordinate grid in unit system.
 * There is a static amount of units on one side,
 * and other is changing its amount to keep first static.
 *
 * */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.ext.additional.CameraController;

public class ExtendCoordinateGrid extends CoordinateGrid
{
 public ExtendCoordinateGrid(String name, float unitHeight) // TODO: move main functionality to CoordinateGrid
 {
  super(name);
  this.unitHeight = unitHeight;
  this.monitor = Monitor.instance;
  
  calcFieldWidth();

  viewport = new ExtendViewport(notIntegerUnitWidth, unitHeight);
  camera = (OrthographicCamera) viewport.getCamera();

  cameraController = new CameraController(viewport);

  monitor.addField(this);

  //Monitor.log.info("Coordinate grid \""+name+"\" was created ["+unitWidth+":"+unitHeight+"]");
 }

 private void calcFieldWidth()
 {
  notIntegerUnitWidth = (monitor.pixelWidth * unitHeight) / monitor.pixelHeight;
  unitWidth = (int) Math.ceil(notIntegerUnitWidth);
 }

 public void debug(Color lines, Color center, Color lim)
 {
  camera.update();

  liner.setProjectionMatrix(camera.combined);
  batch.setProjectionMatrix(camera.combined);

  liner.begin(ShapeRenderer.ShapeType.Line);

  liner.setColor(lines);
  for (int i = 0; i < unitWidth; i++) liner.line(i, 0, i, unitHeight);
  for (int i = 0; i < unitHeight; i++) liner.line(0, i, unitWidth, i);

  liner.setColor(center);
  liner.line(0,unitHeight/2, notIntegerUnitWidth,unitHeight/2);
  liner.line(notIntegerUnitWidth /2,0, notIntegerUnitWidth /2,unitHeight);

  liner.setColor(lim);
  liner.line(notIntegerUnitWidth,0, notIntegerUnitWidth,unitHeight);

  liner.end();
 }

 public void update()
 {
  calcFieldWidth();

  viewport.setMinWorldWidth(notIntegerUnitWidth);
  viewport.update(monitor.pixelWidth, monitor.pixelHeight, false);

  cameraController.update(viewport.getMinWorldWidth(), viewport.getMinWorldHeight());
 }
}
