package com.mygdx.game.ext.deprecated;
/*
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.view.Monitor;
import com.mygdx.game.ext.drawable.components.Component;
import com.mygdx.game.ext.drawable.ComponentCaller;
import com.mygdx.game.ext.drawable.scenes.Scene;
import com.mygdx.game.ext.drawable.components.Field;
import com.mygdx.game.ext.drawable.components.ComponentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// TODO: Cloneable
@Deprecated
public abstract class actorcopy2<T extends actorcopy2<T>> extends ComponentCaller
{
 protected final Monitor monitor;
 protected Texture texture;
 protected SpriteBatch batch;
 protected boolean isVisible = true;

 protected Vector2 drawPosition = new Vector2(), drawSize = new Vector2();
 protected Vector2 size = new Vector2();

 public HashMap<String, Field> componentValues; // TODO: change integer with ComponentValue

 public actorcopy2()
 {
  this.monitor = Monitor.instance;

  try { this.batch = monitor.getBatch(); } catch (NullPointerException e)
  { throw new NullPointerException("Initialize Monitor first!"); }

  componentValues = new HashMap<>();
 }

 public void draw(float ext)
 {
  drawPosition.set(position);
  drawSize.set(size);

  // TODO : sprite name, getActor(name)
  if (texture==null) {Monitor.log.error("Set texture for SPRITE_NAME!"); System.exit(1);}

  for (Component<?> component : components)
   if (component.type==ComponentType.GRAPHICS_COMPONENT)
    component.behave(this);

  if (isVisible)
  batch.draw(texture, drawPosition.x, drawPosition.y, drawSize.x, drawSize.y);

 }

 public void calc()
 {
  for (Component<?> component : components)
   if (component.type==ComponentType.PHYSICS_COMPONENT)
   component.behave(this);
 }

 @SuppressWarnings("unchecked")
 public T texture(Texture texture) { this.texture = texture; return (T) this; }
 @SuppressWarnings("unchecked")
 public T visibility(boolean visible) { isVisible = visible; return (T) this; }
 @SuppressWarnings("unchecked")
 public T size(float width, float height) { size.x = width; size.y = height; return (T) this; }
 @SuppressWarnings("unchecked")
 public T position(float x, float y) { this.position.x = x; this.position.y = y; return (T) this;}
 @SuppressWarnings("unchecked")
 public T scene(Scene<?> scene, String name) {scene.actor(this); return (T) this;}

 protected ArrayList<Component<?>> components = new ArrayList<>();

 @SuppressWarnings("unchecked")
 public T component(Component<?>... components)
 {
  for (Component<?> component : components) component.init(this);
  this.components.addAll(Arrays.asList(components)); return (T) this;
 }

 public Vector2 getDrawPosition()
 {
  return drawPosition;
 }

 public Vector2 getDrawSize()
 {
  return drawSize;
 }
}


*/