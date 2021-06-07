package com.mygdx.game.ext.core.components.presets;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Action;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class ActionComponent extends Component
{
 private static final Action.Arg1<Actor> defAction = (actor)->{ System.out.println("no action for " + actor.getClass().getSimpleName());};
 private static final ComputeableHashMap<ActionComponent> childList = new ComputeableHashMap<>();
 public Action.Arg1<Actor> action;

 private ActionComponent(Action.Arg1<Actor> action, Actor actor)
 {
  this.action = action;
  actor.addComponent(childList);
 }

 public static ActionComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new ActionComponent(defAction, actor));
 }
}
