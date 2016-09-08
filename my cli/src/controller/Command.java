package controller;

import java.io.IOException;

import model.MyModel;
import view.MyView;

public interface Command {
	public static final MyView view=new MyView();
	public static final MyModel model=new MyModel();
	public void doCommand(String string) throws IOException;
}
