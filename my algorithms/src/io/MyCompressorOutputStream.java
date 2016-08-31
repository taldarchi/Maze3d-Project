package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	
	private OutputStream out;
	
	public MyCompressorOutputStream() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyCompressorOutputStream(OutputStream out) {
		super();
		this.out = out;
	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub

	}

}
