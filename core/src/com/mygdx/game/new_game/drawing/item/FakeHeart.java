package com.mygdx.game.new_game.drawing.item;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.new_game.Main;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.drawing.entities.Alice;
import com.mygdx.game.new_game.drawing.stat.Heart;
import com.mygdx.game.new_game.events.UseSword;

public class FakeHeart extends Heart
{
 public FakeHeart(float x, float y)
 {
  super(x, y);
  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = SpriteManager.get("fake_heart");
  drawingComponent.drawSize.set(1.85f,2.51f);
  drawingComponent.spriteColor.set(.3f,.3f,.3f,1);
 }

 @Override
 protected void touch(Actor actor)
 {
  if (deleted) return;
  if (actor instanceof Alice)
  {
   ((Alice) actor).damage();
   delete();
  }
  else if (actor instanceof Alice.SwordBox && UseSword.isPlaying && UseSword.frame>2) { delete(); }
 }
}
