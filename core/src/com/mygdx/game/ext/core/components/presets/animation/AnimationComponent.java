package com.mygdx.game.ext.core.components.presets.animation;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class AnimationComponent extends Component
{
 private static final ComputeableHashMap<AnimationComponent> childList = new ComputeableHashMap<>();
 private static final AnimationData defaultAnimation = new AnimationData(1000f, Animation.PlayMode.LOOP, new TextureAtlas.AtlasRegion(new Texture(0,0, Pixmap.Format.Alpha),0,0,0,0));
 public AnimationData animation = defaultAnimation;

 public AnimationComponent(Actor actor) { actor.addComponent(childList); }

 public static AnimationComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new AnimationComponent(actor));
 }
}
