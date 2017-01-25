package controller.server;

import java.io.InputStream;
import java.io.OutputStream;

public interface Client_Handler {
void handleClient(InputStream informClient,OutputStream outToClient);
}
