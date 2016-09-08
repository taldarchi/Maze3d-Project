package controller;

import java.io.IOException;

public interface Command {
	public void doCommand(String string) throws IOException;
}
