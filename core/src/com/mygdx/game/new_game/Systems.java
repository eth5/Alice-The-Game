package com.mygdx.game.new_game;

import com.mygdx.game.ext.core.system.EventSystem;
import com.mygdx.game.ext.core.system.presets.AnimationSystem;
import com.mygdx.game.ext.core.system.presets.DrawingSystem;
import com.mygdx.game.ext.core.system.presets.PhysicsSystem;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionSystem;
import com.mygdx.game.new_game.systems.AliceBehaviourSystem;
import com.mygdx.game.new_game.systems.CutsceneKeyBoardSystem;
import com.mygdx.game.new_game.systems.KeyBoardSystem;

public class Systems
{
 public static final AnimationSystem animationSystem = new AnimationSystem();
 public static final CollisionSystem collisionSystem = new CollisionSystem();
 public static final PhysicsSystem physicsSystem = new PhysicsSystem();
 public static final DrawingSystem drawingSystem = new DrawingSystem();
 public static final KeyBoardSystem keyBoardSystem = new KeyBoardSystem();
 public static final CutsceneKeyBoardSystem cutsceneKeyBoardSystem = new CutsceneKeyBoardSystem();
 public static final AliceBehaviourSystem aliceBehaviourSystem = new AliceBehaviourSystem();
 public static final EventSystem eventSystem = new EventSystem();
}
