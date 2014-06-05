package apcs.shoppingMaul;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
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
import apcs.katechon.sounds.RandomPlayer;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;
import apcs.katechon.utils.Utils;
import apcs.katechon.windowingtoolkit.Button;
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
import apcs.shoppingMaul.periodics.TimeScore;

public class ShoppingMaul extends KatechonGameBase
{
	public static void main(String[] args)
	{
		IConfig config = new MappedConfig();
		config.setString(ConfigKey.TITLE, "Shopping Maul");
		config.setInt(ConfigKey.AMOUNT_OF_LAYERS, 6);
		
		new KatechonEngine(ShoppingMaul.class, config).start();
	}
	
	public ShoppingMaul()
	{
		instance = this;
	}
	
	private static ShoppingMaul instance;
	
	private IPlayer player;
	
	private GameMap map;
	
	private Board board;
	
	private Set<Man> men;
	
	private KatechonEngine engine;
	
	private IDrawable target;
	
	private Window window;

	@Override
	public void init(final KatechonEngine engine)
	{
		this.engine = engine;
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
		
		board = new Board(ControlScheme.WSAD, pack, engine.getSwingWindow(), speed, this);
		engine.addDrawable(board, 2);
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(board);
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(board);

		map = null;
		try
		{
			map = new GameMap(ShoppingMaul.class, "smallMap.txt", -1000, -1000);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(200);
		}
		
		map.insertMap(board);
		
		men = new HashSet<Man>();
		
		window = new Window((width / 2) - 225, (height / 2) - 225, 400, 400);
		replay();
		
		try
		{
			player = new RandomPlayer(getClass().getResourceAsStream("/apcs/shoppingMaul/assets/main.wav"));
			((RandomPlayer)player).addClip(getClass().getResourceAsStream("/apcs/shoppingMaul/assets/Airport Lounge.wav"));
			((RandomPlayer)player).addClip(getClass().getResourceAsStream("/apcs/shoppingMaul/assets/Groove Grove.wav"));
			player.loop();
		}
		catch (Exception e)
		{
			Log.exception(e);
		}
		
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(player);
		
		
		CommandManager.getInstance().registerCommand("{add|spawn} * {leopard|leopards}", new AddLeopardCommand(pack));
		CommandManager.getInstance().registerCommand("remove * {leopard|leopards}", new RemoveLeopardCommand(pack));
		CommandManager.getInstance().registerCommand("count leopards", new CountLeopardsCommand(pack));

		CommandManager.getInstance().registerCommand("goto", new GotoCommand(board));
		CommandManager.getInstance().registerCommand("whereami", new WhereAmICommand(board));
		
		EngineManager.getInstance().getEngine(ISchedulerTask.class).addItem(timeScore);
		timeScore.start();
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
	
	private void replay()
	{	
		//Set things to finished so they dispose before being recreated.
		window.setFinished(true);
//		target.setFinished(true);
		
		this.board.goTo(200, 300);
		
		for(Man m : men)
		{
			m.dispose();
		}
		
		men = map.spawnMen(100, 5);
		
		Log.info("amount of men spawned: " + men.size());
		
		
		EngineModuleBase<ICollidable> collidableEngine = EngineManager.getInstance().getEngine(ICollidable.class);
		EngineModuleBase<ISchedulerTask> schedulerEngine = EngineManager.getInstance().getEngine(ISchedulerTask.class);
		for(Man someGuy : men)
		{
			collidableEngine.addItem(someGuy);
			schedulerEngine.addItem(someGuy);
			board.addDrawable(someGuy);
		}
		
		Man thatGuy = Utils.getRandomItem(men);
		
		thatGuy.setIsTarget(true);
		
		board.setTarget(thatGuy);
		
		final BufferedImage topImage = thatGuy.getTopDownImage();
		final BufferedImage deadImage = thatGuy.getDeadImage();
		
		final Font font = new Font("Arial", Font.PLAIN, 20);
		Message messageLine1 =  new Message("Your task is to find the man shown below.", 10, 55, font, Color.GREEN);
		Message messageLine2 =  new Message("Find him and destroy him!", 10, 390, font, Color.GREEN);
		
		WindowImage w_topDownImage = new WindowImage(topImage, 75, 175);
		WindowImage w_deadImage = new WindowImage(deadImage, 225, 125);
		
		window.setFinished(false);
//		window = new Window((KatechonEngine.getInstance().getSwingWindow().getWidth() / 2) - 225, (KatechonEngine.getInstance().getSwingWindow().getWidth() / 2) - 225, 400, 400);
		window.addDisplayable(messageLine1);
		window.addDisplayable(messageLine2);
		window.addDisplayable(w_topDownImage);
		window.addDisplayable(w_deadImage);

		window.setVisible(true);
		KWT.getInstance().addWindow(window);
		
		if (target != null)
		{
			target.setFinished(true);
		}
		
		target = new IDrawable()
		{
			private boolean isFinished = false;
			@Override
			public boolean isFinished()
			{
				return isFinished;
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
				g.drawImage(topImage, 10, 30, null);
			}

			@Override
			public void setFinished(boolean finished)
			{
				isFinished = finished;
			}
		};
		
		engine.addDrawable(target, 3);
		
		timeScore.reset();
		timeScore.start();
	}
	
	private FinishedMessage message = new FinishedMessage();
	private TimeScore timeScore = new TimeScore();
	private GreyOut greyOut = new GreyOut();
	
	public void showFinishedMessage()
	{
		timeScore.stop();
		greyOut.setFinished(false);
		message.setMessage("Your time: " + timeScore + " seconds.");
		KatechonEngine.getInstance().addDrawable(greyOut, 4);
		KatechonEngine.getInstance().addDrawable(message, 5);
		message.show();
		final Button playAgain = new PlayAgainButton(530, 350, 50, 50, this);
		
		playAgain.setMouseOverColor(Color.CYAN);
		playAgain.setPassiveColor(Color.YELLOW);
		playAgain.setMouseHeldColor(new Color(18, 75, 90));
	}
	
	public void hideFinishedMessage()
	{
		greyOut.setFinished(true);
		message.hide();
		instance.replay();
	}
	
	public void timePenalty(double seconds)
	{
		timeScore.timePenalty(seconds);
	}
}
