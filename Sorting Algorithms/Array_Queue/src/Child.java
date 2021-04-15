class Child{
    private String Name;
    private String Gender;
    private int Age;
    public Child(String Name, String Gender,int Age){
        this.Name=Name;
        this.Gender=Gender;
        this.Age=Age;
    }//Constuctor for Class
    public  void returnInformation(){
        System.out.println("Name: "+this.Name);
        System.out.println("Gender: "+this.Gender);
        System.out.println("Age: "+this.Age);
    }
    public  void returnName(){
        System.out.println("Name: "+this.Name);

    }

}
