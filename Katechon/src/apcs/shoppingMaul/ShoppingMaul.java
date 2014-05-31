package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Font;
import java.util.Set;

import apcs.katechon.KatechonGameBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.commands.CommandManager;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.collisions.MattsCollisionEngine;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.logging.Log;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;
import apcs.katechon.utils.Utils;
import apcs.katechon.windowingtoolkit.KWT;
import apcs.katechon.windowingtoolkit.Message;
import apcs.katechon.windowingtoolkit.Window;
import apcs.katechon.windowingtoolkit.WindowImage;
import apcs.shoppingMaul.commands.CountLeopardsCommand;
import apcs.shoppingMaul.commands.GotoCommand;
import apcs.shoppingMaul.commands.RemoveLeopardCommand;
import apcs.shoppingMaul.commands.AddLeopardCommand;
import apcs.shoppingMaul.commands.WhereAmICommand;
import apcs.shoppingMaul.man.Man;
import apcs.shoppingMaul.map.GameMap;

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
		EngineManager.getInstance().addEngine(new MattsCollisionEngine());
		
		engine.getSwingWindow().setBackgroundColor(Color.BLACK);
		
		int speed = 10;

		int width = engine.getSwingWindow().getWidth();
		int height = engine.getSwingWindow().getHeight();
		int xCenterOffset = -75;
		int yCenterOffset = -75;
		
		LeopardPack pack = new LeopardPack(ControlScheme.WSAD, (width / 2) + xCenterOffset, (height / 2) + yCenterOffset, speed, 5, 3);
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(pack);
		
		Board board = new Board(ControlScheme.WSAD, pack, engine.getSwingWindow(), speed);
		board.goTo(200, 300);
		engine.addDrawable(board, 2);
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(board);
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(board);

		GameMap map = null;
		try
		{
			map = new GameMap(ShoppingMaul.class, "map.txt", -1000, -1000);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(200);
		}
		
		map.insertMap(board);
		
		Set<Man> men = map.spawnMen(100, 5);
		
		Log.info("amount of men spawned: " + men.size());
		
		for(Man someGuy : men)
		{
			EngineManager.getInstance().getEngine(ICollidable.class).addItem(someGuy);
			EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(someGuy);
			board.addDrawable(someGuy);
		}
		
		Man thatGuy = Utils.getRandomItem(men);
		
		board.setTarget(thatGuy);
		
		Font font = new Font("Arial", Font.PLAIN, 20);
		Message messageLine1 =  new Message("Your task is to find the man shown below.", 10, 55, font, Color.GREEN);
		Message messageLine2 =  new Message("Find him and destroy him!", 10, 390, font, Color.GREEN);
		
		WindowImage topDownImage = new WindowImage(thatGuy.getTopDownImage(), 75, 175);
		WindowImage deadImage = new WindowImage(thatGuy.getDeadImage(), 225, 125);
		
		Window window = new Window((width / 2) - 225, (height / 2) - 225, 400, 400);
		window.setTitle("Your task");
		window.addDisplayable(messageLine1);
		window.addDisplayable(messageLine2);
		window.addDisplayable(topDownImage);
		window.addDisplayable(deadImage);
		
		KWT.getInstance().addWindow(window);
		window.setVisible(true);
		
		CommandManager.getInstance().registerCommand("{add|spawn} * {leopard|leopards}", new AddLeopardCommand(pack));
		CommandManager.getInstance().registerCommand("remove * {leopard|leopards}", new RemoveLeopardCommand(pack));
		CommandManager.getInstance().registerCommand("count leopards", new CountLeopardsCommand(pack));

		CommandManager.getInstance().registerCommand("goto", new GotoCommand(board));
		CommandManager.getInstance().registerCommand("whereami", new WhereAmICommand(board));
	}

	@Override
	public void onGameEnd()
	{
		
	}
}
