package com.mygdx.game.ext.core.group;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.actor.Actor;

public class Group extends Array<Actor>
{
 public void remAll(Actor... actors) { this.removeAll( Array.with( actors ), true ); }
}
