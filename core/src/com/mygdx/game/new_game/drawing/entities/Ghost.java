package com.mygdx.game.new_game.drawing.entities;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Action;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;

public class Ghost extends Actor
{
 public boolean touchedAlice = false;
 public boolean alive = true;
 public int sign;
 public long dieTime;

 private Action.Arg1<Actor> touch = actor->{
  if (actor.getClass().getSimpleName().equals("Alice"))
  {
   touchedAlice = true;
  }

 };
 public Ghost(float x, float y)
 {
  PhysicsComponent physicsComponent = PhysicsComponent.get(this);
  physicsComponent.position.set(x,y);
  physicsComponent.size.set(2.14f*1.1f,2.06f*1.1f+2.6f);
  physicsComponent.personalGravity.y = -0.9f;

  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = SpriteManager.get("ghost");
  drawingComponent.offset.set(-0.1f,2.6f);
  drawingComponent.drawSize.set(2.14f*1.3f,2.06f*1.3f);
  drawingComponent.useExtrapolation = true;


  CollisionComponent cc = CollisionComponent.get(this);
  cc.collisionType = CollisionType.BODY;
  cc.touch = touch;

  // cc.box.setPosition(x,y);
  // cc.box.setSize(physicsComponent.size.x, physicsComponent.size.y);

  Systems.physicsSystem.addActor(this);
  Systems.collisionSystem.addActor(this);

 }
}
