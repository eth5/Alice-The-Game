package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Rect;
import com.dongbat.jbump.World;
import com.mygdx.game.ext.additional.CameraController;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.event.Event;
import com.mygdx.game.ext.core.group.presets.Layer;
import com.mygdx.game.ext.core.system.System;

import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionSystem;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.TreeMap;

public class DrawingSystem extends System
{
 public static boolean EXTRAPOLATE = true;
 public CameraController cameraController;
 public static boolean DEBUG = false;
 private final ShapeDrawer shapeDrawer;
 public static PhysicsComponent master;
 public static final Array<Integer> masterIds = new Array<>();
 public void setMaster(Actor actor)
 {
  master = PhysicsComponent.get(actor);
 }
 public void setMasterIds(Integer... ids)
 {
  DrawingSystem.masterIds.clear();
  DrawingSystem.masterIds.addAll(ids);
 }
 public DrawingSystem()
 {
  batch = Monitor.instance.getBatch();
  type = Type.RENDER_SYSTEM;
  priority = 100;
  shapeDrawer = new ShapeDrawer(Monitor.instance.getBatch(), new TextureRegion(new Texture(Gdx.files.internal("white.jpg"))));
 }

 protected SpriteBatch batch;
 protected TextureAtlas.AtlasRegion texture;
 protected Vector2 position, size, velocity;
 private DrawingComponent drawingComponent;
 public TreeMap<Integer, Layer> layers = new TreeMap<>();

 public void handle()
 {
  // logger.info("Drawing System");
  for (int i = 0; i < masterIds.size; i++) drawingChekOff(layers.get(masterIds.get(i)));
 // drawingChekOff(FirstAliceLevel.events);

  batch.begin();
  Color color = batch.getColor();
  layers.forEach((key, layer) -> iterateLayer(layer));
  //if (DEBUG) layers.forEach((key, layers) -> showWorldRects(layers, CollisionSystem.world));// debug
  //if (DEBUG) debug();
  batch.setColor(color);
  batch.end();
 }

 private static final int BLOCKS = 40;
 private void drawingChekOff(Array<Actor> actors)
 {
  for (int i = 0; i < actors.size; i++)
  {
   Actor actor = actors.get(i);
   float delta = master.position.x - BodyPropertiesComponent.get(actor).position.x;
   DrawingComponent.get(actor).draw = delta > -BLOCKS && delta < BLOCKS;
  }
 }

 private void iterateLayer(Layer layer)
 { ;
  batch.setProjectionMatrix(layer.getCoordinateGrid().camera.combined);
  layer.forEach(this::calc);
 }

 private void calc(Actor actor)
 {
  this.actor = actor;
  loadFields();
  if (!drawingComponent.draw) return;
  behave();
 }

 protected void loadFields()
 {
  drawingComponent = DrawingComponent.get(actor);
  texture = drawingComponent.texture;
  drawSize = drawingComponent.drawSize;
  drawPosition = drawingComponent.drawPosition;

  if (drawingComponent.useExtrapolation)
  {
   PhysicsComponent physicsComponent = PhysicsComponent.get(actor);
   position = physicsComponent.position;
   velocity = physicsComponent.velocity;
   size = physicsComponent.size;
   if (!EXTRAPOLATE) ApplicationLoop.instance.extrapolation = 0;
  } else
  {
   BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(actor);
   position = bodyPropertiesComponent.position;
   size = bodyPropertiesComponent.size;
  }

  calculateDrawPosition();
 }

 private Vector2 drawPosition;
 private Vector2 drawSize;

 protected void behave()
 {
  if (!(drawingComponent.spriteColor.a==1
    &&
    drawingComponent.spriteColor.r==1 &&
  drawingComponent.spriteColor.g==1 &&
  drawingComponent.spriteColor.b==1)) batch.setColor(drawingComponent.spriteColor);

  boolean flippedX = texture.isFlipX() != drawingComponent.flipX, flippedY = texture.isFlipY() != drawingComponent.flipY;
  if (flippedX || flippedY) texture.flip(flippedX, flippedY);

  batch.draw(texture, drawPosition.x, drawPosition.y, drawSize.x/2, drawSize.y/2, drawSize.x,drawSize.y,1,1, drawingComponent.rotate);

  batch.setColor(Color.WHITE);
//  debug();

 }

 private void calculateDrawPosition()
 {
  drawPosition.set(position);
  if (!drawingComponent.offset.isZero())drawPosition.add(drawingComponent.offset);

  if (drawingComponent.useExtrapolation) // TODO: Упростить
  {
   if (drawingComponent.extrapolationX) drawPosition.x += velocity.x * ApplicationLoop.instance.extrapolation;
   else if (drawingComponent.extrapolationOffNanoX < ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationX = true;

   if (drawingComponent.extrapolationY) drawPosition.y += velocity.y * ApplicationLoop.instance.extrapolation;
   else if (drawingComponent.extrapolationOffNanoY < ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationY = true;
  }
 }

 private void debug()
 {
  if (Event.eventSystemInstance!=null) drawEvents();
   drawCollisionBox(); // TODO: adds collision (FIX!)
 // drawTextureBox();
 }

 private void draw(Vector2 position, Vector2 size, Color color)
 {
  shapeDrawer.setColor(color);
  shapeDrawer.setDefaultLineWidth(0.05f);
  shapeDrawer.rectangle(position.x, position.y, size.x, size.y);
 }

 private void drawEvents()
 {
  batch.setProjectionMatrix(Event.eventSystemInstance.coordGrid);
  Event.eventSystemInstance.events.forEach((event) -> draw(event.position, event.size, Color.GOLD));
 }

 private void drawCollisionBox()
 {
  if (!PhysicsComponent.has(actor)) return; // TODO: has is expensive. Fix
  final PhysicsComponent physicsComponent = PhysicsComponent.get(actor);
  draw(physicsComponent.position, physicsComponent.size, Color.PINK);
 }

 private void drawTextureBox() { draw(drawPosition, drawSize, Color.LIME); }

 private void showWorldRects(Array<Actor> array, World<Actor> world)
 {
  for (Actor actor1 : array)
  {
   if (!CollisionComponent.has(actor1))continue; // TODO: Using Has is expensive. Will be fixed in task #40
   Item item = CollisionComponent.get(actor1).item;
   if (world.hasItem(item))
   {
    DrawingComponent drawingComponent = DrawingComponent.get(actor1);
    shapeDrawer.setColor(drawingComponent.debugCollisionColor);
    drawingComponent.debugCollisionColor = Color.LIME;
    shapeDrawer.setDefaultLineWidth(0.03f);
    Rect rect = world.getRect(item);
    shapeDrawer.rectangle(rect.x+cameraController.cameraX, rect.y+cameraController.cameraY, rect.w, rect.h);
   }
  }
 }
}

/* COMMENT SECTION */

//   float valueX,valueY;
//   if (drawingComponent.extrapolationX) valueX = velocity.x*extr;
//   else
//   {
//    color = Color.RED;
//    valueX = 0;
//    if (drawingComponent.extrapolationOffNanoX < ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationX = true;
//   }
//   if (drawingComponent.extrapolationY) valueY = velocity.y*extr;
//   else
//   {
//    color = Color.RED;
//    valueY = 0;
//    if (drawingComponent.extrapolationOffNanoY < ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationY = true;
//   }
