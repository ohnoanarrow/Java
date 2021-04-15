
class Song{


    private String name;//Name of song
    private String artist; //Song Artist
    private String genre;//Songs genre
    private int year;//Release Year
    private int ranking;//Songs Ranking


    public Song(String name,String artist, String genre, int year, int ranking){
        super();
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.ranking = ranking;
    }//Constructor

    public String getName(){
        return name;
    }// name getter

    public String getArtist(){
        return artist;
    }// artist getter


    public String getGenre(){
        return genre;
    }// genre getter


    public int getYear(){
        return year;
    }// year getter


    public int getRanking(){
        return ranking;
    }// Ratin getter
    @Override
    public String toString() {
        return "Song Name:" + this.getName()+" Artist Name:"+this.getArtist()+ " Genre:"+this.getGenre() + " Year:" + this.getYear() + " Ranking:" + this.getRanking() + "\n";
          //return "Song Name:" + this.getName()+ "Year:" + this.getYear() +"\n";
            //return "Ranking"+this.getRanking();
    }
}//class
