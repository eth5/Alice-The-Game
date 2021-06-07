package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class BodyPropertiesComponent extends Component
{
 public Vector2 position = new Vector2(1,1);
 public Vector2 size = new Vector2(1,1);



 private static final ComputeableHashMap<BodyPropertiesComponent> childList = new ComputeableHashMap<>();
 private BodyPropertiesComponent(Actor actor)
 {
  actor.addComponent(childList);
 }

 public static BodyPropertiesComponent get(Actor actor)
 {
  return childList.compute(actor, ()-> new BodyPropertiesComponent(actor));
 }
}
