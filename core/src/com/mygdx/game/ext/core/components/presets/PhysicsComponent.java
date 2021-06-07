package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class PhysicsComponent extends Component
{
 private static final ComputeableHashMap<PhysicsComponent> childList = new ComputeableHashMap<>();

 public Vector2 position;
 public Vector2 size;
 public Vector2 personalGravity = new Vector2(0,0);
 public boolean useGravity = true;

 public Vector2 velocity = new Vector2(0,0);

 public Vector2 speed = new Vector2();
 public Vector2 acceleration = new Vector2();

 public Color color = Color.GREEN; // for dr/aw debug value

 private PhysicsComponent(Actor actor)
 {
  actor.addComponent(childList);
  BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(actor);

  position = bodyPropertiesComponent.position;
  size = bodyPropertiesComponent.size;
 }
 public static PhysicsComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new PhysicsComponent(actor));
 }
 public static boolean has(Actor actor) { return childList.containsKey(actor); }
}
