package com.mygdx.game.new_game.drawing.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Action;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationData;
import com.mygdx.game.ext.core.components.presets.movement.JumpComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_game.systems.AliceBehaviourSystem;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.drawing.item.Heart;
import com.mygdx.game.new_game.events.UseSword;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;

public class Alice extends Actor
{
 Logger logger = new Logger("ALICE",Logger.INFO);

 private int heartAmount;

 public long invisibilityStartTime;

 private Action.Arg1<Actor> touch =
         actor->
         {
          if (actor.getClass().getSimpleName().equals("Gear") | actor.getClass().getSimpleName().equals("Ghost"))
          {
           damage();
           // if (ApplicationLoop.instance.inGameTime>invisibilityStartTime+1_000_000_000L)takeDamage();
          }
         };

 public void damage()
 {
  if (ApplicationLoop.instance.inGameTime>invisibilityStartTime+1_000_000_000L)takeDamage();
 }
 private void takeDamage()
 {
  invisibilityStartTime = ApplicationLoop.instance.inGameTime;
  removeHeart();
 }
 private final Action onDead;
 public Alice(Action onDead)
 {
  this.onDead = onDead;
  //init(x, y);
 }
 public void init(float x,float y)
 {
  PhysicsComponent physicsComponent = PhysicsComponent.get(this);
  physicsComponent.position.set(x,y);
  physicsComponent.size.set(2f,6.49f/1.3f);

  DrawingComponent drawingComponent = DrawingComponent.get(this);

  drawingComponent.texture = SpriteManager.get("alice_run/0");

  drawingComponent.offset.set(-1.9f,0);
  drawingComponent.drawSize.set(5.14f/1.3f,6.49f/1.3f);
  drawingComponent.useExtrapolation = true;


  CollisionComponent cc = CollisionComponent.get(this);
  cc.collisionType = CollisionType.BODY;
  cc.touch = touch;

  // cc.box.setPosition(x,y);
  // cc.box.setSize(physicsComponent.size.x, physicsComponent.size.y);

  Systems.physicsSystem.addActor(this);
  Systems.collisionSystem.addActor(this);

  AnimationComponent animationComponent = AnimationComponent.get(this);
  animationComponent.animation = new AnimationData(0.08f, Animation.PlayMode.LOOP,
          SpriteManager.getAnimations("alice_run", 6)
  );

  Systems.animationSystem.addActor(this);
  AliceBehaviourSystem.setAlice(this);

  JumpComponent.get(this);
fightAnimation= new FightAnimation(this);

  FirstAliceLevel.alicel.add(fightAnimation);
 }
 public class FightAnimation extends Actor
 {
  private void init()
  {
   DrawingComponent drawingComponent = DrawingComponent.get(this);
   drawingComponent.texture = SpriteManager.get("alice_fight/0");
   drawingComponent.offset.set(0.5f,0);
   drawingComponent.draw = false;
  }

  public FightAnimation(Alice alice)
  {
   fightAnimation = this;

   BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(this);

   bodyPropertiesComponent.position = DrawingComponent.get(alice).drawPosition;
   bodyPropertiesComponent.size.set(8.47f/1.3f, 6.81f/1.3f);

   DrawingComponent drawingComponent = DrawingComponent.get(this);
   drawingComponent.texture = SpriteManager.get("alice_fight/0");
   drawingComponent.offset.set(0.5f,0);
   drawingComponent.draw = false;

   AnimationComponent animationComponent = AnimationComponent.get(this);
   animationComponent.animation = new AnimationData(0.08f, Animation.PlayMode.LOOP,
           SpriteManager.getAnimations("alice_fight",7)
//     SpriteManager.textures.get("fight_a1"),
//     SpriteManager.textures.get("fight_a2"),
//     SpriteManager.textures.get("fight_a3"),
//     SpriteManager.textures.get("fight_a4"),
//     SpriteManager.textures.get("fight_a5"),
//     SpriteManager.textures.get("fight_a6"),
//     SpriteManager.textures.get("fight_a7")
   );

   Systems.animationSystem.addActor(this);

    useSword = new UseSword(0,0, alice);
    swordBox = new SwordBox(alice);

  }
 }

 public UseSword useSword;
 public SwordBox swordBox;

 private Array<Heart> hearts = new Array<>();;

 private Heart heart;

 public FightAnimation fightAnimation;

 public void initHearts(int hearts)
 {
  for (Heart heart : this.hearts) FirstAliceLevel.interfaceL.remAll(heart);
  this.hearts.clear();
  for (int i = 0; i < hearts; i++) addHeart();
  sort();
 }
 public void removeHeart()
 {
  heart = hearts.pop();
  FirstAliceLevel.interfaceL.remAll(heart);
  sort();

  // logger.info("Alice has lost one heart ("+hearts.size+" left)");

  if (hearts.size==0)
  {
   logger.info("Alice has died");

   onDead.invoke();

   //logger.error("Здесь игра должна заново вызывать putActors(), а старых удалить. Грубо говоря, перезапустить уровень. Если я забыла это реализовать, пожалуйста, сделай это");
   //System.exit(1);
  }
 }

 public void addHeart()
 {
  heart = new Heart(0,12-2.5f);
  hearts.add(heart);
  FirstAliceLevel.interfaceL.add(heart);
  sort();
  // logger.info("Alice has got one heart ("+hearts.size+" left)");
 }


 int i;
 private void sort()
 {
  i = 0;
  for (Heart heartz: hearts)
  {
   BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(heartz);

   bodyPropertiesComponent.position.x = i*1.8f+0.5f+0.2f*i;
   i++;
  }
 }



 public class SwordBox extends Actor
 {
  private Action.Arg1<Actor> touch2 =
    actor->
    {
     if (actor.getClass().getSimpleName().equals("Ghost"))
     {
      if (UseSword.isPlaying && UseSword.frame>2) ((Ghost)actor).alive = false; // TODO: use component for this
     }
    };
  public SwordBox(Alice alice)
  {
   swordBox = this;


   PhysicsComponent physicsComponent = PhysicsComponent.get(this);
   physicsComponent.position.set(5,5);
   physicsComponent.size.set(3.1f, 1);
   physicsComponent.useGravity = true;

   DrawingComponent.get(swordBox).draw = false;

   CollisionComponent cc = CollisionComponent.get(this);
   cc.collisionType = CollisionType.BODY;
   cc.touch = touch2;

   // cc.box.setPosition(x,y);
   // cc.box.setSize(physicsComponent.size.x, physicsComponent.size.y);

   Systems.physicsSystem.addActor(this);
   Systems.collisionSystem.addActor(this);
  }
 }
}
