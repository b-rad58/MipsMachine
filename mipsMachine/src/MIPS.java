

public class MIPS {

	/**
	 * @param args
	 */
	private int[] register = new int[30];
	private int[] memory = new int[64];
	private int PC = 0;
	private int readAddress;
	private int readRegister1;
	private int readRegister2;
	private int dataField;
	private int readData1;
	private int readData2;
	private int writeData;
	private int ALUresult;
	private int zero;
	private int address;
	private int readData;
	
	public int[] getRegister() {
		return register;
	}
	
	public int[] getMemory() {
		return memory;
	}
	public MIPS(int[] register) {
		this.register = register;
	}
	public void printRegister() {
		for (int i=0; i<30; i++) {
			System.out.println("R" + (i+1) + ": " + register[i]);
		}
	}
	
	public void printMemory() {
		for (int i=0; i<64; i+=4) {
			System.out.print("Memory" + (i+1) + ": " + memory[i]);
			System.out.print("\tMemory" + (i+2) + ": " + memory[i+1]);
			System.out.print("\tMemory" + (i+3) + ": " + memory[i+1]);
			System.out.println("\tMemory" + (i+4) + ": " + memory[i+1]);
		}
	}
	
	public MIPS() {}
	
	// function for R type to abstract common behaviour 
	
	public void add(int operand1, int operand2 ,int operand3) {
		//register[operand1] = register[operand2] + register[operand3];
		
		System.out.println("\nSemantics: $" + operand1 + " = $" + operand2 + " + $" + operand3);
		
		//IF Stage
		System.out.println("\nIF Stage:");
		readAddress = PC;
		System.out.println("Read address: " + readAddress);
		PC += 4; // +=4
		
		// IF/ID buffer
		System.out.println("\nIF/ID buffer");
		System.out.println("+----------------------------------------+");
		System.out.println("| op | rs | rt | rd | shamt | funct | PC |");
		System.out.println("+----+----+----+----+-------+------------+");
		System.out.println("| 0  | " + operand2 + "  | " + operand3 + "  | " + operand1 + "  |   0   |   32  | " + PC + "  |");
		System.out.println("+----------------------------------------+");
		
		// ID Stage
		System.out.println("\nID Stage");
		readRegister1 = operand2; //from rs-field
		readRegister2 = operand3; //from rt-field
		
		System.out.println("readRegister1: " + readRegister1);
		System.out.println("readRegister2: " + readRegister2);
		
		dataField = operand1; //labled instruction[15,..,11] contains the rd-field (i.e., the destination field)
		System.out.println("writeRegister: " + dataField);

		readData1 = register[readRegister1]; //content of readRegister1
	    readData2 = register[readRegister2]; //content of readRegister2
	    
	    System.out.println("Readdata1: " + readData1);
	    System.out.println("Readdata2: " + readData2);
		
		
		
		//Control Signal Vector (for add)
		System.out.println("\nControl Signal Vector");
		System.out.println("+-----------------------------------------------------------------------------------------------+");
		System.out.println("|            Execution Stage            |         Memory Stage          | Write Back Stage      |");
		System.out.println("+---------------------------------------+-------------------+-----------------------------------+");
		System.out.println("| Reg Dst | ALU Op1 | ALU Op0 | ALU Src | Branch | Mem Read | Mem Write | Reg Write | MemToReg  |");
		System.out.println("+---------+---------+---------+---------+--------+----------+-----------+-----------+-----------+");
		System.out.println("|    1    |    1    |    0    |    0    |    0   |     0    |     0     |      1    |     0     |");
		System.out.println("+-----------------------------------------------------------------------------------------------+");
		
			    
	    // ID/EX buffer
	    System.out.println("\nID/EX buffer");
	    System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("| PC | ReadData1 | ReadData2 | Instructions[15-0] | Instructions[20-16] | Instructions[15-11] |");
		System.out.println("+----+-----------+-----------+--------------------+---------------------+---------------------+");
		System.out.println("| " + PC + "  |     " + readData1 + "     |     " + readData2 + "     |         n/a        |           "   +  readRegister2 + "         |          " + dataField + "          |");  
		System.out.println("+---------------------------------------------------------------------------------------------+");
		
		// EX Stage (ALOP determines the function of the ALU)
		System.out.println("\nEX Stage"); 
		System.out.println("MUX 11: " + readData2); 
		
		ALUresult = readData1 + readData2;
		register[operand1] = ALUresult;
		
		if (readData1 - readData2 == 0)
			zero = 1;
		else
			zero = 0;
		
		// EX/MEM buffer
		System.out.println("\nEX/MEM buffer");
		System.out.println("+------------------------------------------------------------+");
		System.out.println("| Add result | ReadData2 | ALU result | Zero | Add Result |");
		System.out.println("+---------------+-----------+------------+-------------------+");
		System.out.println("|     2         |     " + readData2 + "     |     " + ALUresult + "     |  " + zero + "   |      " + PC + "     |");
		System.out.println("+------------------------------------------------------------+");
		
		// MEM Stage
		System.out.println("\nMEM Stage");
		
		
		// MEM/WB buffer
		System.out.println("\nMEM/WB buffer");
		System.out.println("+----------+-------------+----+");
		System.out.println("| ReadData |  ALU result | rd |");
		System.out.println("+----------+-------------+----+");
		System.out.println("|    Z     |     " + ALUresult + "      |  " + dataField + " |");
		System.out.println("+----------+-------------+----+");
		
		// WB stage
		System.out.println("\nWB Stage");
		System.out.println("WriteData: " + ALUresult);
		System.out.println("WriteReg: " + dataField);
		
				
		
		
		
		
	}
	
	public void sub(int operand1, int operand2 ,int operand3) {
		//register[operand1] = register[operand2] - register[operand3];
		
		System.out.println("\nSemantics: $" + operand1 + " = $" + operand2 + " - $" + operand3);
		
		//IF Stage
		System.out.println("\nIF Stage:");
		readAddress = PC;
		System.out.println("Read address: " + readAddress);
		PC += 4; // +=4
		
		// IF/ID buffer
		// IF/ID buffer
				System.out.println("\nIF/ID buffer");
				System.out.println("+----------------------------------------+");
				System.out.println("| op | rs | rt | rd | shamt | funct | PC |");
				System.out.println("+----+----+----+----+-------+------------+");
				System.out.println("| 0  | " + operand2 + "  | " + operand3 + "  | " + operand1 + "  |   0   |   34  | " + PC + "  |");
				System.out.println("+----------------------------------------+");
				
		// ID Stage
		System.out.println("\nID Stage");
		readRegister1 = operand2; //from rs-field
		readRegister2 = operand3; //from rt-field
				
		System.out.println("readRegister1: " + readRegister1);
		System.out.println("readRegister2: " + readRegister2);
				
		dataField = operand1; //labled instruction[15,..,11] contains the rd-field (i.e., the destination field)
		System.out.println("writeRegister: " + dataField);

		readData1 = register[readRegister1]; //content of readRegister1
		readData2 = register[readRegister2]; //content of readRegister2
			    
		System.out.println("Readdata1: " + readData1);
		System.out.println("Readdata2: " + readData2);
		
		
		
		//Control Signal Vector (for add)
		System.out.println("\nControl Signal Vector");
		System.out.println("+-----------------------------------------------------------------------------------------------+");
		System.out.println("|            Execution Stage            |         Memory Stage          | Write Back Stage      |");
		System.out.println("+---------------------------------------+-------------------+-----------------------------------+");
		System.out.println("| Reg Dst | ALU Op1 | ALU Op0 | ALU Src | Branch | Mem Read | Mem Write | Reg Write | MemToReg  |");
		System.out.println("+---------+---------+---------+---------+--------+----------+-----------+-----------+-----------+");
		System.out.println("|    1    |    1    |    0    |    0    |    0   |     0    |     0     |      1    |     0     |");
		System.out.println("+-----------------------------------------------------------------------------------------------+");
			    
	    // ID/EX buffer
		 System.out.println("\nID/EX buffer");
		    System.out.println("+---------------------------------------------------------------------------------------------+");
			System.out.println("| PC | ReadData1 | ReadData2 | Instructions[15-0] | Instructions[20-16] | Instructions[15-11] |");
			System.out.println("+----+-----------+-----------+--------------------+---------------------+---------------------+");
			System.out.println("| " + PC + "  |     " + readData1 + "     |     " + readData2 + "     |         n/a        |           "   +  readRegister2 + "         |          " + dataField + "          |");  
			System.out.println("+---------------------------------------------------------------------------------------------+");
			
		// EX Stage (ALOP determines the function of the ALU)
		System.out.println("\nEX Stage");
		
		
		ALUresult = readData1 - readData2;
		register[operand1] = ALUresult;
		
		if (readData1 - readData2 == 0)
			zero = 1;
		else
			zero = 0;
		
		// EX/MEM buffer
		System.out.println("\nEX/MEM buffer");
		System.out.println("+--------------------------------------------+");
		System.out.println("| ReadData2 | ALU result | Zero | Add Result |");
		System.out.println("+-----------+------------+------+------------+");
		System.out.println("|     " + readData2 + "     |     " + ALUresult + "     |  " + zero + "   |      " + PC + "     |");
		System.out.println("+--------------------------------------------+");
		
		// MEM Stage
		System.out.println("\nMEM Stage");
		
		// MEM/WB buffer
		System.out.println("\nMEM/WB buffer");
		System.out.println("+----------+-------------+----+");
		System.out.println("| ReadData |  ALU result | rd |");
		System.out.println("+----------+-------------+----+");
		System.out.println("|    Z     |     " + ALUresult + "      |  " + dataField + " |");
		System.out.println("+----------+-------------+----+");
		
		// WB stage
		System.out.println("\nWB Stage");
		System.out.println("Write data: " + ALUresult);
		System.out.println("Write register: " + dataField);
	}
	
	public void lw(int operand1, int operand2 ,int operand3) {
		
		System.out.println("\nSemantics: $" + operand1 + " = Memory[$" + operand3 + "+" + operand2 + "]");
		
		//IF Stage
		System.out.println("\nIF Stage:");
		readAddress = PC;
		System.out.println("Read address: " + readAddress);
		PC += 4; // +=4
		
		// IF/ID buffer
		System.out.println("\nIF/ID buffer");
		System.out.println("+----------------------------------------+");
		System.out.println("| op | rs | rt | address/immediate | PC |");
		System.out.println("+----+----+----+-------------------+----+");
		System.out.println("| 35 | " + operand1 + "  | " + operand3 + "  |        " + operand2 + "          |  " + PC + " |");
		System.out.println("+----------------------------------------+");
		
		// ID Stage
		System.out.println("\nID Stage");
		readRegister1 = operand1; //from rs-field
		dataField = operand3; //from rt-field
		
		System.out.println("readRegister : " + readRegister1);
		System.out.println("writeRegister: " + dataField);
		System.out.println("Memory address: $" + dataField + "+" + operand2);

		readData1 = register[readRegister1]; //content of readRegister1	
		readData2 = register[dataField];
		System.out.println("Readdata1: " + readData1);
			    		
		address = operand2; //labled instruction[15,..,11] contains the rd-field (i.e., the destination field)
		System.out.println("address: " + address);
		System.out.println("address is converted from 16-bit to 32-bit");
		
		//Control Signal Vector (for add)
		System.out.println("\nControl Signal Vector");
		System.out.println("+-----------------------------------------------------------------------------------------------+");
		System.out.println("|            Execution Stage            |         Memory Stage          | Write Back Stage      |");
		System.out.println("+---------------------------------------+-------------------+-----------------------------------+");
		System.out.println("| Reg Dst | ALU Op1 | ALU Op0 | ALU Src | Branch | Mem Read | Mem Write | Reg Write | MemToReg  |");
		System.out.println("+---------+---------+---------+---------+--------+----------+-----------+-----------+-----------+");
		System.out.println("|    0    |    0    |    0    |    1    |    0   |     1    |     0     |      1    |     1     |");
		System.out.println("+-----------------------------------------------------------------------------------------------+");
		
		
	    // ID/EX buffer
	    System.out.println("\nID/EX buffer");
	    System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("| PC | ReadData1 | ReadData2 | Instructions[15-0] | Instructions[20-16] | Instructions[15-11] |");
		System.out.println("+----+-----------+-----------+--------------------+---------------------+---------------------+");
		System.out.println("| " + PC + "  |    " + readData1 + "      |    " + readData2 + "      |          " + address +  "         |       n/a           |          n/a        |");  
		System.out.println("+---------------------------------------------------------------------------------------------+");
		
		// EX Stage (ALOP determines the function of the ALU)
		System.out.println("\nEX Stage"); 		
		
		ALUresult = readData1 + address;
		System.out.println("ALUresult: " + ALUresult);
		
		if (readData1 - readData2 == 0)
			zero = 1;
		else
			zero = 0;
		
				
		// EX/MEM buffer
		System.out.println("\nEX/MEM buffer");
		System.out.println("+-----------------------------------------------+");
		System.out.println("| ReadData2 | ALU result | Zero | Add Result    |");
		System.out.println("+-----------+------------+------+---------------+");
		System.out.println("|     " + readData2 + "     |      " + ALUresult + "     |  " + zero + "   |       " + PC + "       |");
		System.out.println("+-----------------------------------------------+");
		
		// MEM Stage
		System.out.println("\nMEM Stage");
		
		readAddress = ALUresult;
		System.out.println("Address: " + ALUresult);
		
		readData = memory[readAddress];
		System.out.println("Read data: " + readData);
		
		// MEM/WB buffer
		System.out.println("\nMEM/WB buffer");
		System.out.println("+----------+-------------+");
		System.out.println("| ReadData |  ALU result |");
		System.out.println("+----------+-------------+");
		System.out.println("|    " + readData + "     |       " + ALUresult + "     |");
		System.out.println("+----------+-------------+");
		
		// WB stage
		System.out.println("\nWB Stage");
		System.out.println("Write register: " + readData);
		
	
	}
	
	public void sw(int operand1, int operand2 ,int operand3) {
		
        System.out.println("\nSemantics: Memory[$" + operand3 + "+" + operand2 + "] = $" + operand1);
		
		//IF Stage
		System.out.println("\nIF Stage:");
		readAddress = PC;
		System.out.println("Read address: " + readAddress);
		PC += 4; // +=4
		
		// IF/ID buffer
		System.out.println("\nIF/ID buffer");
		System.out.println("+----------------------------------------+");
		System.out.println("| op | rs | rt | address/immediate | PC |");
		System.out.println("+----+----+----+----+-------+------------+");
		System.out.println("| 43 | " + operand3 + "  | " + operand1 + "  | " + operand2 + "  |  " + PC + "  |");
		System.out.println("+----------------------------------------+");
		
		// ID Stage
		System.out.println("\nID Stage");
		readRegister1 = operand1; //from rs-field
		readRegister2 = operand3; //from rt-field
		
		System.out.println("readRegister1: " + readRegister1);
		System.out.println("readRegister2: " + readRegister2);
		
		readData1 = register[readRegister1]; //content of readRegister1	
		readData2 = register[readRegister2]; //content of readRegister2
		System.out.println("Readdata1: " + readData1);
		System.out.println("Readdata2: " + readData2);
			    		
		address = operand2;
		System.out.println("address: " + address);
		System.out.println("address is converted from 16-bit to 32-bit");
		
		//Control Signal Vector (for add)
		System.out.println("\nControl Signal Vector");
		System.out.println("+-----------------------------------------------------------------------------------------------+");
		System.out.println("|            Execution Stage            |         Memory Stage          | Write Back Stage      |");
		System.out.println("+---------------------------------------+-------------------+-----------------------------------+");
		System.out.println("| Reg Dst | ALU Op1 | ALU Op0 | ALU Src | Branch | Mem Read | Mem Write | Reg Write | MemToReg  |");
		System.out.println("+---------+---------+---------+---------+--------+----------+-----------+-----------+-----------+");
		System.out.println("|    X    |    0    |    0    |    1    |    0   |     0    |     1     |      0    |     X     |");
		System.out.println("+-----------------------------------------------------------------------------------------------+");
		
		
	    // ID/EX buffer
		System.out.println("\nID/EX buffer");
	    System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("| PC | ReadData1 | ReadData2 | Instructions[15-0] | Instructions[20-16] | Instructions[15-11] |");
		System.out.println("+----+-----------+-----------+--------------------+---------------------+---------------------+");
		System.out.println("| " + PC + "  |    " + readData1 + "      |    " + readData2 + "      |          " + dataField +  "         |       n/a           |          n/a        |");  
		System.out.println("+---------------------------------------------------------------------------------------------+");
		
		// EX Stage (ALOP determines the function of the ALU)
		System.out.println("\nEX Stage"); 		
		
		ALUresult = readData2 + address;
		
				
		// EX/MEM buffer
		System.out.println("\nEX/MEM buffer");
		System.out.println("+-----------------------------------------------+");
		System.out.println("| ReadData2 | ALU result | Zero | Add Result    |");
		System.out.println("+-----------+------------+------+---------------+");
		System.out.println("|     " + readData2 + "     |      " + ALUresult + "     |  " + zero + "   |       " + PC + "       |");
		System.out.println("+-----------------------------------------------+");
		
		// MEM Stage
		System.out.println("\nMEM Stage");
		
		System.out.println("Address: " + ALUresult);
		
		writeData = readData2;
		System.out.println("Write data: " + writeData);
		
		// MEM/WB buffer
		System.out.println("\nMEM/WB buffer");
		System.out.println("+----------+-------------+----+");
		System.out.println("| ReadData |  ALU result | rd |");
		System.out.println("+----------+-------------+----+");
		System.out.println("| " + readData + "     |     " + ALUresult + "      |  " + dataField + " |");
		System.out.println("+----------+-------------+----+");
		
		// WB stage
		System.out.println("\nWB Stage");
		System.out.println("Memory" + ALUresult + "= " + writeData);
		memory[ALUresult] = writeData;
	}
	
	public void beq(int operand1, int operand2 ,int operand3) {
		/*beq  $5, $13, label1
	      
	      Branch if EQual.  If the contents of registers $5
	      and $13 are the same (equal), then go to label1.
	    */
		
		System.out.println("\nSemantics: if($" + operand3 + "==$" + operand2 + ") go to PC+4+" + operand1 + "x4");
		readAddress = PC;
		PC += 4; // +=4
		System.out.println("+-----------------------------------+");
		System.out.println("| op | rs | rt |  address/immediate |");
		System.out.println("+----+----+----+----+---------------+");
		System.out.println("| 4  | "  + operand1 + " |  " + operand2 +  " |        " + operand3 + "       |");
		System.out.println("+-----------------------------------+");
		
		readRegister1 = operand1; //from rs-field
		readRegister2 = operand2; //from rt-field
		
		readData1 = register[readRegister1]; //content of readRegister1
	    readData2 = register[readRegister2]; //content of readRegister2
	    
	    if (readData1 - readData2 == 0)
			zero = 1;
		else
			zero = 0;
	    
	  //Control Signal Vector (for beq)
	  		System.out.println("+-----------------------------------------------------------------------------------------------+");
	  		System.out.println("|            Execution Stage            |  Memory Stage     |         Write back Stage          |");
	  		System.out.println("+---------------------------------------+-------------------+-----------------------------------+");
	  		System.out.println("| Reg Dst | ALU Op1 | ALU Op0 | ALU Src | Branch | Mem Read | Mem Write | Reg Write | MemToReg  |");
	  		System.out.println("+---------+---------+---------+---------+--------+----------+-----------+-----------+-----------+");
	  		System.out.println("|    X    |    0    |    1    |    0    |    1   |     0    |     0     |      0    |     X     |");
	  		System.out.println("+-----------------------------------------------------------------------------------------------+");
	  		
	  		 // ID/EX buffer
		    System.out.println("\nID/EX buffer");
		    System.out.println("+---------------------------------------------------------------------------------------------+");
			System.out.println("| PC | ReadData1 | ReadData2 | Instructions[20-16] | Instructions[15-11] | Instructions[10-0] |");
			System.out.println("+----+-----------+-----------+---------------------+---------------------+--------------------+");
			System.out.println("| " + PC + "  |    " + readData1 + "      |    " + readData2 + "      |    " + "     n/a         |          " + dataField + "          |" + "         n/a        |");  // maybe??
			System.out.println("+---------------------------------------------------------------------------------------------+");
			
			// EX Stage (ALOP determines the function of the ALU)
			System.out.println("\nEX Stage"); 		
			System.out.println("Zero: " + zero);

					
			// EX/MEM buffer
			System.out.println("\nEX/MEM buffer");
			System.out.println("+--------------------------------------------+");
			System.out.println("| ReadData2 | ALU result | Zero | Add Result |");
			System.out.println("+-----------+------------+------+------------+");
			System.out.println("|    " + readData2 + "      |      " + ALUresult + "     |  " + zero + "   |      " + PC + "     |");
			System.out.println("+--------------------------------------------+");
			
			// MEM Stage
			if (zero==1) {
				PC = PC+operand3*4;
				System.out.println("Equal");
			}
			else
				System.out.println("not equal");
			
			System.out.println("PC: " + PC);
			
			
		
	}
	
	

}
