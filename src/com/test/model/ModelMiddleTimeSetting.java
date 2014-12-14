package com.test.model;

import java.nio.ByteBuffer;

import com.test.packet.ByteBufferUtil;
import com.test.packet.Packet;
import com.test.packet.PacketConverter;

public class ModelMiddleTimeSetting implements Packet {

	static {
		PacketConverter.registerClass(ModelMiddleTimeSetting.class);
	}

	public int eventId;
	public int location;

	@Override
	public void encode(ByteBuffer byteBuffer) {
		// TODO Auto-generated method stub
		byteBuffer.putInt(eventId);
		byteBuffer.putInt(location);
	}

	@Override
	public void decode(ByteBuffer byteBuffer) {
		// TODO Auto-generated method stub
		this.eventId = byteBuffer.getInt();
		this.location = byteBuffer.getInt();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(
				   "eventId:" + eventId + ","
				 + "location:" + location
				 );

		return builder.toString();
	}
}
