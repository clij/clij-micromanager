package net.haesleinhuepf.clij.converters.implementations;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.converters.AbstractCLIJConverter;
import net.haesleinhuepf.clij.converters.CLIJConverterPlugin;
import net.haesleinhuepf.clij.micromanager.NioBuffer;
import net.imglib2.RandomAccessibleInterval;
import org.scijava.plugin.Plugin;


/**
 * ClearCLImageToNioBufferConverter
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 6 2019
 */
@Plugin(type = CLIJConverterPlugin.class)
public class ClearCLImageToNioBufferConverter extends AbstractCLIJConverter<ClearCLImage, NioBuffer> {

    @Override
    public NioBuffer convert(ClearCLImage source) {
        // todo: the following conversion takes twice the time that might be needed. Speedup if necessary
        ClearCLBuffer buffer = clij.convert(source, ClearCLBuffer.class);
        return clij.convert(buffer, NioBuffer.class);
    }


    @Override
    public Class<ClearCLImage> getSourceType() {
        return ClearCLImage.class;
    }

    @Override
    public Class<NioBuffer> getTargetType() {
        return NioBuffer.class;
    }
}