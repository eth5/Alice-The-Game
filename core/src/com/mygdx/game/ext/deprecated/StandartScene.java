package com.mygdx.game.ext.deprecated;

@Deprecated
public class StandartScene
{
 /*
 private final String name;
 private final Monitor monitor;
 private final ExtendField field;
 private final SpriteBatch batch;
 private final ShapeRenderer liner;

 @Deprecated
 protected final Map<String, StandartActor> listDobjectsByName = new HashMap<>();
 @Deprecated
 private final List<StandartActor> sortedDobjectsForDraw = new ArrayList<>();
 @Deprecated
 private final List<Integer> layers = new ArrayList<>();

 public boolean drawGrid;

 private float width, height;

 // TODO: RENDER SCENE GRID

 public StandartScene(String name, ExtendField field, float width, float height)
 {
  this.name = name; this.field = field;
  this.monitor = field.monitor; this.camera = field.camera;
  this.batch = field.batch; this.liner = field.liner;
  this.width = width; this.height = height;

  Monitor.log.info("Scene \""+name+"\" was created");

  field.camera.update();
  field.liner.setProjectionMatrix(field.camera.combined);
 }

 /**Provides standard draw extrapolation system a-la: draw(curPos+speed*extrapolation)*/
 /*
 public void iterDraw(float extrapolation)
 {
  monitor.setField(field);


  batch.begin();
  //TODO: Add different layers support 0->128
  //dobjects.forEach((k,obj) -> obj.draw(extrapolation));

  for (StandartActor standartActor : sortedDobjectsForDraw) standartActor.draw(extrapolation);

  batch.end();

 // field.cameraController.setPosition(-dobjects.get("hero").position.x-dobjects.get("hero").speed.x*extrapolation+4,0);
  if (drawGrid) drawGrid();
 }
 */
}

