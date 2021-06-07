package com.mygdx.game.new_game.drawing;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_game.Systems;

public class InvisibleWall extends Actor
{
 public InvisibleWall(float x, float y, float width, float height)
 {
  BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(this);

  bodyPropertiesComponent.position.set(x,y);
  bodyPropertiesComponent.size.set(width, height);

  CollisionComponent collisionComponent = CollisionComponent.get(this);

  // collisionComponent.box.setPosition(x,y);
  // collisionComponent.box.setSize(width, height);

  collisionComponent.collisionType = CollisionType.SOLID;
  Systems.collisionSystem.addActor(this);
 }

}
