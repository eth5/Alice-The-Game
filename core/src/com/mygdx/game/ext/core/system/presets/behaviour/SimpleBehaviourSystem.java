package com.mygdx.game.ext.core.system.presets.behaviour;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.system.System;
import com.mygdx.game.ext.core.system.presets.collisionSystem.BoundingBox;

public class SimpleBehaviourSystem extends System
{
 private Vector2 position, velocity;
 private Vector2 originPosition = new Vector2(10,10);
 private int dir = -1;
 private BoundingBox box;

 {
  type = System.Type.PHYSICS_SYSTEM; // PHYSICS_COMPONENT;
  priority = 10;
 }

 @Override
 protected void loadFields()
 {
  PhysicsComponent physicsComponent = PhysicsComponent.get(actor);

  position = physicsComponent.position;
  velocity = physicsComponent.velocity;

 }

 @Override
 protected void behave()
 {
  // logger.info("Physic System");

  //if (position.y>0) velocity.y-=0.02f;
  //else { velocity.y = 0; position.y = 0; } // TODO: disable extrapolation in here

   if (position.x > originPosition.x + 5 | position.x < originPosition.x - 5) dir *= -1;
  velocity.x = dir*0.05f;
 }
}

