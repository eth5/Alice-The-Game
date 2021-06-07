package com.mygdx.game.new_game.events.cutscenes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.system.presets.DrawingSystem;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.drawing.entities.FinalGhost;
import com.mygdx.game.new_game.drawing.entities.Ghost;
import com.mygdx.game.new_game.events.SpawnGhost;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;
import com.mygdx.game.new_game.systems.AliceBehaviourSystem;

public class MnemoEnding extends Cutscene
{
 public static FinalGhost ghost;

 public MnemoEnding(float x, float y)
 {
  super(x, y);
  size.y+=5;
 }

 BodyPropertiesComponent bp;
 PhysicsComponent pc;

 @Override
 public void play()
 {
  super.play();
  background.drawingComponent.draw = false;
  stage = 1;
  bp = BodyPropertiesComponent.get(ghost);
  pc = PhysicsComponent.get(ghost);
  image.drawingComponent.draw = false;
  text.drawingComponent.draw = false;
 }

 private long lt = 0;
 private long nt;

 @Override
 public void continuePlaying()
 {
  if (ApplicationLoop.instance.inGameTime<lt) return;

   lt=ApplicationLoop.instance.inGameTime+300_000_000;

  super.continuePlaying();
  switch (stage)
  {
   case 1:
    DrawingComponent.get(ghost).spriteColor.a = 0.9f;
    DrawingSystem.EXTRAPOLATE = false;
    bp.position.y -= 0.4f;
    pc.velocity.set(0,0);
    stage = 2;
    break;
   case 2:
    DrawingComponent.get(ghost).spriteColor.a = 0.8f;
    bp.position.y -= 0.4f;
    stage = 3;
    break;
   case 3:
    DrawingComponent.get(ghost).spriteColor.a = 0.6f;
    bp.position.y -= 0.4f;
    stage = 4;
    break;
   case 4:
    DrawingComponent.get(ghost).spriteColor.a = 0.5f;
    bp.position.y -= 0.4f;
    stage = 5;
    break;
   case 5:
    DrawingComponent.get(ghost).spriteColor.a = 0.3f;
    bp.position.y -= 0.4f;
    stage = 6;
    break;
   case 6:
    DrawingComponent.get(ghost).spriteColor.a = 0.2f;
    bp.position.y -= 0.4f;
    stage = 7;
    break;
   case 7:
    DrawingComponent.get(ghost).spriteColor.a = 0.1f;
    bp.position.y -= 0.4f;
    stage = 8;
    break;
   case 8:
    DrawingComponent.get(ghost).spriteColor.a = 0f;
    DrawingComponent.get(ghost).spriteColor.set(1,1,1,0);
    DrawingComponent.get(ghost).texture = SpriteManager.get("girl");
    stage = 9;
    break;
   case 9:
    DrawingComponent.get(ghost).spriteColor.a = 0.1f;
    stage = 10;
    break;
   case 10:
    DrawingComponent.get(ghost).spriteColor.a = 0.2f;
    stage = 11;
    break;
   case 11:
    DrawingComponent.get(ghost).spriteColor.a = 0.35f;
    stage = 12;
    break;
   case 12:
    DrawingComponent.get(ghost).spriteColor.a = 0.45f;
    stage = 13;
    break;
   case 13:
    DrawingComponent.get(ghost).spriteColor.a = 0.60f;
    stage = 14;
    break;
   case 14:
    DrawingComponent.get(ghost).spriteColor.a = 0.75f;
    stage = 15;
    break;
   case 15:
    DrawingComponent.get(ghost).spriteColor.a = 0.90f;
    stage = 16;
    break;
   case 16:
    DrawingComponent.get(ghost).spriteColor.a = 1;
    stage = 17;
    break;
   case 17:
    nt = ApplicationLoop.instance.inGameTime+1_000_000_000;
    stage = 18;
    break;
   case 18:
    end();
    BodyPropertiesComponent.get(AliceBehaviourSystem.getAlice()).position.set(45,45);
    break;

  }
 }

 @Override
 protected void end()
 {
  background.drawingComponent.draw = false;
  image.drawingComponent.draw = false;
  text.drawingComponent.draw = false;

  SpawnGhost.play = true;
  Systems.collisionSystem.play = true;
  Systems.physicsSystem.play = true;
  Systems.keyBoardSystem.play = true;
  Systems.cutsceneKeyBoardSystem.play = false;

  eventSystemInstance.removeNowList.add(this);

  PhysicsComponent.get(AliceBehaviourSystem.getAlice()).velocity.set(0,0);
 }
}
