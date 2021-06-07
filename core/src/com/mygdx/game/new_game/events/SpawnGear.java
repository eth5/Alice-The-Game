package com.mygdx.game.new_game.events;

import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.event.Event;
import com.mygdx.game.new_game.systems.AliceBehaviourSystem;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.drawing.entities.Alice;
import com.mygdx.game.new_game.drawing.item.Gear;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;

public class SpawnGear extends Event
{

 public SpawnGear(float x, float y)
 {
  super(x, y);
 }

 @Override
 public void play()
 {
  Alice alice = AliceBehaviourSystem.getAlice();
  BodyPropertiesComponent bp = BodyPropertiesComponent.get(alice);
  FirstAliceLevel.npc.add(gear = new Gear(bp.position.x+20, bp.position.y+10));// вот за такое хочется больно и сильно бить по рукам :)

  eventSystemInstance.removeList.add(this);
  eventSystemInstance.playingNow.add(this);
 }


 Gear gear;

 @Override
 public void continuePlaying()
 {
  PhysicsComponent physicsComponent = PhysicsComponent.get(gear);

  // TODO:  drawingComponent.rotate

  if (physicsComponent.position.x < 1) gear.touchedAlice = true;
  physicsComponent.velocity.x=-0.9f;
  DrawingComponent.get(gear).rotate += physicsComponent.velocity.x * -50;
  if (gear.touchedAlice)
  {
   eventSystemInstance.removeNowList.add(this);
   FirstAliceLevel.npc.remAll(gear);
   Systems.collisionSystem.remActor(gear);
  }

 }
}
