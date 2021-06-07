package com.mygdx.game.ext.core.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.drawing.view.CoordinateGrid;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.system.System;

public abstract class Scene
{
 protected static final Array<Actor> actorsForDelete = new Array<>(20);
 protected final String name;
 protected final Monitor monitor;
 protected SpriteBatch batch;
 protected final ShapeRenderer liner;

 public CoordinateGrid field;
 protected float width, height;

 public Scene(String name, CoordinateGrid field, float width, float height)
 {
  actorsForDelete.clear();
  this.name = name; this.field = field;
  this.monitor = field.monitor; this.camera = field.camera;
  this.batch = field.batch; this.liner = field.liner;
  this.width = width; this.height = height;

  Monitor.log.info("Scene \""+name+"\" was created");

  field.camera.update();
  field.liner.setProjectionMatrix(field.camera.combined);
 }

 public void iterDraw(float extrapolation)
 {
  camera.update();
  monitor.setField(field);

  callRenderSystems();
  // draw(extrapolation);
  if (actorsForDelete.size > 0) deleteActors();
 }
 // protected void draw(float extrapolation) {}
 void act() {}
 // void handleInput() {}

 public void iterPhys() { callPhysSystems(); act();
 }

 // public void iterInput() { callInputSystems(); handleInput(); }
 //private int actStartIndex=0, inputStartIndex=0, graphicsEndIndex=0, actEndIndex=0;

 private Array<System> getArrayForSystemType(final System.Type systemType)
 {
  switch (systemType)
  {
   case RENDER_SYSTEM: return renderSystems;
   case PHYSICS_SYSTEM: return physicSystems;
  }
  throw new IllegalArgumentException("такого быть не должно! " + systemType);
 }

 protected void callRenderSystems() { for (int i = 0; i < renderSystems.size; i++) renderSystems.get(i).handle(); }
 protected void callPhysSystems() { for (int i = 0; i < physicSystems.size; i++) physicSystems.get(i).handle(); }


 private final Array<System> renderSystems = new Array<>();
 private final Array<System> physicSystems = new Array<>();
 public void addSystem(final System.Type systemType, System... systems)
 {
  Array<System> systemsArray = getArrayForSystemType(systemType);
  systemsArray.addAll(systems);
 }
 public void addSystem(System... systems)
 {
  for (System system : systems) getArrayForSystemType(system.getType()).add(system);
 }


 public void remSystem(System... systems) { for (System system : systems) remSystem(system); }
 private void remSystem(System system) { getArrayForSystemType(system.getType()).removeValue(system, true); }
 protected Array<System> getSystems()
 {
  Array<System> systems = new Array<>(physicSystems.size + renderSystems.size);
  systems.addAll(physicSystems);
  systems.addAll(renderSystems);
  return systems;
 }

 protected OrthographicCamera camera;

 protected void drawGrid()
 {
  camera.update();

  liner.setProjectionMatrix(camera.combined);
  batch.setProjectionMatrix(camera.combined);
  liner.begin(ShapeRenderer.ShapeType.Line);
  liner.setColor(Color.GRAY);
  for (int i = 0; i < width; i++) liner.line(i, 0, i, height);
  for (int i = 0; i < height; i++) liner.line(0, i, width, i);

  liner.end();
 }

 protected Group actors = new Group();

 //private static Array<Actor> tmpArray = new Array<>(true,8);//not thread safe!
 protected Scene addActor(Actor... actors) { this.actors.addAll(Array.with(actors)); return this; }
 protected Actor[] remActor(Actor...actors) {this.actors.removeAll(Array.with(actors),true); return actors;}

 public void addActorForDelete(Actor actor)
 {
  actor.deleted = true;
  actorsForDelete.add(actor);
 }
 protected void deleteActors()
 {
  for (int i = 0; i < renderSystems.size; i++) renderSystems.get(i).remActor(actorsForDelete);
  for (int i = 0; i < physicSystems.size; i++) physicSystems.get(i).remActor(actorsForDelete);
  for (int i = 0; i < actorsForDelete.size; i++) actorsForDelete.get(i).deleteComponents();
  actorsForDelete.clear();
 }
}
