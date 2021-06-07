package com.mygdx.game.ext.core.components.presets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class AnimationData extends Animation<TextureAtlas.AtlasRegion>
{
 public float delta;
 public AnimationData(float frameDuration, PlayMode playMode, TextureAtlas.AtlasRegion... keyFrames)
 {
  super(frameDuration, new Array<>(keyFrames), playMode);
 }
 public AnimationData(float frameDuration, PlayMode playMode, Array<TextureAtlas.AtlasRegion> keyFrames)
 {
  super(frameDuration, keyFrames, playMode);
 }

 public TextureAtlas.AtlasRegion update(float delta)
 {
  this.delta += delta;
  return getKeyFrame(this.delta);
 }
 public int getCurrentFrameIndex()
 {
  return getKeyFrameIndex(delta);
 }

 public void setFrameIndex(int index)
 {
  delta = getFrameDuration() * index;
 }

}
