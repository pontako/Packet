import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PacketConverter.registerClass(SimplePacket.class);

		List<Integer> integerList = new ArrayList<>();
		integerList.add(1);
		integerList.add(2);
		integerList.add(3);

		List<String> stringList = new ArrayList<>();
		stringList.add("abc");
		stringList.add("qwer");
		stringList.add("zxcv");

		SimplePacket sendPacket = new SimplePacket(
				10,
				0.2f,
				"foo123bar",
				integerList,
				stringList
				);
		
		PacketConverter converter = new PacketConverter();
		ByteBuffer buffer = converter.serialize(sendPacket, 256);
		byte[] sendBytes = Arrays.copyOfRange(buffer.array(), 0, buffer.remaining());
		dumpByteArray(sendBytes);
//		System.out.println("position:" + buffer.position());
//		System.out.println("remain:" + buffer.remaining());

		try {
			ByteBuffer fromByteBuffer = ByteBuffer.allocate(sendBytes.length);
			fromByteBuffer.put(sendBytes);
			fromByteBuffer.flip();
			dumpByteArray(fromByteBuffer.array());
//			System.out.println("position:" + fromByteBuffer.position());
//			System.out.println("remain:" + fromByteBuffer.remaining());
			SimplePacket receivedPacket = (SimplePacket) converter.deserialize(fromByteBuffer);
			System.out.println(receivedPacket.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void serialize(final Packet packet, int capacity) {
		
	}

	public static void deserialize() {
		
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
