package com.mygdx.game.ext.core.data;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.ext.core.actor.interfaces.Action;
import com.mygdx.game.ext.core.drawing.view.CoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;

public class AssetLoaderScene extends Scene
{
	private Action.Arg1<AssetManager> onLoaded;
	private AssetManager assetManager;
	private AssetLoader assetLoader;
	private boolean ready;
	public AssetLoaderScene(CoordinateGrid field, float width, float height)
	{
		super("Loader", field, width, height);
	}
	public AssetLoaderScene setAssetToLoad(final AssetManager assetManager)
	{
		this.assetManager = assetManager;
		return this;
	}
	public AssetLoaderScene setActionOnLoaded(final Action.Arg1<AssetManager> onLoaded)
	{
		this.onLoaded = onLoaded;
		return this;
	}

	public AssetLoaderScene ready()
	{
		if (assetManager==null)throw new Error("Not ready! assetManager is null!");
		if (onLoaded==null)throw new Error("Not ready! onLoaded is null!");
		assetLoader = new AssetLoader( assetManager,this::onLoad, onLoaded );
		ready = true;
		return this;
	}

	private void onLoad(AssetLoader.Data data)
	{
		text = "loaded..." + data.loadedPercent+"%";
		System.out.println(text);
	}
	private String text = "loading...";
	private final BitmapFont bitmapFont = new BitmapFont();
	@Override
	public void iterDraw(float extrapolation)
	{
		if (ready)
		{
			assetLoader.update();
			batch.begin();
			bitmapFont.draw( batch, text, width/2, height/2);
			batch.end();
		}
	}
}
