package com.mygdx.game.new_game.events.cutscenes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;
import com.mygdx.game.new_game.systems.AliceBehaviourSystem;

public class Greeting extends Cutscene
{
 public static boolean seen = false;
 public static final boolean SHOW = true;

 public Greeting(float x, float y)
 {
  super(x, y);
 }

 @Override
 public void play()
 {
  if (seen | (!SHOW)) {  eventSystemInstance.removeList.add(this); return;}
  seen = true;

  super.play();
  image.drawingComponent.texture = SpriteManager.get("tutorial");
  image.drawingComponent.drawSize.set(9.62f*1.5f,5.13f*1.5f);
  background.drawingComponent.spriteColor.set(Color.GRAY);
  image.bodyPropertiesComponent.position.set(2,2);

  text.drawingComponent.draw = false;

 }

 int stage;

 @Override
 public void continuePlaying()
 {

  if (cutsceneKeyBoardSystem.MOUSE_LEFT())
  {
   switch (stage)
   {
    case 0:
     image.drawingComponent.texture = SpriteManager.get("alice1");
     image.drawingComponent.drawSize.set(4.64f*3, 5.4f*3);
     image.bodyPropertiesComponent.position.set(-4,-2);
     background.drawingComponent.spriteColor.set(Color.WHITE);
     stage = 1;
     break;
    case 1:
     text.drawingComponent.texture = SpriteManager.get("d1_1");
     text.drawingComponent.drawSize.set(1.56f*6, 1.50f*6);
     text.bodyPropertiesComponent.position.set(7,3);
     text.drawingComponent.draw = true;
     stage = 2;
     break;
    case 2:
     text.drawingComponent.texture = SpriteManager.get("d1_2");
     text.drawingComponent.drawSize.set(1.62f*6, 1.46f*6);
     text.bodyPropertiesComponent.position.set(7,3);
     stage = 3;
     break;
    case 3:
     background.drawingComponent.spriteColor.set(Color.DARK_GRAY);
     text.drawingComponent.texture = SpriteManager.get("d1_3");
     text.drawingComponent.drawSize.set(8.34f*2, 3.33f*2);
     text.bodyPropertiesComponent.position.set(1, 3);
     image.drawingComponent.draw = false;
     stage = 4;
     break;
    case 4:
     text.drawingComponent.texture = SpriteManager.get("d1_4");
     text.bodyPropertiesComponent.position.set(0.5f, 3);
     stage = 5;
     break;
    case 5:
     text.drawingComponent.texture = SpriteManager.get("d1_5");
     text.drawingComponent.drawSize.set(10.62f*1.2f, 8.95f*1.2f);
     text.bodyPropertiesComponent.position.set(1, 1f);

     stage = 6;
     break;
    case 6:
     image.drawingComponent.draw = true;
     text.drawingComponent.draw = false;
     image.drawingComponent.texture = SpriteManager.get("alice1");
     image.drawingComponent.drawSize.set(4.64f*3, 5.4f*3);
     image.bodyPropertiesComponent.position.set(-4,-2);
     background.drawingComponent.spriteColor.set(Color.WHITE);
     stage = 7;
     break;
    case 7:
     text.drawingComponent.draw = true;
     text.drawingComponent.texture = SpriteManager.get("d1_6");
     text.drawingComponent.drawSize.set(1.54f * 4, 0.81f * 4);
     text.bodyPropertiesComponent.position.set(8, 4);
     stage = 8;
     break;
    case 8:
     text.drawingComponent.texture = SpriteManager.get("d1_7");
     text.drawingComponent.drawSize.set(2.06f * 4, 1.80f * 4);
     text.bodyPropertiesComponent.position.set(7.5f, 4);
     stage = 9;
     break;
    case 9:
     end();
   }
  }


  PhysicsComponent.get(AliceBehaviourSystem.getAlice()).velocity.set(0,0);
 }
}
