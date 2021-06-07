package com.mygdx.game.ext.core.event.presets;

import com.mygdx.game.ext.core.event.Event;

public class PlaySound extends Event
{
 public PlaySound(float x, float y)
 {
  super(x, y);
 }

 @Override
 public void play()
 {
  System.out.println("poop");

  eventSystemInstance.events.remove(this);
 }
}
