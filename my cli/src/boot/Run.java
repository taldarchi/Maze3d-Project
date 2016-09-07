package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.MyController;
import model.MyModel;
import view.MyView;

public class Run {

	public static void main(String[] args) throws IOException {
		MyView view=new MyView();
		MyModel model=new MyModel();
		MyController controller=new MyController(view,model);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //stream
	    PrintWriter out = new PrintWriter(System.out);
		controller.start(in,out);

	}

}
