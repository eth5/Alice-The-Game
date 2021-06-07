package com.mygdx.game.ext.core.event;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.system.EventSystem;

public abstract class Event
{
 public static EventSystem eventSystemInstance;

 public Vector2 position = new Vector2(30,5);
 public Vector2 size = new Vector2(2,2);

 public abstract void play();

 public Event(float x, float y)
 {
  position.x = x;
  position.y = y;
 }

 public void continuePlaying() {}
}
