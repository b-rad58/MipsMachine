import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MIPStester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] register = new int[30];
		Scanner scanner;
		try {
			 String temp;
			 scanner = new Scanner(new File("register.txt"));
		     for (int i=0 ; i<30; i++) {
		    	 temp = scanner.next();
		    	 temp = temp.replaceAll("<", "");
		    	 temp = temp.replaceAll(">", "");
		         register[i] = Integer.parseInt(temp);
		     }
		 }
		 catch (FileNotFoundException e) {
			 System.out.println("File not found");
		 }
		MIPS mips = new MIPS(register);
		
		String[] instruction = new String[4];
		
		System.out.println("Enter instruction in the format:\n <opcode><operand1><operand2><operand3>");
		String inst = in.next();
		
		
		while (!(inst.equals("0"))) {
			inst = inst.replaceAll("<", "");
			instruction = inst.split(">");
		
			System.out.println(instruction[0]);
			System.out.println(instruction[1]);
			System.out.println(instruction[2]);
			System.out.println(instruction[3]);

			switch (Integer.parseInt(instruction[0])) {
				case 32: mips.add(Integer.parseInt(instruction[1]), Integer.parseInt(instruction[2]), Integer.parseInt(instruction[3]));
                     break;
				case 34: mips.sub(Integer.parseInt(instruction[1]), Integer.parseInt(instruction[2]), Integer.parseInt(instruction[3]));
            		 break; //sub
				case 35: mips.lw(Integer.parseInt(instruction[1]), Integer.parseInt(instruction[2]), Integer.parseInt(instruction[3])); 
            		 break; //lw
				case 43: mips.sw(Integer.parseInt(instruction[1]), Integer.parseInt(instruction[2]), Integer.parseInt(instruction[3])); 
            		 break; //sw
				case  4: mips.beq(Integer.parseInt(instruction[1]), Integer.parseInt(instruction[2]), Integer.parseInt(instruction[3])); 
            		 break; //beq
				default: break; //error
			}
			System.out.println("Enter instruction in the format (or 0 to exit):\n <opcode><operand1><operand2><operand3>");
			inst = in.next();
			
		}
		
		System.out.println("Register Values: ");
		mips.printRegister();
		System.out.println("\nMemory Values: ");
		mips.printMemory();
	
		
		
		in.close();
		

	}

}
