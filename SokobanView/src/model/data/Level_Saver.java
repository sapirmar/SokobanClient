package model.data;
import java.io.IOException;
import java.io.OutputStream;

public interface Level_Saver {
public void SaveLevel(OutputStream out,Level2D level2d)throws IOException;

}
