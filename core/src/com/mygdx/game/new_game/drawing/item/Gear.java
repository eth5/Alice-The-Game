package com.mygdx.game.new_game.drawing.item;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Action;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;

public class Gear extends Actor
{
 public boolean touchedAlice = false;

 private Action.Arg1<Actor> touch = actor->{
  if (actor.getClass().getSimpleName().equals("Alice"))
  {
   touchedAlice = true;
  }

 };
 public Gear(float x, float y)
 {
  PhysicsComponent physicsComponent = PhysicsComponent.get(this);
  physicsComponent.position.set(x,y);
  physicsComponent.size.set(3f,3f);
  physicsComponent.personalGravity.y = -0.9f;

  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = SpriteManager.get("gear");
  drawingComponent.offset.set(0,0);
  drawingComponent.drawSize.set(3f,3f);
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
