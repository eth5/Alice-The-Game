package com.mygdx.game.ext.core.data;

import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.ext.core.actor.interfaces.Action;

class AssetLoader
{
	public static class Data
	{
		int loadedPercent;
		private Data setPercent(final float percent)
		{
			loadedPercent = (int)percent;
			return this;
		}
	}
	private AssetManager assetManager;
	private final Action.Arg1<Data> onLoad;
	private final Action.Arg1<AssetManager> onLoaded;
	public AssetLoader(AssetManager assetManager, Action.Arg1<Data> onLoad, Action.Arg1<AssetManager> onLoaded)
	{
		this.assetManager = assetManager;
		this.onLoad = onLoad;
		this.onLoaded = onLoaded;
	}

	private final Data data = new Data().setPercent(0f);
	private boolean loaded = false;
	public void update()
	{
		if (loaded)throw new Error("Хватит загружать!");
		loaded = assetManager.update();
		if (loaded) { onLoaded.invoke(assetManager); assetManager = null; }
		else onLoad.invoke(data.setPercent(assetManager.getProgress() * 100));
	}
}
