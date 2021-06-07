package com.mygdx.game.new_game.events;

import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.event.Event;
import com.mygdx.game.new_game.drawing.entities.Alice;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;

public class UseSword extends Event
{
 public static boolean isPlaying = false;
 public static int frame;

 Alice alice;
 public UseSword(float x, float y, Alice alice)
 {
  super(x, y);
  this.alice = alice;
 }

 @Override
 public void play()
 {
  if (isPlaying) return;
  eventSystemInstance.playingNow.add(this);
  isPlaying = true;

  DrawingComponent.get(alice).draw = false;
  DrawingComponent.get(alice.fightAnimation).draw = true;

  AnimationComponent.get(alice.fightAnimation).animation.delta = 0;

  swordBox = alice.swordBox;

 }

 Alice.SwordBox swordBox;

 @Override
 public void continuePlaying()
 {

  frame = AnimationComponent.get(alice.fightAnimation).animation.getCurrentFrameIndex();

  if (frame==6)
  {
   reset();

  }
 }

 public void reset()
 {
  isPlaying = false;
  eventSystemInstance.removeNowList.add(this);

  DrawingComponent.get(alice).draw = true;
  DrawingComponent.get(alice.fightAnimation).draw = false;
  AnimationComponent.get(alice.fightAnimation).animation.delta = 0;
 }
}
