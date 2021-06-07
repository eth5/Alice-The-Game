package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.system.System;

public class AnimationSystem extends System
{
 public AnimationSystem()
 {
  type = Type.RENDER_SYSTEM;
  priority = 99;
 }
 private float delta;
 @Override
 public void handle()
 {
  delta = Gdx.graphics.getDeltaTime(); // TODO : Fix with inGameTime -> нет работает некорректно, я проверял
  super.handle();
 }

 private AnimationComponent animationComponent;
 private DrawingComponent drawingComponent;

 protected void loadFields()
 {
  animationComponent = AnimationComponent.get(actor);
  drawingComponent = DrawingComponent.get(actor);
 }
 protected void behave()
 {
  TextureAtlas.AtlasRegion texture = animationComponent.animation.update(delta);
  drawingComponent.texture = texture;
 }
}
