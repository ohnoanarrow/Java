import java.util.Scanner;
import java.util.Arrays;
class TowerOfHanoi{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to TowerOfHanoi");
		String[] towerNames =new String[3];
		for(int i =0;i<=2;i++ ){
			System.out.println("Please enter a tower name");
			 towerNames[i] = scan.nextLine();
		}

		LLStack<hanoiDisk> A = new LLStack<hanoiDisk>(towerNames[0]);
		LLStack<hanoiDisk> B = new LLStack<hanoiDisk>(towerNames[1]);
		LLStack<hanoiDisk> C = new LLStack<hanoiDisk>(towerNames[2]);

		System.out.println("How many disk(s) would you like");
		int amountDisk = scan.nextInt();
		scan.nextLine();
		hanoiDisk[] arrayDisks =new hanoiDisk[amountDisk];
		for(int i =0;i<amountDisk;i++ ){
			System.out.println("Please enter your disks in descending order");
			System.out.println("Please enter your disk name");
			String name = scan.nextLine();
			System.out.println("Please enter your disk value");
			int value = scan.nextInt();
			scan.nextLine();
			hanoiDisk cat = new hanoiDisk(name,value);
			A.push(cat);

		}
		move(A,C,B,A.size());

		System.out.println("The final tower in the destination is: ");
		while(C.empty() == false){
			System.out.println(C.pop().value());
		}//while for printing

	}

	public static void move(LLStack<hanoiDisk> source, LLStack<hanoiDisk> destination, LLStack<hanoiDisk> holding, int size){
		if(size>1){
			move(source, holding, destination, size-1);
			moveDisk(source,destination);
			move(holding, destination, source, size-1);
		}
		else if(size==1){
			moveDisk(source,destination);
		}
	}// Moves disk to the proper Destination using recursion.

	public static void moveDisk(LLStack<hanoiDisk> source,LLStack<hanoiDisk> destination){

		hanoiDisk disk = source.pop();
		System.out.println(source.name()+"-->"+destination.name()+" "+disk.name());
		destination.push(disk);

	}

}
