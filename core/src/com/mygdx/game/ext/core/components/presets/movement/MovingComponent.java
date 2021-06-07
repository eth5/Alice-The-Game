package com.mygdx.game.ext.core.components.presets.movement;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;
import com.mygdx.game.ext.core.system.presets.PhysicsSystem;

public class MovingComponent extends Component
{
 private float speed = 0;
 private float acc = 0;

 private boolean isMoving = false;

 private float accSing;

 public float getSpeed()
 {
  speed = 0;
  if (isMoving) acc = moveTo;
  moveTo = 0;
  accSing = Math.signum(acc);

  if (!isMoving)
  {
   if (acc!=0)
   {
    acc += accSing*PhysicsSystem.FRICTION;
    if (Math.signum(acc) != accSing) acc=0;
   }
  }

  speed += acc;

  isMoving = false;
  return speed;
 }

 public float moveTo;

 public void move(float vel)
 {
  moveTo+=vel;
  isMoving = true;
 }

 protected static final ComputeableHashMap<MovingComponent> childList = new ComputeableHashMap<>();
 private MovingComponent(Actor actor)
 {
  actor.addComponent(childList);
 }
 public static MovingComponent get(Actor actor) { return childList.compute(actor, ()->new MovingComponent(actor)); }
}
