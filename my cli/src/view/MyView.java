package view;

import java.io.IOException;

public class MyView implements Facade {
	
	private CLI cli;

	public MyView(CLI cli) {
		super();
		this.cli = cli;
	}

	public void start() throws IOException{
		cli.start();
	}
}
