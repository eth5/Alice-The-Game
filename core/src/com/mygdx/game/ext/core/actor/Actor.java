package com.mygdx.game.ext.core.actor;

import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.new_game.Main;

import java.util.HashSet;

public abstract class Actor
{
 public boolean doDrawing = true, doActing = true, doInputHandling = true;  // Used by Scene to determine call Actor methods or not
 private final HashSet<ObjectMap<Actor,?>> components = new HashSet<>();

 public boolean deleted = false;
 public void pause()
 {
  doDrawing = false;
  doActing = false;
  doInputHandling = false;
 }

 public void resume()
 {
  doDrawing = true;
  doActing = true;
  doInputHandling = true;
 }
 public void deleteComponents()
 {
  components.forEach(component -> {component.remove(this);});
 }
 public void addComponent(ObjectMap<Actor,?> componentMap)
 {
  components.add(componentMap);
 }

 public void delete()
 {
  deleted = true;
  Main.currentScene.addActorForDelete(this);
 }
}
