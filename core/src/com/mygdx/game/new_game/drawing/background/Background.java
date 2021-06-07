package com.mygdx.game.new_game.drawing.background;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.new_game.SpriteManager;

public class Background extends Actor
{
 public Background()
 {
  BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(this);

  bodyPropertiesComponent.position.set(0,0);
  bodyPropertiesComponent.size.set(50,12);

  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = SpriteManager.get("background");

  // collisionComponent.box.setPosition(x,y);
  // collisionComponent.box.setSize(width, height);
 }
}
