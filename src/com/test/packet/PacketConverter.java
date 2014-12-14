package com.test.packet;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    // utility method //

    public static void encodePacketList(List<? extends Packet> packetList, ByteBuffer byteBuffer) {
    	if (packetList == null || packetList.size() <= 0) {
    		byteBuffer.putInt(0);
    		return;
    	}

    	byteBuffer.putInt(packetList.size());
    	for (Packet one : packetList) {
    		encode(one, byteBuffer);
    	}
    }

    public static List<? extends Packet> decodePacketList(Class<? extends Packet> packetClass,
    		ByteBuffer byteBuffer) throws Exception {
    	int size = byteBuffer.getInt();
    	if (size == 0) {
    		return null;
    	}

        if (packetClass != null) {
        	List<Packet> packetList = new ArrayList<Packet>();
            for (int i = 0; i < size; i++) {
            	packetList.add(decode(packetClass, byteBuffer));
            }

            return packetList;
        }
		return null;
    }

	public static void encode(final Packet packet, ByteBuffer byteBuffer) {
		if (packet == null) {
			byteBuffer.putInt(0);
			return;
		}

    	byteBuffer.putInt(1);
        packet.encode(byteBuffer);
	}

	public static Packet decode(Class<? extends Packet> packetClass, ByteBuffer byteBuffer) throws Exception {
    	if (byteBuffer.getInt() == 0) {
    		return null;
    	}

        if (packetClass != null) {

            final Packet packet = packetClass.newInstance();
            packet.decode(byteBuffer);
 
            return packet;
        }
		return null;
	}
}