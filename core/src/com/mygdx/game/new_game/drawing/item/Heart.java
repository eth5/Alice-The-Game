package com.mygdx.game.new_game.drawing.item;

import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.drawing.stat.Envy;

public class Heart extends Envy
{
 public Heart(float x, float y)
 {
  super(x, y, 1.8f, 1.9f, SpriteManager.get("heart"));
 }
}
