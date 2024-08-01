package Excel;
import java.io.IOException;
import java.util.ArrayList;

public class testsampleExcel {

	public static void main(String[] args) throws IOException {
		
		dataDrivenExcel d = new dataDrivenExcel();
		ArrayList<String> data =d.getdata("Add Profile", "RestAssured");
		
		
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		

	}

}
