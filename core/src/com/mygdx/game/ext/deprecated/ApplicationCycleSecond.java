package com.mygdx.game.ext.deprecated;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;

/**
 * @Deprecated , use {@link ApplicationLoop} instead
 * This class stands for separation game loop in 3 parts:
 * Physics loop, graphic loop and handle input loop
 * Graphics and input (FPS) are called on max fps, depends is v-sync on or off
 * Physics are being called TICK_AMOUNT times for second (TPS)
 * Extrapolation value depends on TPS
 */

@Deprecated
public class ApplicationCycleSecond extends ApplicationAdapter
{
 Logger logger = new Logger("GAMELOOP", Logger.INFO);
 public static final boolean DEBUG = false;
 private long TIME_COUNT_START = TimeUtils.nanoTime();
 private final long SECOND_IN_NANO = 1_000_000_000;
 private final long TICK_AMOUNT = 5;
 private final long TICK_IN_NANO = SECOND_IN_NANO/TICK_AMOUNT;

 public long
   FPS, TPS, // displayable
   time, tick, second, // use in code
   nextTickCallTime, nextSecondCallTime, prevTickCallTime, // use to measure extrapolation
   prevTime; // use to save while close app

 public float extrapolation = 0.5f;

 //TODO: replace nextTickCallTime everywhere with prevTickCallTime
 @Override
 public void render()
 {
  time = TimeUtils.nanoTime() - TIME_COUNT_START;
  FPS++;

  if (time > nextTickCallTime && TPS!=TICK_AMOUNT)
  {
   TPS++;
   prevTickCallTime = nextTickCallTime;
   nextTickCallTime = time+TICK_IN_NANO-(time-nextTickCallTime);
   tick++;

   calcPhysic();
  }

  handleInput();

  if (time > nextSecondCallTime)
  {
   nextSecondCallTime = time+SECOND_IN_NANO-(time-nextSecondCallTime);
   second++;

   if (DEBUG) logger.info("FPS:"+ FPS+" TPS:" +TPS + " TICK:"+tick);
   FPS = 0; TPS = 0;
  }

  // TODO: extrapolation in first second isnt right, this can be fixed with this check
  //  if time is less than 1 sec, dont change extrapolation.
  //  find better solution
  if (time>SECOND_IN_NANO) extrapolation = (time-prevTickCallTime) / (TICK_IN_NANO+0f);
  System.out.println("("+time+"-"+prevTickCallTime+") /"+TICK_IN_NANO);

  drawGraphic();

  prevTime = time;
 }

 // DEBUG

 @Override
 public void create()
 {
  logger.info("Application cycle has launched successfully");

  texture = new Texture(Gdx.files.internal("badlogic.jpg"));
  batch = new SpriteBatch();
 }


 Texture texture;
 SpriteBatch batch;

 public void drawGraphic()
 {
  Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  pos += sp*extrapolation;

  batch.begin();
  batch.draw(texture,pos,0);
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
 // DEBUG


 @Override
 public void pause()
 {
  System.out.println(prevTime);
 }

 @Override
 public void resume()
 {
  System.out.println(time);
 }

 @Override
 public void resize(int width, int height)
 {
 }


}
