package test.minecraft;

import minecraft.CompressionScheme;
import minecraft.Map;
import nbt.Tag;
import test.TestingUtils;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MapTest
{
	public void printLastMapId() throws Throwable
	{
		Tag.Compound idcounts;
		try(FileInputStream fis = new FileInputStream(TestingUtils.getInputFile("idcounts.dat")))
		{
			idcounts = (Tag.Compound)Tag.deserialize(CompressionScheme.None.getInputStream(fis));
		}
		System.out.println("Last created map number: "+((Tag.Short)idcounts.find(Tag.Type.SHORT, "map")).v);
	}
	public void editMapImage() throws Throwable
	{
		final Map map;
		try(FileInputStream fis = new FileInputStream(TestingUtils.getInputFile("map.dat")))
		{
			map = new Map((Tag.Compound)Tag.deserialize(CompressionScheme.GZip.getInputStream(fis)));
		}

		BufferedImage mapimage = map.Image();
		ImageIO.write(mapimage, "png", TestingUtils.getOutputFile("map.png"));

		Graphics2D g = mapimage.createGraphics();
		g.setColor(Color.red);
		g.drawString("My Minecraft Map", 16, 16);

		try(FileOutputStream fos = new FileOutputStream(TestingUtils.getOutputFile("map.dat")))
		{
			map.ToNBT("").serialize(CompressionScheme.GZip.getOutputStream(fos));
		}
	}
}
