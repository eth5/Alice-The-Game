package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.components.presets.movement.JumpComponent;
import com.mygdx.game.ext.core.components.presets.movement.MovingComponent;
import com.mygdx.game.ext.core.system.System;

public class PhysicsSystem extends System
{
 private Vector2 position, velocity, acceleration;


 public static final float GRAVITY_FORCE_VALUE = -0.3f;
 public static final float FRICTION = -0.3f;

 {
  type = Type.PHYSICS_SYSTEM; // PHYSICS_COMPONENT;
  priority = 3;
 }

 @Override
 protected void loadFields()
 {
  PhysicsComponent physicsComponent = PhysicsComponent.get(actor);

  position = physicsComponent.position;
  velocity = physicsComponent.velocity;
  acceleration = physicsComponent.acceleration;
 }

 @Override
 protected void behave()
 {
  position.add(velocity);
  velocity.setZero();

  count();

  velocity.add(acceleration);
 }

 private void count()
 {
  countGravity();

  velocity.x += MovingComponent.get(actor).getSpeed();
 }

 private void countGravity()
 {
  JumpComponent jumpComponent = JumpComponent.get(actor);
  PhysicsComponent physicsComponent = PhysicsComponent.get(actor);

  if (!jumpComponent.isActive()) {


   if (physicsComponent.useGravity)
    velocity.y+=GRAVITY_FORCE_VALUE+physicsComponent.personalGravity.y; else velocity.y = 0;
    return;
  }
  jumpComponent.iter();

  velocity.y += jumpComponent.speed;
 }
}
