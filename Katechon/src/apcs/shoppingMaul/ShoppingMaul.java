package apcs.shoppingMaul;

import java.awt.Color;

import apcs.katechon.KatechonGameBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.commands.CommandManager;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.SimpleCollisionEngine;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.logging.Log;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;
import apcs.shoppingMaul.commands.CountLeopardsCommand;
import apcs.shoppingMaul.commands.RemoveLeopardCommand;
import apcs.shoppingMaul.commands.AddLeopardCommand;

public class ShoppingMaul extends KatechonGameBase
{
	public static void main(String[] args)
	{
		IConfig config = new MappedConfig();
		config.setString(ConfigKey.TITLE, "Shopping Maul");
		
		new KatechonEngine(ShoppingMaul.class, config).start();
	}

	@Override
	public void init(final KatechonEngine engine)
	{
		Log.setDebugging(true);
		EngineManager.getInstance().addEngine(new SimpleCollisionEngine());
		
		engine.getSwingWindow().setBackgroundColor(Color.WHITE);
		
//		LeopardPack pack = new LeopardPack(ControlScheme.WSAD, 500, 500, 10, 5);
//		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(pack);
//		EngineManager.getInstance().getEngine(ICollidable.class).addItem(pack);
		
		int width = engine.getSwingWindow().getWidth();
		int height = engine.getSwingWindow().getHeight();
		
		int amountOfLeopards = 5;
		int speed = 10;
		Board board = new Board(ControlScheme.WSAD, speed, amountOfLeopards, width, height);
		
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(board);
		engine.addDrawable(board, 2);
		
		CommandManager.getInstance().registerCommand("{add|spawn} * {leopard|leopards}", new AddLeopardCommand(board));
		CommandManager.getInstance().registerCommand("remove * {leopard|leopards}", new RemoveLeopardCommand(board));
		CommandManager.getInstance().registerCommand("count leopards", new CountLeopardsCommand(board));
	}

	@Override
	public void onGameEnd()
	{
		
	}
}
