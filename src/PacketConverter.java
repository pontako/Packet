import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
 
public class PacketConverter {
 
    private static Map<Integer, Class<? extends Packet>> packetClasses = new HashMap<Integer, Class<? extends Packet>>();
 
    public synchronized static void registerClass(final Class<? extends Packet> packetClass) {
        final int id = packetClasses.size() + 1;
        packetClasses.put(id, packetClass);
    }
 
    private int getIdForPacketInstance(final Packet packet) {
        for (final Map.Entry<Integer, Class<? extends Packet>> entry : packetClasses.entrySet()) {
            if (entry.getValue().getName().equals(packet.getClass().getName())) {
                return entry.getKey();
            }
        }
 
        return -1;
    }
 
    public ByteBuffer serialize(final Packet packet, int capacity) {
        final int id = getIdForPacketInstance(packet);
 
        if (id != -1) {
        	final ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
            byteBuffer.putInt(getIdForPacketInstance(packet));
            packet.encode(byteBuffer);
            byteBuffer.flip();
 
            return byteBuffer;
        }
 
        return null;
    }
 
    public Packet deserialize(final ByteBuffer byteBuffer) throws Exception {
        final int packetId = byteBuffer.getInt();
        final Class<? extends Packet> packetClass = packetClasses.get(packetId);
 
        if (packetClass != null) {
            final Packet packet = packetClass.newInstance();
            packet.decode(byteBuffer);
 
            return packet;
        }
 
        return null;
    }
}