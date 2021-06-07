package com.mygdx.game.new_game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.ext.core.system.System;

public class CutsceneKeyBoardSystem extends System
{

 private boolean MOUSE_RIGHT = false, MOUSE_LEFT = false;

 {
  type = Type.RENDER_SYSTEM; // PHYSICS_COMPONENT;
  priority = 3;
 }

 @Override
 public void handle()
 {
  if (!play) return;
  behave();
 }

 @Override
 protected void behave()
 {
  if (!MOUSE_RIGHT) MOUSE_RIGHT = Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT);
  if (!MOUSE_LEFT) MOUSE_LEFT = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
 }

 public boolean MOUSE_RIGHT()
 {
  if (MOUSE_RIGHT) {MOUSE_RIGHT=false; return true;} else return false;
 }

 public boolean MOUSE_LEFT()
 {
  if (MOUSE_LEFT) {MOUSE_LEFT=false; return true;} else return false;
 }
}
