package domain;

public class OpenFile {
	private int fileDescriptor;
	
	public OpenFile(int fd) {
		this.fileDescriptor = fd;
	}

	public int getFileDescriptor() {
		return this.fileDescriptor;
	}
	
	public void setFileDescriptor(int fd) {
		this.fileDescriptor = fd;
	}
}
