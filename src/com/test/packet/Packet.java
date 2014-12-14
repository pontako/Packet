package com.test.packet;
import java.nio.ByteBuffer;
 
public interface Packet {
 
    public void encode(final ByteBuffer byteBuffer);
 
    public void decode(final ByteBuffer byteBuffer);

}