package com.mygdx.game.ext.deprecated;

/*
// TODO: Cloneable
@Deprecated
public abstract class Actor<T extends Actor<T>> extends ComponentCaller
{
 public HashMap<String, Field> actorFields; // Fields, added by Components

 protected Texture texture;
 protected SpriteBatch batch;
 protected boolean doDrawing = true, doActing = true, doInputHandling = true;

 public Actor()
 {
  try { this.batch = Monitor.instance.getBatch(); } catch (NullPointerException e)
  { throw new NullPointerException("Initialize Monitor first!"); }

  actorFields = new HashMap<>();
 }

 public Vector2 drawPosition, drawSize;
 public Vector2 actualPosition, actualSize;

 public void draw(float ext)
 {
  if (!doDrawing) return;

  drawPosition.set(position);
  drawSize.set(size);

  // TODO : sprite name, getActor(name)
  if (texture==null) {throw new NullPointerException("Set texture for all your actors before drawing them!");}

  for (Component<?> component : components)
   if (component.type==ComponentType.GRAPHICS_COMPONENT)
    component.behave(this);

  batch.draw(texture, drawPosition.x, drawPosition.y, drawSize.x, drawSize.y);
 }

 public void act()
 {
  if (!doActing) return;

  for (Component<?> component : components)
   if (component.type==ComponentType.PHYSICS_COMPONENT)
   component.behave(this);
 }

 public void handleInput()
 {
  if (!doInputHandling) return;
 }

 @SuppressWarnings("unchecked")
 public T texture(Texture texture) { this.texture = texture; return (T) this; }
 @SuppressWarnings("unchecked")
 public T size(float width, float height) { size.x = width; size.y = height; return (T) this; }
 @SuppressWarnings("unchecked")
 public T position(float x, float y) { this.position.x = x; this.position.y = y; return (T) this;}
 @SuppressWarnings("unchecked")
 public T scene(Scene<?> scene, String name) {scene.actor(this); return (T) this;}

 protected ArrayList<Component<?>> components = new ArrayList<>();

 @SuppressWarnings("unchecked")
 public T component(Component<?>... components)
 {
  for (Component<?> component : components) component.init(this);
  this.components.addAll(Arrays.asList(components)); return (T) this;
 }

 public Vector2 getDrawPosition()
 {
  return drawPosition;
 }

 public Vector2 getDrawSize()
 {
  return drawSize;
 }

 public void stop() { doActing = false;doInputHandling = false;doDrawing = false; }
 public void resume() { doActing = true;doInputHandling = true;doDrawing = true; }
}


*/