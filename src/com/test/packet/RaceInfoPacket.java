package com.test.packet;
import java.nio.ByteBuffer;
import java.util.List;

import com.test.model.ModelEvent;
import com.test.model.ModelLesson;
import com.test.model.ModelMiddleTimeSetting;
import com.test.model.ModelTargetSetting;



public class RaceInfoPacket implements Packet {

	static {
		PacketConverter.registerClass(RaceInfoPacket.class);
	}

	public ModelLesson lesson;
	public ModelEvent event;
	public List<ModelMiddleTimeSetting> middleTimeSettings;
	public List<ModelTargetSetting> targetSettings;

	@Override
	public void encode(ByteBuffer byteBuffer) {
		PacketConverter.encode(lesson, byteBuffer);
		PacketConverter.encode(event, byteBuffer);
		PacketConverter.encodePacketList(middleTimeSettings, byteBuffer);
		PacketConverter.encodePacketList(targetSettings, byteBuffer);
	}

	@Override
	public void decode(ByteBuffer byteBuffer) {
		try {
			lesson = (ModelLesson) PacketConverter.decode(ModelLesson.class, byteBuffer);
			event = (ModelEvent) PacketConverter.decode(ModelEvent.class, byteBuffer);
			middleTimeSettings = (List<ModelMiddleTimeSetting>) PacketConverter.decodePacketList(ModelMiddleTimeSetting.class, byteBuffer);
			targetSettings = (List<ModelTargetSetting>) PacketConverter.decodePacketList(ModelTargetSetting.class, byteBuffer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("---> RaceInfoPacket\n");
		builder.append(
				   "lesson:" + lesson + "\n"
				 + "event:" + event + "\n"
				 );

		builder.append("middleTimeSettings:\n");
		for (ModelMiddleTimeSetting one : middleTimeSettings) {
			builder.append(" " + one + "\n");
		}
		builder.append("\n");

		builder.append("targetSettings:\n");
		for (ModelTargetSetting one : targetSettings) {
			builder.append(" " + one + "\n");
		}
		builder.append("\n");

		builder.append("<--- RaceInfoPacket\n");

		return builder.toString();
	}
}
