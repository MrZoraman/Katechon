package apcs.shoppingMaul;

import java.awt.Color;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import apcs.katechon.KatechonGameBase;
import apcs.katechon.KatechonEngine;
import apcs.katechon.commands.CommandManager;
import apcs.katechon.engine.EngineManager;
import apcs.katechon.engine.collisions.ICollidable;
import apcs.katechon.engine.collisions.SimpleCollisionEngine;
import apcs.katechon.logging.Log;
import apcs.katechon.utils.ConfigKey;
import apcs.katechon.utils.IConfig;
import apcs.katechon.utils.MappedConfig;
import apcs.shoppingMaul.commands.AddPillarCommand;
import apcs.shoppingMaul.commands.SpawnLeopardCommand;
import apcs.shoppingMaul.commands.SpawnLeopardPackCommand;

public class ShoppingMaul extends KatechonGameBase
{
	public static IConfig config;
	
	private static KatechonEngine mainEngine;
	
	private static SnowLeopard snowLeopard;
	private static LeopardPack mainPack;
	
	private static Set<LeopardPack> packs;
	
	public static void main(String[] args)
	{
		config = new MappedConfig();
		config.setString(ConfigKey.TITLE, "Shopping Maul");
		
		//see this line of code looks pretty clean to me
		mainEngine = new KatechonEngine(ShoppingMaul.class, config);
		
		mainEngine.start();
	}

	@Override
	public void init()
	{
		Log.info("finding us some files");
//		Object obj = ShoppingMaul.class.getResource("/assets/Pillar.png");
		ClassLoader cl = this.getClass().getClassLoader();
		Object obj = cl.getResourceAsStream("test.txt");
		Log.info("null: " + (obj == null));
		
		mainEngine = KatechonEngine.getInstance();
		
		mainEngine.getSwingWindow().setBackgroundColor(Color.WHITE);
		
		packs = new HashSet<LeopardPack>();
		
		snowLeopard = new SnowLeopard(100, 100, 10, 28, 8, true, true);
		
		mainPack = new LeopardPack(100, 100, true, snowLeopard);
		
		packs.add(mainPack);
		
		mainEngine.addDrawable(mainPack, 1);
		mainEngine.scheduleTask(mainPack);
		EngineManager.getInstance().addEngine(new SimpleCollisionEngine());
		
		EngineManager.getInstance().getEngine(ICollidable.class).addItem(mainPack);
		
		CommandManager.getInstance().registerCommand("spawnleopard", new SpawnLeopardCommand());
		CommandManager.getInstance().registerCommand("spawnpack", new SpawnLeopardPackCommand());
		
		CommandManager.getInstance().registerCommand("addpillar", new AddPillarCommand());
	}

	@Override
	public void onGameEnd()
	{
		
	}
	
	public static SnowLeopard getMainLeopard()
	{
		return snowLeopard; 
	}
	
	public static void addPack(LeopardPack pack)
	{
		packs.add(pack);
	}
	
	public static Set<LeopardPack> getPacks()
	{
		return packs;
	}
}
