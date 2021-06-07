package com.mygdx.game.ext.additional;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class CameraController
{
 private ExtendViewport viewport;
 private OrthographicCamera camera;
 public float cameraX, cameraY;
 private final float originWidth, originHeight;
 private float curWidth, curHeight;

 public CameraController(ExtendViewport viewport)
 {
  setViewport(viewport);

  curHeight = originHeight = viewport.getMinWorldHeight();
  curWidth = originWidth = viewport.getMinWorldWidth();

  setStartPosition();
 }

 public void setPosition(float x, float y)
 {
  cameraX = x; cameraY = y;
  camera.position.x = curWidth/2-x;
  camera.position.y = curHeight/2-y;
  camera.update();
 }

 public void move(float x, float y)
 {
  cameraX += x; cameraY += y;
 }

 public void setStartPosition()
 {
  camera.position.x = curWidth/2;
  camera.position.y = curHeight/2;
 }

 public void update(float width, float height)
 {
  curWidth = width;
  curHeight = height;

  setPosition(cameraX, cameraY);
 }

 public void setViewport(ExtendViewport viewport)
 {
  this.viewport = viewport;
  this.camera = (OrthographicCamera) viewport.getCamera();
 }
}
