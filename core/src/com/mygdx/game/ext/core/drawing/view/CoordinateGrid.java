package com.mygdx.game.ext.core.drawing.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.ext.additional.CameraController;


public abstract class CoordinateGrid
{
 public CameraController cameraController;

 public Monitor monitor;
 public CoordinateGrid field;

 public float unitWidth, unitHeight;
 public float notIntegerUnitWidth; // TODO: Find better name {unitWidth = Math.ceil(notIntegerUnitWidth)}


 public ExtendViewport viewport;
 public OrthographicCamera camera;

 public SpriteBatch batch;
 public ShapeRenderer liner;

 public CoordinateGrid(String name)
 {
  Monitor.log.info("Coordinate grid \""+name+"\" was created");

  this.monitor = Monitor.instance;


  batch = monitor.getBatch();
  liner = monitor.getLiner();

  monitor.addField(this);
 }

 abstract void update();
}
