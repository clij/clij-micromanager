package net.haesleinhuepf.clij.converters.implementations;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.coremem.enums.NativeTypeEnum;
import net.haesleinhuepf.clij.converters.AbstractCLIJConverter;
import net.haesleinhuepf.clij.converters.CLIJConverterPlugin;
import net.haesleinhuepf.clij.micromanager.NioBuffer;
import org.scijava.plugin.Plugin;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;


/**
 * NioBufferToClearCLBufferConverter
 * <p>
 * <p>
 * <p>
 * Author: @nicost
 * 6 2019
 */
@Plugin(type = CLIJConverterPlugin.class)
public class NioBufferToClearCLBufferConverter extends AbstractCLIJConverter<NioBuffer, ClearCLBuffer> {

    @Override
    public ClearCLBuffer convert(NioBuffer source) {
        ClearCLBuffer target;
        NativeTypeEnum type = null;
        if (source.getBuffer() instanceof ByteBuffer) {
            type = NativeTypeEnum.UnsignedByte;
        } else if (source.getBuffer() instanceof ShortBuffer) {
            type = NativeTypeEnum.UnsignedShort;
        } else if (source.getBuffer() instanceof FloatBuffer) {
            type = NativeTypeEnum.Float;
        } // Todo: other types, exception when type not found
        target = clij.createCLBuffer(source.getDimensions(), type);
        target.readFrom(source.getBuffer(), true);

        return target;
    }


    @Override
    public Class<NioBuffer> getSourceType() {
        return NioBuffer.class;
    }

    @Override
    public Class<ClearCLBuffer> getTargetType() {
        return ClearCLBuffer.class;
    }
}