import java.util.Scanner;
import java.util.Timer;
class HotPotato{
    public static ArrayQueue<Child> adminCopy= new ArrayQueue<Child>();
    public static ArrayQueue<Child> StudentCopy= new ArrayQueue<Child>();
    private static boolean queueExists;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to enter 1 for Admin mode or 2 for User mode");
        int option = scan.nextInt();
        if(option==1){
            System.out.println("How many students do you have?");
            int size=scan.nextInt();
            if(queueExists==false){
                createQueue(size);
                queueExists=true;
            }
            Child winner =playGame(adminCopy);
            System.out.println("This is the information for your winner:");
            winner.returnInformation();
        }
        else if(option==2){
            System.out.println("How many players do you have?");
            int size=scan.nextInt();
            if(queueExists==false){
                createQueue(size);
                queueExists=true;
            }
            Child winner =playGame(StudentCopy);
            System.out.println("This is your winner:");
            winner.returnName();

        }
        else{
            System.out.println("Enter 1 or 2");
        }
    }
    public static void createQueue(int size){
        Scanner scan = new Scanner(System.in);
        ArrayQueue<Child> queue = new ArrayQueue<Child>();
        for (int i =0;i<size ;i++ ) {
            System.out.println("Please enter name of Child "+i);
            String name = scan.nextLine();
            System.out.println("Please enter Gender of Child "+i);
            String Gender = scan.nextLine();
            System.out.println("Please enter Age of Child "+i);
            int Age = scan.nextInt();
            scan.nextLine();
            Child newChild = new Child(name,Gender,Age);
          StudentCopy.add(newChild);
           adminCopy.add(newChild);
          //queue.add(newChild);
        }
        //return  queue;

    }//creates a queue of children of size size
    public static Child playGame(ArrayQueue<Child> queueKids){
        int skip =60;
        while(queueKids.size>1){
            for (int i =0;i<skip ;i++ ) {
                queueKids.add(queueKids.remove());
            }
            // this is one round
            queueKids.remove();//removes one kid at end of duration of round
        }
        return queueKids.remove();
    }

}
