import java.nio.ByteBuffer;
import java.util.List;


public class SimplePacket implements Packet {
 
    private int someInteger;
 
    private float someFloat;

    private String someString;

    private List<Integer> integerList;

    private List<String> stringList;
 
    private static final String CHARSET = "utf-8";
 
    @Override
    public void encode(final ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.someInteger);
        byteBuffer.putFloat(someFloat);
        ByteBufferUtil.putString(byteBuffer, someString, CHARSET);
        ByteBufferUtil.putIntegerList(byteBuffer, integerList);
        ByteBufferUtil.putStringList(byteBuffer, stringList, CHARSET);
    }
 
    @Override
    public void decode(final ByteBuffer byteBuffer) {
        this.someInteger = byteBuffer.getInt();
        this.someFloat = byteBuffer.getFloat();
        this.someString = ByteBufferUtil.getString(byteBuffer, CHARSET);
        this.integerList = ByteBufferUtil.getIntegerList(byteBuffer);
        this.stringList = ByteBufferUtil.getStringList(byteBuffer, CHARSET);
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(
				   "someInteger:" + someInteger + "\n"
				 + "someFloat:" + someFloat + "\n"
				 + "someString:" + someString + "\n"
				 );

		builder.append("integerList:\n");
		for (int i : integerList) {
			builder.append(" " + i + ",");
		}
		builder.append("\n");

		builder.append("stringList:\n");
		for (String s : stringList) {
			builder.append(" " + s + ",");
		}
		builder.append("\n");

		return builder.toString();
	}

	public SimplePacket() {
		super();
	}

	public SimplePacket(int someInteger, float someFloat,
			String someString,
			List<Integer> integerList,
			List<String> stringList) {
		super();
		this.someInteger = someInteger;
		this.someFloat = someFloat;
		this.someString = someString;
		this.integerList = integerList;
		this.stringList = stringList;
	}
     
    // Setters and getters here
}