package net.haesleinhuepf.clij.micromanager;

import ij.ImagePlus;
import ij.gui.NewImage;
import net.haesleinhuepf.clij.CLIJ;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.test.TestUtilities;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

public class CLIJMMConverterTests {
    @Test
    public void simpleCLBufferTest() {
        CLIJ clij = CLIJ.getInstance();
        ImagePlus imp = NewImage.createShortImage("test", 10, 10, 3, NewImage.FILL_RANDOM);

        NioBuffer nioBuffer1 = clij.convert(imp, NioBuffer.class);

        ClearCLBuffer clBuffer1 = clij.convert(nioBuffer1, ClearCLBuffer.class);
        ClearCLBuffer clBuffer2 = clij.create(clBuffer1);

        clij.op().copy(clBuffer1, clBuffer2);

        NioBuffer nioBuffer2 = clij.convert(clBuffer2, NioBuffer.class);

        ImagePlus result = clij.convert(nioBuffer2, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(imp, result));
    }

    @Test
    public void simpleCLImageTest() {
        CLIJ clij = CLIJ.getInstance();
        ImagePlus imp = NewImage.createShortImage("test", 10, 10, 3, NewImage.FILL_RANDOM);

        NioBuffer nioBuffer1 = clij.convert(imp, NioBuffer.class);

        ClearCLImage clBuffer1 = clij.convert(nioBuffer1, ClearCLImage.class);
        ClearCLImage clBuffer2 = clij.create(clBuffer1);

        clij.op().copy(clBuffer1, clBuffer2);

        NioBuffer nioBuffer2 = clij.convert(clBuffer2, NioBuffer.class);

        ImagePlus result = clij.convert(nioBuffer2, ImagePlus.class);

        assertTrue(TestUtilities.compareImages(imp, result));

    }
}