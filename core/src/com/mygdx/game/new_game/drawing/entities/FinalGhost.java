package com.mygdx.game.new_game.drawing.entities;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Action;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.events.UseSword;
import com.mygdx.game.new_game.events.cutscenes.MnemoEnding;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;
import com.mygdx.game.new_game.systems.AliceBehaviourSystem;

public class FinalGhost extends Actor
{

 private boolean first = true;

 private Action.Arg1<Actor> touch = actor->{
  if (actor.getClass().getSimpleName().equals("SwordBox") && UseSword.isPlaying && first)
  {
   first = false;
   MnemoEnding.ghost = this;
   FirstAliceLevel.mnemoEnding.play();

  }

 };
 public FinalGhost(float x, float y)
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

  drawingComponent.spriteColor.set(.78f, .78f,0,1);

  CollisionComponent cc = CollisionComponent.get(this);
  cc.collisionType = CollisionType.BODY;
  cc.touch = touch;

  // cc.box.setPosition(x,y);
  // cc.box.setSize(physicsComponent.size.x, physicsComponent.size.y);

  Systems.physicsSystem.addActor(this);
  Systems.collisionSystem.addActor(this);

 }
}
