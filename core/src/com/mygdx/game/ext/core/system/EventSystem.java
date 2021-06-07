package com.mygdx.game.ext.core.system;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.event.Event;
import com.mygdx.game.ext.core.group.presets.Layer;

import java.util.ArrayList;
import java.util.Arrays;


/** if Master steps in event boundaries it starts continuously call event.play on that event */
public class EventSystem extends System
{

 public Matrix4 coordGrid;

 public void setLayer(Layer layer)
 {
  coordGrid = layer.getCoordinateGrid().camera.combined;
 }

 {
  type = Type.PHYSICS_SYSTEM; // PHYSICS_COMPONENT;
  Event.eventSystemInstance = this;
 }

 public final ArrayList<Event> events = new ArrayList<>();
 public final ArrayList<Event> playingNow = new ArrayList<>();
 public final ArrayList<Event> removeList = new ArrayList<>();
 public final ArrayList<Event> removeNowList = new ArrayList<>();

 private Actor master;

 private Vector2 position;
 private Vector2 size;

 @Override
 public void handle()
 {
  for (Event event: events)
  {
   if (position.x < event.position.x + event.size.x
     && position.x + size.x > event.position.x
     && position.y < event.position.y + event.size.y
     && position.y + size.y > event.position.y)
   {
    event.play();
   }
  }
 // java.lang.System.out.println(events);
  events.removeAll(removeList);
  removeList.clear();

  playingNow.forEach((Event::continuePlaying)); // For continious event, which must be called outside its own boundaries.

  playingNow.removeAll(removeNowList);
  removeNowList.clear();
 }

 public EventSystem setMaster(Actor master)
 {
  this.master = master;

  BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(master);

  position = bodyPropertiesComponent.position;
  size = bodyPropertiesComponent.size;

  return this;
 }

 public Actor getMaster()
 {
  return master;
 }

 @Override
 protected void behave()
 {
  super.behave();
 }


 public void addEvent(Event... events)
 {
  this.events.addAll(Arrays.asList(events));
 }

 public void reset()
 {
  playingNow.clear();
  events.clear();
 }


}
