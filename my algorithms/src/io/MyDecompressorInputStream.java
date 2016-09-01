package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
	
	private InputStream in;
	
	
	public MyDecompressorInputStream() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyDecompressorInputStream(InputStream in) {
		super();
		this.in = in;
	}
	
	@Override
	public int read() throws IOException {
		in.read();
		return this.in.read();
	}
	@Override
    public int read(byte b[]) throws IOException {
		byte temp[] = new byte[b.length-9];
		in.read(temp);
		int j=-1;
		for(int i=9;i<temp.length;i=i+2){
			int times=temp[i];
			while(times>0){
				j=j+1;
				times--;
				b[j]=temp[i+1];
			}
		}
		return 0;
	}
}

