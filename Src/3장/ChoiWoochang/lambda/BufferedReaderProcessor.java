import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static String processFile() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
			return br.readLine();
		}
	}
	
	public static String processFile2(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
			return p.process(br);
		}
	}
	
	public static void main(String[] args) throws IOException {
		String result = processFile();
		System.out.println(result);
		String result1 = processFile2((BufferedReader br) -> br.readLine());
		System.out.println(result1);
		
	}
}
