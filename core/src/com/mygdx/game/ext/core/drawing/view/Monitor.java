package com.mygdx.game.ext.core.drawing.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;

import java.util.ArrayList;

/**
 * Manages different fields
 * */

public class Monitor
{
 public static Monitor instance;

 Array<CoordinateGrid> fields;

 public static final Logger log = new Logger("CORE", Logger.INFO);

 public int
   pixelWidth = Gdx.graphics.getWidth(),
   pixelHeight = Gdx.graphics.getHeight();

 public Monitor()
 {
  fields = new Array<>();

  batch = new SpriteBatch();
  liner = new ShapeRenderer();

  instance = this;

  Monitor.log.info("View system is active");
 }

 private final ShapeRenderer liner;
 private final SpriteBatch batch;

 public void update(int width, int height)
 {
  if (width==0 || height==0) {
   Monitor.log.error("Incorrect window size, pausing"); return;}

  this.pixelWidth = width;
  this.pixelHeight = height;

  for (CoordinateGrid field : fields) field.update();
 }

 /* Being called automatically, when field is created */
 public void addField(CoordinateGrid field)
 {
  fields.add(field);
 }

 public void setField(CoordinateGrid field)
 {
  batch.setProjectionMatrix(field.camera.combined);
  liner.setProjectionMatrix(field.camera.combined);
 }

 public SpriteBatch getBatch() { return batch; }
 public ShapeRenderer getLiner() { return liner; }
}
