package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

import apcs.katechon.KatechonGameBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.commands.CommandManager;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.EngineModuleBase;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.collisions.MattsCollisionEngine;
import apcs.katechon.engine.scheduler.ISchedulerTask;
import apcs.katechon.logging.Log;
import apcs.katechon.rendering.IDrawable;
import apcs.katechon.sounds.IPlayer;
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
	
	private IPlayer player;

	@Override
	public void init(final KatechonEngine engine)
	{
		Log.setDebugging(true);
		EngineManager.getInstance().addEngine(new MattsCollisionEngine());
		
		engine.getSwingWindow().setBackgroundColor(Color.BLACK);
		
		start(engine);
		
		
		
//		try
//		{
//			player = new RandomPlayer(getClass().getResourceAsStream("/apcs/shoppingMaul/assets/main.wav"));
//			((RandomPlayer)player).addClip(getClass().getResourceAsStream("/apcs/shoppingMaul/assets/Airport Lounge.wav"));
//			((RandomPlayer)player).addClip(getClass().getResourceAsStream("/apcs/shoppingMaul/assets/Groove Grove.wav"));
//			player.loop();
//		}
//		catch (Exception e)
//		{
//			Log.exception(e);
//		}
//		
//		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(player);
		
		
		
	}
	
	public void start(KatechonEngine engine)
	{
		int speed = 10;

		int width = engine.getSwingWindow().getWidth();
		int height = engine.getSwingWindow().getHeight();
		int xCenterOffset = -75;
		int yCenterOffset = -75;
		
		LeopardPack pack = new LeopardPack(ControlScheme.WSAD, (width / 2) + xCenterOffset, (height / 2) + yCenterOffset, speed, 5, 3);
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(pack);
		
		Board board = new Board(this, ControlScheme.WSAD, pack, engine.getSwingWindow(), speed);
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
		
		
		long before = System.nanoTime();
		EngineModuleBase<ICollidable> collidableEngine = EngineManager.getInstance().getEngine(ICollidable.class);
		EngineModuleBase<ISchedulerTask> schedulerEngine = EngineManager.getInstance().getEngine(ISchedulerTask.class);
		for(Man someGuy : men)
		{
			collidableEngine.addItem(someGuy);
			schedulerEngine.addItem(someGuy);
			board.addDrawable(someGuy);
		}
		System.out.println("spawned men in " + (System.nanoTime() - before) + " nanoseconds.");
		
		Man thatGuy = Utils.getRandomItem(men);
		
		thatGuy.setIsTarget(true);
		
		board.setTarget(thatGuy);
		
		final BufferedImage topDownImage = thatGuy.getTopDownImage();
		final BufferedImage deadImage = thatGuy.getDeadImage();
		
		final Font font = new Font("Arial", Font.PLAIN, 20);
		Message messageLine1 = new Message("Your task is to find the man shown below.", 10, 55, font, Color.GREEN);
		Message messageLine2 = new Message("Find him and destroy him!", 10, 390, font, Color.GREEN);
		
		WindowImage w_topDownImage = new WindowImage(topDownImage, 75, 175);
		WindowImage w_deadImage = new WindowImage(deadImage, 225, 125);
		
		Window window = new Window((width / 2) - 225, (height / 2) - 225, 400, 400);
		window.setTitle("Your task");
		window.addDisplayable(messageLine1);
		window.addDisplayable(messageLine2);
		window.addDisplayable(w_topDownImage);
		window.addDisplayable(w_deadImage);
		
		KWT.getInstance().addWindow(window);
		window.setVisible(true);
		
		IDrawable target = new IDrawable()
		{
			@Override
			public boolean isFinished()
			{
				return false;
			}

			@Override
			public void draw(Graphics g)
			{
				Color c = new Color(0, 0, 0, 150);
				g.setColor(c);
				g.fillRect(5, 3, 100, 100);
				
				g.setFont(font);
				g.setColor(Color.GREEN);
				g.drawString("Target:", 10, 20);
				g.drawImage(topDownImage, 10, 30, null);
			}
		};
		
		engine.addDrawable(target, 3);
		
		CommandManager.getInstance().registerCommand("{add|spawn} * {leopard|leopards}", new AddLeopardCommand(pack));
		CommandManager.getInstance().registerCommand("remove * {leopard|leopards}", new RemoveLeopardCommand(pack));
		CommandManager.getInstance().registerCommand("count leopards", new CountLeopardsCommand(pack));

		CommandManager.getInstance().registerCommand("goto", new GotoCommand(board));
		CommandManager.getInstance().registerCommand("whereami", new WhereAmICommand(board));
	}

	@Override
	public void onGameEnd()
	{
		if(player != null)
		{
			try
			{
				player.close();
			}
			catch (IOException e)
			{
				Log.exception(e);
			}
		}
	}
}
