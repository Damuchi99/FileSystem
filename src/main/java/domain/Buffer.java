package domain;

public class Buffer {
	private byte[] bufferBytes;
	private int bufferStart;
	private int bufferEnd;
	
    public Buffer(int tamanio){
        this.bufferBytes = new byte[tamanio];
        this.bufferStart = 0;
        this.bufferEnd = tamanio - 1;
    }

    public void limit(int bytesLeidos){
        this.bufferEnd = this.bufferStart + bytesLeidos;
    }

	public byte[] getBufferBytes() {
		return bufferBytes;
	}

	public void setBufferBytes(byte[] bufferBytes) {
		this.bufferBytes = bufferBytes;
	}

	public int getBufferStart() {
		return bufferStart;
	}

	public void setBufferStart(int bufferStart) {
		this.bufferStart = bufferStart;
	}

	public int getBufferEnd() {
		return bufferEnd;
	}

	public void setBufferEnd(int bufferEnd) {
		this.bufferEnd = bufferEnd;
	}
}
