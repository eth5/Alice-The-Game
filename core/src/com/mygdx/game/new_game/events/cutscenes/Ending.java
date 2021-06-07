package com.mygdx.game.new_game.events.cutscenes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;
import com.mygdx.game.new_game.systems.AliceBehaviourSystem;

public class Ending extends Cutscene
{
 public static boolean seen = false;
 public static final boolean SHOW = true;

 public Ending(float x, float y)
 {
  super(x, y);
 }

 @Override
 public void play()
 {

  BodyPropertiesComponent.get(AliceBehaviourSystem.getAlice()).position.set(10,3);

  if (seen | (!SHOW)) {  eventSystemInstance.removeList.add(this); return;}
  seen = true;

  super.play();
  image.drawingComponent.draw = true;
  background.drawingComponent.spriteColor.set(Color.GRAY);
  image.drawingComponent.texture = SpriteManager.get("d3_1");
  image.drawingComponent.drawSize.set(6.07f*2.3f, 3.90f*2.3f);
  background.drawingComponent.spriteColor.set(0.5f, 0.5f, 0.5f, 1);
  image.bodyPropertiesComponent.position.set(1,1.5f);
  text.drawingComponent.draw = false;
 }

 private int stage;

 @Override
 public void continuePlaying()
 {
  if (cutsceneKeyBoardSystem.MOUSE_LEFT())
  {
   switch (stage)
   {
    case 0:
     image.drawingComponent.draw = true;
     image.drawingComponent.texture = SpriteManager.get("alice1");
     image.drawingComponent.drawSize.set(4.64f*3, 5.4f*3);
     image.bodyPropertiesComponent.position.set(-4,-2);
     background.drawingComponent.spriteColor.set(Color.WHITE);

     text.drawingComponent.draw = true;
     text.drawingComponent.texture = SpriteManager.get("d3_2");
     text.drawingComponent.drawSize.set(1.68f*5, 0.58f*5);
     text.bodyPropertiesComponent.position.set(8,5);
     stage = 1;
     break;
    case 1:
     text.drawingComponent.texture = SpriteManager.get("d3_3");
     text.drawingComponent.drawSize.set(5.34f*2, 3.07f*2);
     text.bodyPropertiesComponent.position.set(8,5);
     stage = 2;
     break;
    case 2:
     text.drawingComponent.draw = false;
     image.drawingComponent.spriteColor.set(1,0.8f,0.8f,0.9f);
     background.drawingComponent.spriteColor.set(0.8f,0.8f,0.8f,1);
     stage = 3;
     break;
    case 3:
     image.drawingComponent.spriteColor.set(1,0.6f,0.6f,0.7f);
     background.drawingComponent.spriteColor.set(0.6f,0.6f,0.6f,1);
     stage = 4;
     break;
    case 4:
     image.drawingComponent.spriteColor.set(1,0.3f,0.3f,0.4f);
     background.drawingComponent.spriteColor.set(0.4f,0.4f,0.4f,1);
     stage = 5;
     break;

    case 5:
     image.drawingComponent.draw = false;
     background.drawingComponent.spriteColor.set(Color.DARK_GRAY);
     stage = 6;
     break;

    case 6:
     text.drawingComponent.draw = true;
     text.drawingComponent.texture = SpriteManager.get("d3_4");
     text.drawingComponent.drawSize.set(4.76f*4, 1.22f*4);
     text.bodyPropertiesComponent.position.set(1.5f,4);
     stage = 7;
     break;
    case 7:
     text.drawingComponent.texture = SpriteManager.get("d3_5");
     text.drawingComponent.drawSize.set(2.96f*4, 0.61f*4);
     text.bodyPropertiesComponent.position.set(2.5f,5);
     stage = 8;
     break;
    case 8:
     image.drawingComponent.texture = SpriteManager.get("ending");
     text.drawingComponent.draw = false;
     image.drawingComponent.draw = true;
     image.drawingComponent.spriteColor = Color.WHITE;
     background.drawingComponent.spriteColor.set(Color.WHITE);
     image.drawingComponent.drawSize.set(12.71f*1.1f, 10.61f*1.1f);
     image.bodyPropertiesComponent.position.set(0,0);
     break;
   }
  }


  PhysicsComponent.get(AliceBehaviourSystem.getAlice()).velocity.set(0,0);
 }

 @Override
 protected void end()
 {
  System.out.println("do you understand me?");
 }
}
