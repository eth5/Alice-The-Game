package com.mygdx.game.ext.deprecated;

/*
public class MovementComponent extends Component<MovementComponent>
{
 public Field<Vector2> speed;
 public Vector2 acceleration;

 public MovementComponent()
 {
  type = ComponentType.PHYSICS_COMPONENT; // todo: remove comptype class
 }

 @Override
 public void init(Actor<?> actor)
 {
  if (actor.actorFields.get("speed") == null)
  {
   actor.actorFields.put("speed", new Field<>(Vector2::new));
  }

  speed = actor.actorFields.get("speed");
  speed.get().x = 0.1f;
 }

 @Override @SuppressWarnings("unchecked")
 public void behave(Actor<?> actor)
 {
  speed = actor.actorFields.get("speed");

  actor.position.add(speed.get());
 }
}
*/