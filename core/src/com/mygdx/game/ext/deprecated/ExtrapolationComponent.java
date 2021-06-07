package com.mygdx.game.ext.deprecated;

/*
public class ExtrapolationComponent extends Component<ExtrapolationComponent>
{
 ApplicationLoop loop;

 public ExtrapolationComponent()
 {
  type = ComponentType.GRAPHICS_COMPONENT; // todo: remove comptype class
  loop = ApplicationLoop.instance;
 }

 Field<Vector2> speed;

 @Override
 public void init(Actor<?> actor)
 {
  if (actor.actorFields.get("speed") == null)
   actor.actorFields.put("speed", new Field<>(Vector2::new));
 }

 @Override @SuppressWarnings("unchecked")
 public void behave(Actor<?> actor)
 {
  speed = actor.actorFields.get("speed");

  actor.getDrawPosition().set(
    actor.position.x+speed.get().x*loop.extrapolation,
    actor.position.y+speed.get().y*loop.extrapolation
  );
 }
}
*/