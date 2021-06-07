package com.mygdx.game.new_game.events.cutscenes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.systems.AliceBehaviourSystem;

public class MeetingAlice2 extends Cutscene
{
 public static boolean seen = false;
 public static final boolean SHOW = true;

 public MeetingAlice2(float x, float y)
 {
  super(x, y);
  size.y+=5;
 }

 @Override
 public void play()
 {
  if (seen | (!SHOW)) {  eventSystemInstance.removeList.add(this); return;}
  seen = true;

  super.play();
  image.drawingComponent.texture = SpriteManager.get("alice2");
  image.drawingComponent.drawSize.set(4.11f*3, 4.68f*3);
  background.drawingComponent.spriteColor.set(Color.WHITE);
  image.bodyPropertiesComponent.position.set(-4,-2);
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
     text.drawingComponent.draw = true;
     text.drawingComponent.texture = SpriteManager.get("d2_1");
     text.drawingComponent.drawSize.set(1.95f*6, 1.56f*6);
     text.drawingComponent.spriteColor.set(Color.BLACK);
     text.bodyPropertiesComponent.position.set(7,2);
     stage = 1;
     break;
    case 1:
     text.drawingComponent.spriteColor.set(Color.WHITE);
     text.drawingComponent.texture = SpriteManager.get("d2_2");
     text.drawingComponent.drawSize.set(2.24f*6, 1.58f*6);
     text.bodyPropertiesComponent.position.set(7,2);
     stage = 2;
     break;
    case 2:
     text.drawingComponent.texture = SpriteManager.get("d2_3");
     text.drawingComponent.drawSize.set(1.43f*6, 1.1f*6);
     text.bodyPropertiesComponent.position.set(7,3);
     stage = 3;
     break;
    case 3:
     image.drawingComponent.texture = SpriteManager.get("alice1");
     image.drawingComponent.drawSize.set(4.64f*3, 5.4f*3);
     image.bodyPropertiesComponent.position.set(-4,-2);
     background.drawingComponent.spriteColor.set(Color.WHITE);

     text.drawingComponent.texture = SpriteManager.get("d2_4");
     text.drawingComponent.drawSize.set(2.04f*6, 1.39f*6);
     text.bodyPropertiesComponent.position.set(7,3);
     stage = 4;
     break;
    case 4:
     end();
     break;
   }
  }


  PhysicsComponent.get(AliceBehaviourSystem.getAlice()).velocity.set(0,0);
 }
}
