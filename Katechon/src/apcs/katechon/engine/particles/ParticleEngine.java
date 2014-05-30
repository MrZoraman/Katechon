package apcs.katechon.engine.particles;

import java.util.Set;

import apcs.katechon.engine.EngineModuleBase;
import apcs.katechon.utils.AI;

public class ParticleEngine extends EngineModuleBase<ParticleBase>
{
	private static final int SPEED = 15;
	
	private final AI ai;
	
	public ParticleEngine()
	{
		super(ParticleBase.class);
		ai = new AI(SPEED);
	}

	@Override
	protected void process(Set<ParticleBase> items)
	{
		
	}

	
}
