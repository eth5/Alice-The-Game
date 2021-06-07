package com.mygdx.game.ext.core.drawing;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.new_game.SpriteManager;

/**
 * This class stands for separation game loop in 3 parts:
 * Physics loop, graphic loop and handle input loop
 * Graphics and input (FPS) are called on max fps, depends is v-sync on or off
 * Physics are being called TICK_AMOUNT times for second (TPS)
 * Extrapolation value depends on TPS
 */

public abstract class ApplicationLoop extends ApplicationAdapter
{
 // TODO : Interface, like View
 public static ApplicationLoop instance;

 public static Logger logger;
 private static final boolean DEBUG = false;

 private static final long SECOND_IN_NANO = 1_000_000_000;
 private static final long TICK_AMOUNT = 10;
 private static final float TICK_IN_NANO = SECOND_IN_NANO / (float)TICK_AMOUNT;

 private static final long ESTIMATED_MAX_FRAME_CALL = (long)(TICK_IN_NANO - TICK_IN_NANO/15);
 private final static byte MAX_TOLERATED_LATE_FRAMES = 5;

 public long realTime = TimeUtils.nanoTime(), renderDelta, inGameTime; // IN NANOSECONDS
 public long tick, nextTickTime, nextSecondTime, TPS, FPS;

 public float extrapolation = 0.5f;
 private byte lateFramesAmount = 0;

 @Override // TODO: USE WITHOUT super.create() lwjgl application check in desktop launcher
 public void create()
 {
  logger = new Logger("APPLICATION LOOP", Logger.INFO);
  logger.info("The application loop has started");
  instance = this;
  Monitor.instance = new Monitor();
 }

 /** If you feel the need to override this method, please call super.render() **/
 @Override
 public void render()
 {
  checkInitialization();
  FPS++;
  renderDelta = -(realTime - (realTime = TimeUtils.nanoTime())); // lastCallTime - curCallTime

  if (renderDelta > ESTIMATED_MAX_FRAME_CALL && ++lateFramesAmount > MAX_TOLERATED_LATE_FRAMES)
  {
   logger.error("Big delta call time detected (Perhaps resources are not available for this process (or ding-dong Kostya forgot to finish freezing dt in extraploation loop :/))");
   lateFramesAmount = 0;
  }
  else
  {
   if ( lateFramesAmount > MAX_TOLERATED_LATE_FRAMES) lateFramesAmount = 0;
   inGameTime += renderDelta; //Math.min( renderDelta, TICK_IN_NANO );
  }

  if (inGameTime > nextTickTime)
  {
   nextTickTime += TICK_IN_NANO;
   tick++; TPS++;
   extrapolation = 0;// (inGameTime-(nextTickTime-TICK_IN_NANO)) / (TICK_IN_NANO);
   calcPhysics();
  } else extrapolation = (inGameTime-(nextTickTime-TICK_IN_NANO)) / (TICK_IN_NANO);
  // handleInput();
  drawGraphics();

  if (inGameTime > nextSecondTime)
  {
   nextSecondTime += SECOND_IN_NANO;
   if (DEBUG) logger.info("FPS:"+FPS+" TPS:"+TPS);
   TPS = 0; FPS = 0;
  }
 }

 private void checkInitialization()
 {
  if (instance == null) throw new NullPointerException("Please call super.create() in your create() method first!");
 }

 public void drawGraphics() {}
 public void calcPhysics() {}
 // public void handleInput() {}

 private long pauseStartTime = 0;

 @Override
 public void resume()
 {
  long delta = TimeUtils.nanoTime() - pauseStartTime;
  nextTickTime += delta;
  inGameTime += delta;
  renderDelta = (-(realTime - (realTime = TimeUtils.nanoTime())))+delta;
  lateFramesAmount = 0;
 }

 @Override
 public void pause() { pauseStartTime = TimeUtils.nanoTime(); }

 @Override // TODO: USE WITHOUT super.resize() lwjgl application check in desktop launcher
 public void resize(int width, int height)
 {
  checkInitialization();
  Monitor.instance.update(width, height);
 }

 @Override
 public void dispose()
 {
  //todo add disposable resource
  SpriteManager.dispose();

 }
}

/*
 // FREEZES IN-GAME TIME, BUT RENDERING CONTINUES
 //inGameTime += (renderDelta < 100_000_000) ? renderDelta : freeze(); // if window was on hold more for than a 0.1 sec, it freezes time for that moment
 private int freeze()
 {
  if (DEBUG) logger.info("Time was frozen for "+renderDelta/1_000_000_000f+" sec"); return 0;
 }
 */

/* EXAMPLE OF EXTRAPOLATION */
 /*
 @Override
 public void create()
 {
  texture = new Texture(Gdx.files.internal("badlogic.jpg"));
  batch = new SpriteBatch();

  logger.info("Application cycle has launched successfully");
 }

 Texture texture;
 SpriteBatch batch;

 public void drawGraphic()
 {
  Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  batch.begin();
  batch.draw(texture,pos+sp*extrapolation,0);
  batch.end();
 }

 float sp=5, pos, lastpos;
 public void calcPhysic()
 {
  lastpos = pos;
  pos = lastpos+sp;
 }

 public void handleInput()
 {

 }
*/
// END OF EXAMPLE
