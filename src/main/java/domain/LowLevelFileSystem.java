package domain;

import java.util.function.Consumer;

public interface LowLevelFileSystem {
	  public int openFile(String path);
	  public void closeFile(int fd);
	  public int syncReadFile(int fd, byte[] bufferBytes, int bufferStart, int bufferEnd);
	  public void syncWriteFile(int fd, byte[] bufferBytes, int bufferStart, int bufferEnd);
	  public void asyncReadFile(int fd, byte[] buffer, int bufferStart, int bufferEnd, Consumer<Integer> callback);
}
