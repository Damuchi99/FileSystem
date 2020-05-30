package domain;

import java.util.function.Consumer;

public class HighLevelFileSystem {
	
	private LowLevelFileSystem lowLevelFileSystem;
	
	public HighLevelFileSystem(LowLevelFileSystem lowLevelFileSystem) {
		this.lowLevelFileSystem = lowLevelFileSystem;
	}
	
	public OpenFile openFile(String path) {
		int fd = this.lowLevelFileSystem.openFile(path);
		return new OpenFile(fd);
	}
	
	public void closeFile(int fd) {
		this.lowLevelFileSystem.closeFile(fd);
	}
	
	public void syncReadFile(OpenFile file, Buffer buffer) {
		int bytesLeidos = this.lowLevelFileSystem.syncReadFile(
				file.getFileDescriptor(), 
				buffer.getBufferBytes(), 
				buffer.getBufferStart(), 
				buffer.getBufferEnd()
				);
		
		buffer.limit(bytesLeidos);
	}
	
	void syncWriteFile(OpenFile file, Buffer buffer) {
		this.lowLevelFileSystem.syncWriteFile(file.getFileDescriptor(), 
				buffer.getBufferBytes(), 
				buffer.getBufferStart(), 
				buffer.getBufferEnd()
				);
	}
	
	void asyncReadFile(OpenFile file, Consumer<Buffer> callback) {
		Buffer buffer = new Buffer(100);
		this.lowLevelFileSystem.asyncReadFile(file.getFileDescriptor(), 
				buffer.getBufferBytes(), 
				buffer.getBufferStart(), 
				buffer.getBufferEnd(), 
				bytesLeidos -> {
					buffer.limit(bytesLeidos);
					callback.accept(buffer);
				});
	}
}
