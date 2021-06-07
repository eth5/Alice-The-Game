package com.mygdx.game.ext.core.components;

import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Func;

import java.util.HashMap;

public class ComputeableHashMap<T> extends ObjectMap<Actor, T>
{
 public T compute(Actor actor, Func<T> supply)
 {
  T value;
  if (containsKey(actor)) value = get(actor);
  else
   {
    value = supply.invoke();
    put(actor, value);
   }
  return value;
 }
}
