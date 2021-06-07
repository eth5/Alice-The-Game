package com.mygdx.game.new_game;

import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;

public class Main extends ApplicationLoop
{
 public static Scene currentScene;

 @Override
 public void create()
 {
  super.create();
  System.out.println("here?");
  currentScene = new FirstAliceLevel("first-alice-level",new ExtendCoordinateGrid("coordinate-grid", 12),1000,1000);
 }

 private void load()
 {
//  Asset asset = new Asset();
//  FirstAliceLevel.setResourceToAsset(asset);
//  CoordinateGrid coordinateGrid = new ExtendCoordinateGrid("coordinate-grid", 12);
//  currentScene = new AssetLoaderScene(coordinateGrid, 1000,1000)
//          .setAssetToLoad(asset)
//          .setActionOnLoaded(
//                  a->
//                  {
//                   currentScene = new FirstAliceLevel(asset,"first-alice-level",coordinateGrid,1000,1000);
//                  })
//          .ready();
 }


 @Override
 public void drawGraphics()
 {
  currentScene.iterDraw(extrapolation);
 }

 @Override
 public void calcPhysics()
 {
  currentScene.iterPhys();
 }
}
