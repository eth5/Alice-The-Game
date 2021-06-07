package com.mygdx.game.ext.core.components.presets.movement;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class LF4DComponent extends Component
{
 protected static final ComputeableHashMap<LF4DComponent> childList = new ComputeableHashMap<>();

 protected LF4DComponent(LF4DComponent LF4DComponent) {}
 protected LF4DComponent(Actor actor)
 {
  this.actor = actor;
  actor.addComponent(childList);
 }

 protected Actor actor;

 public static LF4DComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new LF4DComponent(actor));
 }

}
