import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ByteBufferUtil {

	public static boolean putString(ByteBuffer byteBuffer, String string,
			String charset) {
		boolean result = true;
		try {
			byte[] bytes = string.getBytes(charset);
			byteBuffer.putInt(bytes.length);
			byteBuffer.put(bytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public static String getString(ByteBuffer byteBuffer, String charset) {
		String result = null;
		int length = byteBuffer.getInt();
		byte[] bytes = new byte[length];
		byteBuffer.get(bytes);
		try {
			result = new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean putIntegerList(ByteBuffer byteBuffer, List<Integer> integerList) {
		if (byteBuffer == null || integerList == null) {
			return false;
		}

		int size = integerList.size();
        byteBuffer.putInt(size);
        for (Integer i : integerList) {
        	byteBuffer.putInt(i);
        }

		return true;
	}

	public static List<Integer> getIntegerList(ByteBuffer byteBuffer) {
		List<Integer> integerList = new ArrayList<Integer>();
        int size = byteBuffer.getInt();
        for (int i = 0; i < size; i++) {
        	integerList.add(byteBuffer.getInt());
        }
        return integerList;
	}

	public static boolean putStringList(ByteBuffer byteBuffer, List<String> stringList,
			String charset) {
		if (byteBuffer == null || stringList == null) {
			return false;
		}

		boolean result = true;

		int size = stringList.size();
        byteBuffer.putInt(size);
        for (String s : stringList) {
        	result &= putString(byteBuffer, s, charset);
        }

		return result;
	}

	public static List<String> getStringList(ByteBuffer byteBuffer, String charset) {
		List<String> stringList = new ArrayList<String>();
        int size = byteBuffer.getInt();
        for (int i = 0; i < size; i++) {
        	stringList.add(getString(byteBuffer, charset));
        }
        return stringList;
	}
}
