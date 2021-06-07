package com.mygdx.game.new_game.events.cutscenes;

import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.event.Event;

import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.drawing.cutscene.CutsceneImage;
import com.mygdx.game.new_game.drawing.cutscene.CutsceneText;
import com.mygdx.game.new_game.events.SpawnGhost;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;
import com.mygdx.game.new_game.systems.AliceBehaviourSystem;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.systems.CutsceneKeyBoardSystem;

public class Cutscene extends Event
{
 public Cutscene(float x, float y)
 {
  super(x, y);
  cutsceneKeyBoardSystem = Systems.cutsceneKeyBoardSystem;
 }

 @Override
 public void play()
 {
  stage = 0;
  Systems.collisionSystem.play = false;
  Systems.physicsSystem.play = false;
  Systems.cutsceneKeyBoardSystem.play = true;
  Systems.keyBoardSystem.play = false;
  SpawnGhost.play = false;

  eventSystemInstance.playingNow.add(this);
  eventSystemInstance.removeList.add(this);

  background.drawingComponent.texture = SpriteManager.get("background");
  background.drawingComponent.draw = true;
  background.drawingComponent.drawSize.set(30,12);

  image.drawingComponent.draw = true;
  text.drawingComponent.draw = true;
 }

 protected static CutsceneImage image;
 protected static CutsceneText text;
 protected static CutsceneImage background;

 int stage;

 CutsceneKeyBoardSystem cutsceneKeyBoardSystem;

 @Override
 public void continuePlaying()
 {
  super.continuePlaying();
 }

 public static void set(CutsceneImage imagez, CutsceneImage backgroundz, CutsceneText textz)
 {
  image = imagez;
  text = textz;
  background = backgroundz;
 }

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
