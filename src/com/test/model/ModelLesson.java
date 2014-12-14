package com.test.model;

import java.nio.ByteBuffer;

import com.test.packet.ByteBufferUtil;
import com.test.packet.Packet;
import com.test.packet.PacketConverter;

public class ModelLesson implements Packet {

	static {
		PacketConverter.registerClass(ModelLesson.class);
	}

	public int date;
	public String classStr;

	@Override
	public void encode(ByteBuffer byteBuffer) {
		// TODO Auto-generated method stub
		byteBuffer.putInt(date);
		ByteBufferUtil.putString(byteBuffer, classStr);
	}

	@Override
	public void decode(ByteBuffer byteBuffer) {
		// TODO Auto-generated method stub
		this.date = byteBuffer.getInt();
		this.classStr = ByteBufferUtil.getString(byteBuffer);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(
				   "date:" + date + "\n"
				 + "classStr:" + classStr + "\n"
				 );

		return builder.toString();
	}
}
