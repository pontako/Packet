import java.beans.DesignMode;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.test.model.ModelEvent;
import com.test.model.ModelLesson;
import com.test.model.ModelMiddleTimeSetting;
import com.test.model.ModelTargetSetting;
import com.test.packet.PacketConverter;
import com.test.packet.RaceInfoPacket;
import com.test.packet.SimplePacket;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] sendBytes = serializeRaceInfoPacket();
		deserializeRaceInfoPacket(sendBytes);
	}

	public static byte[] serializeRaceInfoPacket() {
		RaceInfoPacket raceInfoPacket = new RaceInfoPacket();

		ModelLesson lesson = new ModelLesson();
		lesson.date = 20141214;
		lesson.classStr = "A class";

		ModelEvent event = new ModelEvent();
		event.type = 1;
		event.data1 = "100m";

		List<ModelMiddleTimeSetting> middleTimeSettings  = new ArrayList<>();
		for ( int i = 0; i < 3; i++) {
			ModelMiddleTimeSetting one = new ModelMiddleTimeSetting();
			one.eventId = i;
			one.location = i * 20;
			middleTimeSettings.add(one);
		}
		
		List<ModelTargetSetting> targetSettings  = new ArrayList<>();
		for ( int i = 0; i < 3; i++) {
			ModelTargetSetting one = new ModelTargetSetting();
			one.studentId = "12345" + i;
			one.value = i * 100;
			targetSettings.add(one);
		}

		raceInfoPacket.lesson = lesson;
		raceInfoPacket.event = event;
		raceInfoPacket.middleTimeSettings = middleTimeSettings;
		raceInfoPacket.targetSettings = targetSettings;

		PacketConverter converter = new PacketConverter();
		ByteBuffer buffer = converter.serialize(raceInfoPacket, 2048);
		byte[] sendBytes = Arrays.copyOfRange(buffer.array(), 0, buffer.remaining());
		dumpByteArray(sendBytes);

		return sendBytes;
	}

	public static void deserializeRaceInfoPacket(byte[] sendBytes) {
		try {
			PacketConverter converter = new PacketConverter();
			ByteBuffer fromByteBuffer = ByteBuffer.allocate(sendBytes.length);
			fromByteBuffer.put(sendBytes);
			fromByteBuffer.flip();
			dumpByteArray(fromByteBuffer.array());
			// System.out.println("position:" + fromByteBuffer.position());
			// System.out.println("remain:" + fromByteBuffer.remaining());
			RaceInfoPacket receivedPacket = (RaceInfoPacket) converter
					.deserialize(fromByteBuffer);
			System.out.println(receivedPacket.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void dumpByteArray(byte[] bytes) {
		System.out.println("---> start dumpByteArray");
		for (byte b : bytes) {
			System.out.print(String.format("%02X", b));
			System.out.print(",");
		}
		System.out.print("\n");
		System.out.println("<--- finish dumpByteArray");
	}
}
