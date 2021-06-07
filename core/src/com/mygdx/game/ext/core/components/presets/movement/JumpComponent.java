package com.mygdx.game.ext.core.components.presets.movement;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.ComputeableHashMap;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.system.presets.PhysicsSystem;

public class JumpComponent extends LF4DComponent // TODO: где-то в формуле допущена ошибка (v=v0+at или s=vot+(at*t)/2)
{
 private float estimatedTime;
 private float height;
 private float startSpeed;

 private boolean isActive;

 public void configure(float time, float height)
 {
  estimatedTime = time;
  this.height = height;

  startSpeed=(height-((PhysicsSystem.GRAVITY_FORCE_VALUE*estimatedTime*estimatedTime)/2))/estimatedTime;
  //System.out.println(startSpeed);
 }

 public float time=0;
 public float speed;

 public void jump()
 {
  if (!CollisionComponent.get(actor).isStanding) return;
  CollisionComponent.get(actor).isStanding = false;

  time = 0; speed = 0;
  isActive = true;
 }

 public boolean iter()
 {
  time++;
  speed = startSpeed+PhysicsSystem.GRAVITY_FORCE_VALUE*time;
//  System.out.println(speed);

  if (time==estimatedTime) {finish(); return false;}
  return true;
 }

 public void finish()
 {
  isActive = false;
 }

 public JumpComponent(Actor actor)
 {
  super(actor);
  configure(5,5);
 }

 public boolean isActive()
 {
  return isActive;
 }

 protected static final ComputeableHashMap<JumpComponent> childList = new ComputeableHashMap<>();

 public static JumpComponent get(Actor actor) { return childList.compute(actor, () -> new JumpComponent(actor)); }
}
