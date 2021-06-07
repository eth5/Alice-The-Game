package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.system.System;

public class ControlSystem extends System
{
 public ControlSystem()
 {
  type = Type.RENDER_SYSTEM;
 }

 protected Vector2 position, velocity, speed;

 @Override
 protected void loadFields()
 {
  PhysicsComponent physicsComponent = PhysicsComponent.get(actor);

  position = physicsComponent.position;
  velocity = physicsComponent.velocity;
  speed = physicsComponent.speed;
 }

 @Override
 protected void behave()
 {
  // logger.info("Control System");

  if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {velocity.y=0; speed.y = 0.7f;}
  if (Gdx.input.isKeyPressed(Input.Keys.A)) speed.x = -0.03f;
  if (Gdx.input.isKeyPressed(Input.Keys.D)) speed.x =  0.03f;

  if (!speed.isZero())
  {
   velocity.add(speed);
   speed.setZero();
  }

  if (Math.abs(velocity.x)>0.7f) velocity.x = (velocity.x/Math.abs(velocity.x))*0.7f;

 }
}
