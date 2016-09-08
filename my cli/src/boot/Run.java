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
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //standart streams
	    PrintWriter out = new PrintWriter(System.out, true);
	    MyView view=new MyView(in,out);
	    MyModel model=new MyModel();
	    MyController controller=new MyController(view,model);
	    view.setController(controller);
	    model.setController(controller);
	    view.start();

	}

}
