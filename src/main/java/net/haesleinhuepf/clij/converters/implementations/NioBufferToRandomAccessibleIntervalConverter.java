package net.haesleinhuepf.clij.converters.implementations;

import ij.ImagePlus;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.converters.AbstractCLIJConverter;
import net.haesleinhuepf.clij.converters.CLIJConverterPlugin;
import net.haesleinhuepf.clij.micromanager.NioBuffer;
import net.imglib2.RandomAccessibleInterval;
import org.scijava.plugin.Plugin;

import java.util.Random;


/**
 * NioBufferToImagePlusConverter
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 6 2019
 */
@Plugin(type = CLIJConverterPlugin.class)
public class NioBufferToRandomAccessibleIntervalConverter extends AbstractCLIJConverter<NioBuffer, RandomAccessibleInterval> {

    @Override
    public RandomAccessibleInterval convert(NioBuffer source) {
        // todo: the following conversion takes twice the time that might be needed. Speedup if necessary
        ClearCLBuffer buffer = clij.convert(source, ClearCLBuffer.class);
        return clij.convert(buffer, RandomAccessibleInterval.class);
    }


    @Override
    public Class<NioBuffer> getSourceType() {
        return NioBuffer.class;
    }

    @Override
    public Class<RandomAccessibleInterval> getTargetType() {
        return RandomAccessibleInterval.class;
    }
}