package com.mygdx.game.new_game.drawing.stat;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_game.Main;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.drawing.entities.Alice;

public class Heart extends Envy
{
 public Heart(float x, float y)
 {
  super(x, y, 2f, 4f, SpriteManager.get("heart"));

  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.drawSize.set(1.8f,1.9f);
  drawingComponent.offset.set(0,2);

  CollisionComponent cc = CollisionComponent.get(this);
  cc.collisionType = CollisionType.BODY;
  cc.touch = this::touch;
  Systems.collisionSystem.addActor(this);
 }
 protected void touch(Actor actor)
 {
  if (deleted) return;
  if (actor instanceof Alice){
   ((Alice) actor).addHeart();
   delete();
  }
 }
}
