package com.mygdx.game.new_game.events;

import com.mygdx.game.ext.core.event.Event;

public class TestEvent extends Event
{
 public TestEvent(float x, float y)
 {
  super(x, y);
 }

 @Override
 public void play()
 {
  System.out.println("ой");
  eventSystemInstance.events.remove(this);
 }
}
