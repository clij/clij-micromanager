package net.haesleinhuepf.clij.converters.implementations;

import ij.ImagePlus;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.clearcl.ClearCLImage;
import net.haesleinhuepf.clij.converters.AbstractCLIJConverter;
import net.haesleinhuepf.clij.converters.CLIJConverterPlugin;
import net.haesleinhuepf.clij.coremem.enums.NativeTypeEnum;
import net.haesleinhuepf.clij.micromanager.NioBuffer;
import org.scijava.plugin.Plugin;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;


/**
 * ImagePlusToNioBufferConverter
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 6 2019
 */
@Plugin(type = CLIJConverterPlugin.class)
public class ImagePlusToNioBufferConverter extends AbstractCLIJConverter<ImagePlus, NioBuffer> {

    @Override
    public NioBuffer convert(ImagePlus source) {
        // todo: the following conversion takes twice the time that might be necessary. Speedup if necessary
        ClearCLBuffer buffer = clij.convert(source, ClearCLBuffer.class);
        return clij.convert(buffer, NioBuffer.class);
    }


    @Override
    public Class<ImagePlus> getSourceType() {
        return ImagePlus.class;
    }

    @Override
    public Class<NioBuffer> getTargetType() {
        return NioBuffer.class;
    }
}