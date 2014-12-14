package com.test.model;

import java.nio.ByteBuffer;

import com.test.packet.ByteBufferUtil;
import com.test.packet.Packet;
import com.test.packet.PacketConverter;

public class ModelEvent implements Packet {

	static {
		PacketConverter.registerClass(ModelEvent.class);
	}

	public int type;
	public String data1;

	@Override
	public void encode(ByteBuffer byteBuffer) {
		// TODO Auto-generated method stub
		byteBuffer.putInt(type);
		ByteBufferUtil.putString(byteBuffer, data1);
	}

	@Override
	public void decode(ByteBuffer byteBuffer) {
		// TODO Auto-generated method stub
		this.type = byteBuffer.getInt();
		this.data1 = ByteBufferUtil.getString(byteBuffer);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(
				   "type:" + type + "\n"
				 + "data1:" + data1 + "\n"
				 );

		return builder.toString();
	}
}
