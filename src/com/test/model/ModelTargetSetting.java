package com.test.model;

import java.nio.ByteBuffer;

import com.test.packet.ByteBufferUtil;
import com.test.packet.Packet;
import com.test.packet.PacketConverter;

public class ModelTargetSetting implements Packet {

	static {
		PacketConverter.registerClass(ModelTargetSetting.class);
	}

	public int value;
	public String studentId;

	@Override
	public void encode(ByteBuffer byteBuffer) {
		// TODO Auto-generated method stub
		byteBuffer.putInt(value);
		ByteBufferUtil.putString(byteBuffer, studentId);
	}

	@Override
	public void decode(ByteBuffer byteBuffer) {
		// TODO Auto-generated method stub
		this.value = byteBuffer.getInt();
		this.studentId = ByteBufferUtil.getString(byteBuffer);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(
				   "value:" + value + ","
				 + "studentId:" + studentId
				 );

		return builder.toString();
	}
}
