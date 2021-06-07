package com.mygdx.game.new_game.scenes;

import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.ext.core.group.presets.Layer;

public class SceneLayers extends ObjectMap<Integer, Layer>
{

	public void createLayer(int id)
	{
		if (containsKey(id)) throw new IllegalArgumentException(id + "<- такой слой уже есть!");
		put(id, new Layer(null));
	}
}
