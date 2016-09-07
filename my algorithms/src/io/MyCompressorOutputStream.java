package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	
	private OutputStream out;

	public MyCompressorOutputStream(OutputStream out) {
		super();
		this.out = out;
	}

	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}
	
	@Override
	public void write(byte b[]) throws IOException {
		write(b, 0, 9);
		int i=9;
		while(i<b.length){
			int counter=1;
			int x=b[i];
			for(i=i+1;i<b.length;i++){
				if(b[i]==x)
					counter++;
				else
					break;
			}
			while(counter>127){
				write(127);
				write(x);
				counter=counter-127;
			}
			write(counter);
			write(x);
			close();
		}
	}

}