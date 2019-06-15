package net.haesleinhuepf.clij.converters.implementations;

import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.converters.AbstractCLIJConverter;
import net.haesleinhuepf.clij.coremem.enums.NativeTypeEnum;
import net.haesleinhuepf.clij.micromanager.NioBuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;


/**
 * ClearCLBufferToNioBufferConverter
 * <p>
 * <p>
 * <p>
 * Author: @haesleinhuepf
 * 6 2019
 */
public class ClearCLBufferToNioBufferConverter  extends AbstractCLIJConverter<ClearCLBuffer, NioBuffer> {

    @Override
    public NioBuffer convert(ClearCLBuffer source) {
        Buffer buffer = null;
        if (source.getNativeType() == NativeTypeEnum.UnsignedByte) {
            buffer = ByteBuffer.allocate((int) (source.getSizeInBytes() / source.getNativeType().getSizeInBytes()));
            source.writeTo(buffer, true);
        } else if (source.getNativeType() == NativeTypeEnum.UnsignedShort) {
            buffer = ShortBuffer.allocate((int) (source.getSizeInBytes() / source.getNativeType().getSizeInBytes()));
            source.writeTo(buffer, true);
        } else if (source.getNativeType() == NativeTypeEnum.Float) {
            buffer = FloatBuffer.allocate((int) (source.getSizeInBytes() / source.getNativeType().getSizeInBytes()));
            source.writeTo(buffer, true);
        } // Todo: other types, exception when type not found

        NioBuffer target = new NioBuffer(buffer, source.getDimensions());

        return target;
    }

    @Override
    public Class<ClearCLBuffer> getSourceType() {
        return ClearCLBuffer.class;
    }

    @Override
    public Class<NioBuffer> getTargetType() {
        return NioBuffer.class;
    }
}