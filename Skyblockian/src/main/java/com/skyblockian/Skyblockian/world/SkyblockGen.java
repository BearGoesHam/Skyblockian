package com.skyblockian.Skyblockian.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class SkyblockGen 
extends ChunkGenerator
{
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<BlockPopulator> getDefaultPopulators(World w)
	{
		return new ArrayList();
	}
	
	public boolean canSpawn(World w, int x, int z)
	{
		return true;
	}
	
	public byte[][] generateBlockSections(World w, Random r, int x, int z, ChunkGenerator.BiomeGrid b)
	{
		return new byte[w.getMaxHeight() / 16][];
	}
}
