package test;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import domain.Buffer;
import domain.HighLevelFileSystem;
import domain.LowLevelFileSystem;
import domain.OpenFile;

public class FileSystemTest {
	HighLevelFileSystem highLevelFileSystem;
	LowLevelFileSystem lowLevelFileSystem;
	Buffer buffer;
	OpenFile file;
	
	@Before
	public void init() {
		lowLevelFileSystem = Mockito.mock(LowLevelFileSystem.class);
		highLevelFileSystem = new HighLevelFileSystem(lowLevelFileSystem);
	}
	
	@Test
	public void abrirArchivo() {
		highLevelFileSystem.openFile("archivo.txt");
		Mockito.verify(lowLevelFileSystem).openFile("archivo.txt");
	}
	
	@Test
	public void cerrarArchivo() {
		highLevelFileSystem.closeFile(1);
		Mockito.verify(lowLevelFileSystem).closeFile(1);
	}
	
	@Test
	public void leerArchivoSincronicamente() {
		buffer = new Buffer(4);
		Mockito.when(lowLevelFileSystem.openFile("archivo.txt")).thenReturn(2);
		file = highLevelFileSystem.openFile("archivo.txt");
		highLevelFileSystem.syncReadFile(file, buffer);
		
		Mockito.verify(lowLevelFileSystem).syncReadFile(2, new byte[4], 0, 3);
	}
	
	@Test
    public void leerArchivoAsincronicamente() {
		buffer = new Buffer(4);
		Consumer<Buffer> callback = (buffer) -> {};
		Mockito.when(lowLevelFileSystem.openFile("archivo.txt")).thenReturn(2);
        file = highLevelFileSystem.openFile("archivo.txt");
        highLevelFileSystem.asyncReadFile(file, callback, buffer);
        Mockito.verify(lowLevelFileSystem)
        	   .asyncReadFile(Mockito.anyInt(), 
        			   		  Mockito.any(byte[].class), 
        			   		  Mockito.anyInt(), 
        			   		  Mockito.anyInt(), 
        			   		  Mockito.any(Consumer.class));
    }
	
	@Test
	public void escribirArchivoSincronicamente() {
		buffer = new Buffer(4);
		Mockito.when(lowLevelFileSystem.openFile("archivo.txt")).thenReturn(2);
		file = highLevelFileSystem.openFile("archivo.txt");
		highLevelFileSystem.syncWriteFile(file, buffer);
		
		Mockito.verify(lowLevelFileSystem).syncWriteFile(2, new byte[4], 0, 3);
	}
}
