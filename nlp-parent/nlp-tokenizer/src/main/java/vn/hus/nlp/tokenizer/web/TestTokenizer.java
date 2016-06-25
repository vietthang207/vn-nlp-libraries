import java.util.Scanner;

import vn.hus.nlp.tokenizer.VietTokenizer;

public class TestTokenizer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VietTokenizer vitk= new VietTokenizer();
		Scanner sc= new Scanner(System.in);
		while (true){
			String line= sc.nextLine();
			String[] arr=vitk.tokenize(line);
			System.out.println("len: "+arr.length);
			for (String w:arr){
				System.out.println(w);
			}
			System.out.println("segment: "+vitk.segment(line));
		}
	}

}
