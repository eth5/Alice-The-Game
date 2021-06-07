package com.mygdx.game.new_game.drawing.cutscene;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.drawing.stat.Envy;
import com.mygdx.game.new_game.SpriteManager;

public class CutsceneImage extends Envy
{
 public DrawingComponent drawingComponent;
 public BodyPropertiesComponent bodyPropertiesComponent;

 public CutsceneImage()
 {
  super(0,0,1,1, null);
  this.bodyPropertiesComponent = BodyPropertiesComponent.get(this);
  this.drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = SpriteManager.get("gear");
  drawingComponent.draw = false;
 }
}
