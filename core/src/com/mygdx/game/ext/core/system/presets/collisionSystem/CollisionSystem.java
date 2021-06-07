package com.mygdx.game.ext.core.system.presets.collisionSystem;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.*;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.system.System;
import com.mygdx.game.ext.core.system.presets.DrawingSystem;
import com.mygdx.game.new_game.Systems;

import java.util.ArrayList;

public class CollisionSystem extends System
{
 private static final CollisionFilter collisionFilter = new CollisionFilter() {
  @Override
  public Response filter(Item item, Item other)
  {
   Actor actorB = (Actor)other.userData;
   CollisionComponent collisionComponent = CollisionComponent.get(actorB);

   switch (collisionComponent.collisionType)
   {
    case CollisionType.PLATFORM:
     float speedY = PhysicsComponent.get((Actor) item.userData).velocity.y;
     Rect otherRect = world.getRect(other);
     Rect itemRect = world.getRect(item);
     float topPlatform = otherRect.y + otherRect.h;
     if (itemRect.y - speedY < topPlatform) return Response.cross;
     else return Response.slide;

     case CollisionType.LIQUID:
     case CollisionType.BODY:
     return Response.cross;
    default:
     return Response.slide;
   }
  }
 };
 public static World<Actor> world = new World<>();
 @Override
 public void addActor(Actor... actors)
 {
  super.addActor(actors);
  for (Actor actor1 : actors) world.add(CollisionComponent.get(actor1).item, 0, 0, 0,0);
  updateActor(actors);
 }



 @Override
 public void remActor(Actor... actors)
 {
  super.remActor(actors);
  for (Actor actor1 : actors) removeItemFromWorld(CollisionComponent.get(actor1).item);
 }

 @Override
 public void remActor(Array<Actor> actors)
 {
  super.remActor(actors);
  for (Actor actor1 : actors) removeItemFromWorld(CollisionComponent.get(actor1).item);
 }
 private void removeItemFromWorld(Item<Actor> item)
 {
  // String text;
  if (world.hasItem(item))
  {
   world.remove(item);
   // text = item.userData.getClass().getSimpleName() + " removed!";
  }
  // else text = item.userData.getClass().getSimpleName() + " not in world!";

  // logger.info("removeItemFromWorld: " + text);
 }

 public void updateActor(Actor... actors)
 {

  for (Actor actor : actors)
  {
   BodyPropertiesComponent bp = BodyPropertiesComponent.get(actor);
   CollisionComponent cc = CollisionComponent.get(actor);

   world.update(cc.item, bp.position.x, bp.position.y, bp.size.x, bp.size.y);
  }
 }

 public void updateActor(float xoff, float yoff, Actor... actors)
 {

  for (Actor actor : actors)
  {
   BodyPropertiesComponent bp = BodyPropertiesComponent.get(actor);
   CollisionComponent cc = CollisionComponent.get(actor);

   world.update(cc.item, bp.position.x+xoff, bp.position.y+yoff, bp.size.x, bp.size.y);
  }
 }

 public CollisionSystem()
 {
  type = Type.PHYSICS_SYSTEM;
  priority = 10;
 }

 public void handle()
 {
  if (!play) return;
  // logger.info("Collision System");
  for (int i = 0; i < assignedActors.size; i++)
  {
   Actor actorA = assignedActors.get(i);
   PhysicsComponent actorAPhysics = PhysicsComponent.get(actorA);
   if (actorAPhysics.velocity.isZero())continue;

   CollisionComponent actorAcc = CollisionComponent.get(actorA);
   if (actorAcc.collisionType != CollisionType.BODY) continue;

   Item<Actor> item = CollisionComponent.get(actorA).item;

   float targetX = actorAPhysics.position.x;
   float targetY = actorAPhysics.position.y;

   Response.Result result = world.move(item, targetX, targetY, collisionFilter);
   actorAPhysics.position.set(result.goalX, result.goalY);

   if (!result.projectedCollisions.isEmpty())
   {
    handleCollisions(actorA, actorAPhysics, actorAcc, result.projectedCollisions.others);
    handleExtrapolationBehaviour(actorA,targetX,targetY,result);
    setColorForDebugBox(result);
   }
   else{ actorAcc.isStanding = false; }

  }
 }



 private void handleExtrapolationBehaviour(final Actor actor, final float targetX, final float targetY, final Response.Result result)
 {
  DrawingComponent drawingComponent = DrawingComponent.get(actor);
  if (drawingComponent.useExtrapolation)
  {
   if (result.goalX != targetX) { drawingComponent.extrapolationX = false; drawingComponent.extrapolationOffNanoX = ApplicationLoop.instance.nextTickTime;}
   if (result.goalY != targetY) { drawingComponent.extrapolationY = false; drawingComponent.extrapolationOffNanoY = ApplicationLoop.instance.nextTickTime; }
  }
 }
 private void setColorForDebugBox(final Response.Result result)
 {
  if (DrawingSystem.DEBUG)
  {
   result.projectedCollisions.items.forEach( item1-> { DrawingComponent.get((Actor)item1.userData).debugCollisionColor = Color.RED;}); //debug
   result.projectedCollisions.others.forEach( other-> { DrawingComponent.get((Actor)other.userData).debugCollisionColor = Color.RED;}); //debug
  }
 }
 private void handleCollisions(final Actor actor, final PhysicsComponent actorPh, final CollisionComponent actorCC, final ArrayList<Item> contacts )
 {

  for (Item<Actor> actorItem : contacts)
  {
   Actor collisionActor = actorItem.userData;
   CollisionComponent cc = CollisionComponent.get(collisionActor);

   if (!actorCC.isStanding)
   {
    Rect actorRect = world.getRect(actorCC.item);
    Rect otherRect = world.getRect(cc.item);
    float topYOther = otherRect.y + otherRect.h;
    actorCC.isStanding = actorRect.y - actorPh.velocity.y > topYOther;
   }
   // java.lang.System.out.println("handle collision " + actor.getClass().getSimpleName() + " + " + collisionActor.getClass().getSimpleName());
   cc.touch.invoke(actor);
   actorCC.touch.invoke(collisionActor);
  }

 }
}
