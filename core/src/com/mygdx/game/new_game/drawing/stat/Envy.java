package com.mygdx.game.new_game.drawing.stat;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;

public abstract class Envy extends Actor
{
 public Envy(float x, float y, float width, float height, TextureAtlas.AtlasRegion texture)
 {
  BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(this);

  bodyPropertiesComponent.position.set(x,y);
  bodyPropertiesComponent.size.set(width, height);

  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = texture;
 }

}
