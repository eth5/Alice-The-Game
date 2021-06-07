package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class DrawingComponent extends Component
{
 private static final ComputeableHashMap<DrawingComponent> childList = new ComputeableHashMap<>();
 private static final TextureAtlas.AtlasRegion defaultTexture;
 public Vector2 drawPosition = new Vector2(0,0);
 static
 {
  Texture texture = new Texture("box.png");
  defaultTexture = new TextureAtlas.AtlasRegion(texture, 0, 0, 0, 0);
 }

 public Color spriteColor = new Color(Color.WHITE); // По default
 public boolean flipX, flipY;
 public final Vector2 offset = new Vector2();
 public final Vector2 drawSize = new Vector2();
 public boolean draw = true;

 public TextureAtlas.AtlasRegion texture;
 public boolean useExtrapolation = false;
 public boolean extrapolationX = true;
 public boolean extrapolationY = true;
 public long extrapolationOffNanoX = 0;
 public long extrapolationOffNanoY = 0;
 public int rotate;

 public Color debugCollisionColor = Color.PINK;


 private DrawingComponent(TextureAtlas.AtlasRegion texture, Actor actor)
 {
  this.texture = texture;
  drawSize.set(BodyPropertiesComponent.get(actor).size);
  actor.addComponent(childList);
 }

 public static DrawingComponent get(Actor actor)
 {
 // System.out.println(childList.compute(actor, () -> new DrawingComponent(nullTexture, actor)));
  return childList.compute(actor, () -> new DrawingComponent(defaultTexture, actor));
 }
}
