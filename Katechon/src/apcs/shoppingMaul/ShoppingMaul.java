package apcs.shoppingMaul;

import java.awt.Color;
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
import apcs.shoppingMaul.commands.CountLeopardsCommand;
import apcs.shoppingMaul.commands.GotoCommand;
import apcs.shoppingMaul.commands.RemoveLeopardCommand;
import apcs.shoppingMaul.commands.AddLeopardCommand;
import apcs.shoppingMaul.commands.WhereAmICommand;
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
		

		
		Man man = ManFactory.makeMan(-100, -100, 5);
		
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(man);
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(man);
		
		LeopardPack pack = new LeopardPack(ControlScheme.WSAD, (width / 2) + xCenterOffset, (height / 2) + yCenterOffset, speed, 5, 3);
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(pack);
		
		Board board = new Board(ControlScheme.WSAD, pack, engine.getSwingWindow(), speed);
		board.addDrawable(man);
		board.goTo(200, 300);
		engine.addDrawable(board, 2);
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(board);
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(board);
	
		
//		FileInputStream mapStream = getClass().getResourceAsStream("map.txt");

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
		
		System.out.println(map);
		
//		FloorTile tile = new FloorTile(600, 600);
//		board.addDrawable(tile);
//
//		Pillar pillar = new Pillar(500, 500, 25, 25, "Pillar 1");
//		EngineManager.getInstance().getEngine(ICollidable.class).addItem(pillar);
//		board.addDrawable(pillar);
//		
//		Pillar pillar2 = new Pillar(100, 700, 25, 25, "Pillar 2");
//		EngineManager.getInstance().getEngine(ICollidable.class).addItem(pillar2);
//		board.addDrawable(pillar2);
//		
//		Pillar pillar3 = new Pillar(50, 100, 25, 25, "Pillar 3");
//		EngineManager.getInstance().getEngine(ICollidable.class).addItem(pillar3);
//		board.addDrawable(pillar3);
		
//		board.setTarget(pillar3);
		
//		Wall wall = new Wall(-100, -100);
//		EngineManager.getInstance().getEngine(ICollidable.class).addItem(wall);
//		board.addDrawable(wall);
		
		

//		board.addDrawable(new Pillar(-500, -500, 25, 25));
		
//		board.addDrawable(new SimpleBoardCollidable(200, 400, 30, 30, 10, false));
//		
//		int amountOfLeopards = 5;
//		Board board = new Board(ControlScheme.WSAD, speed, amountOfLeopards, width, height);
//		
//		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(board);
//		engine.addDrawable(board, 2);
//		
//		
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
