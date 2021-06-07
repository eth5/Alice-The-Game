package com.mygdx.game.new_game.events;

import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.event.Event;
import com.mygdx.game.new_game.drawing.entities.Ghost;
import com.mygdx.game.new_game.systems.AliceBehaviourSystem;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.drawing.entities.Alice;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;

import java.util.ArrayList;

public class SpawnGhost extends Event
{

 public SpawnGhost(float x, float y)
 {
  super(x, y);
 }

 int sign;
 BodyPropertiesComponent bp;
 public long estTime;
 Alice alice;

 @Override
 public void play()
 {
  alice = AliceBehaviourSystem.getAlice();
  bp = BodyPropertiesComponent.get(alice);

  eventSystemInstance.removeList.add(this);
  eventSystemInstance.playingNow.add(this);
 }


 Ghost ghostz;
 ArrayList<Ghost> arrayList = new ArrayList<>();
 ArrayList<Ghost> toBeDeleted = new ArrayList<>();

 private long curTime;

 public static boolean play = true;

 @Override
 public void continuePlaying()
 {
  if (!play) return;
  curTime = ApplicationLoop.instance.inGameTime;

  if (ApplicationLoop.instance.inGameTime>estTime && (!(bp.position.x>200 && bp.position.x<230) && (!(bp.position.x>580 && bp.position.x<600))))
  {
   estTime = ApplicationLoop.instance.inGameTime+(long) (Math.random()*3_500_000_000L+1_000_000_000L);

   sign = (Math.random()>0.1f) ? 1 : -1;

   FirstAliceLevel.environment.add(ghostz = new Ghost((float) (bp.position.x+30*sign+sign*20*Math.random()), 0.1f));
   arrayList.add(ghostz);
   ghostz.sign = sign;
   ghostz.dieTime = curTime+30_000_000_000L;

   if (sign==-1) DrawingComponent.get(ghostz).flipX = true;
  }



  for (Ghost ghost:arrayList)
  {
   PhysicsComponent physicsComponent = PhysicsComponent.get(ghost);

   if (physicsComponent.position.y < 0.5f) physicsComponent.velocity.y += 1.3f;
   physicsComponent.velocity.x=-ghost.sign*0.7f;

   if (!ghost.alive | curTime>ghost.dieTime | physicsComponent.position.x<1)
   {
    FirstAliceLevel.npc.remAll(ghost);
    toBeDeleted.add(ghost);
    Systems.collisionSystem.remActor(ghost);
   }

  }
  arrayList.removeAll(toBeDeleted);
  toBeDeleted.clear();
 }

 public void removeGhost()
 {

 }
}
