package org.kirhgoff.fractals.test;
import java.awt.Dimension;
import java.awt.Point;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.kirhgoff.fractals.color.ColorMapper;
import org.kirhgoff.fractals.controllers.PaletteScroller;
import org.kirhgoff.fractals.controllers.Zoomer;
import org.kirhgoff.fractals.math.Complex;
import org.kirhgoff.fractals.math.Conversion;
import org.kirhgoff.fractals.math.Coord;
import org.kirhgoff.fractals.math.Functor;
import org.kirhgoff.fractals.math.MandelbrotFunctor;

public class ComplexAreaTest {
	@Ignore
	@Test
	public void testSimpleFunctor() throws Exception {
		Functor functor = new MandelbrotFunctor ();
		Assert.assertEquals (new Complex (-1, 2), functor.getNext(new Complex (1, 1)));
	}
	@Test
	public void testInfinity() throws Exception {
		Conversion conversion = new Conversion (new Dimension (400, 800));
		conversion.setCoord(new Coord (-6.0, 2.0, -2.0, 2.0));
		Assert.assertEquals(-6, conversion.convertX(0));
		Assert.assertEquals(2, conversion.convertY(0));
		Assert.assertEquals(2, conversion.convertX(400));
		Assert.assertEquals(-2, conversion.convertY(800));
		Assert.assertEquals(-2, conversion.convertX(200));
		Assert.assertEquals(0, conversion.convertY(400));

		//
		conversion.setNewPoints(new Point (100, 100), new Point (300, 600));
		Assert.assertEquals(-4, conversion.convertX(0));
		Assert.assertEquals(1, conversion.convertY(0));
		Assert.assertEquals(0, conversion.convertX(400));
		Assert.assertEquals(-1, conversion.convertY(800));
		Assert.assertEquals(-2, conversion.convertX(200));
		Assert.assertEquals(0, conversion.convertY(400));
	}

	@Ignore
	@Test
	public void testColorMapper() throws Exception {
		ColorMapper colorMapper = new ColorMapper ();
		Assert.assertEquals(169, colorMapper.red (0));
		Assert.assertEquals(78, colorMapper.red (255));

		Assert.assertEquals(100, colorMapper.green (0));
		Assert.assertEquals(200, colorMapper.green (255));
		
		Assert.assertEquals(20, colorMapper.blue (0));
		Assert.assertEquals(40, colorMapper.blue (255));

	}
	
	@Test
	public void testRotate() throws Exception {
		byte [] array = new byte [] {1, 2, 3, 4};
		Assert.assertArrayEquals(new byte [] {4, 1, 2, 3}, PaletteScroller.rotateForward(array));
		Assert.assertArrayEquals(new byte [] {3, 4, 1, 2}, PaletteScroller.rotateForward(array));
		Assert.assertArrayEquals(new byte [] {2, 3, 4, 1}, PaletteScroller.rotateForward(array));
		Assert.assertArrayEquals(new byte [] {1, 2, 3, 4}, PaletteScroller.rotateForward(array));
		
		Assert.assertArrayEquals(new byte [] {2, 3, 4, 1}, PaletteScroller.rotateBackward(array));
		Assert.assertArrayEquals(new byte [] {3, 4, 1, 2}, PaletteScroller.rotateBackward(array));
		Assert.assertArrayEquals(new byte [] {4, 1, 2, 3}, PaletteScroller.rotateBackward(array));
		Assert.assertArrayEquals(new byte [] {1, 2, 3, 4}, PaletteScroller.rotateBackward(array));		
	}
	
	@Test
	public void testAspectRatio() throws Exception {
		Assert.assertEquals (new Point (200, 300), Zoomer.adjustToAspectRatio(new Dimension (200, 400), new Point (100, 100), new Point (200, 400)));
		Assert.assertEquals (new Point (150, 100), Zoomer.adjustToAspectRatio(new Dimension (200, 100), new Point (50, 50), new Point (200, 100)));

	}
}
