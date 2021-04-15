import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Comparator;
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;

class playlist{
    ArrayList<Song> contents;
    String name;
    // Constructor for playlist
    public playlist(ArrayList<Song> c, String n){
        contents=c;//An array of songs
        name=n;//name of playlist
    }
    public ArrayList<Song> getContents(){
        return contents;
    }//Getter for Songs
    public String getName(){
        return name;
    }
    public <T> void playlistMergeSort(ArrayList<T> contents,Comparator<? super T> comp){
        T[] a = (T[]) contents.toArray();
        a= (T[])mergesort.mergeSort(a,(Comparator)comp);
        ListIterator<T> iterator = contents.listIterator();
         for (int j=0; j<a.length; j++) {
             iterator.next();
             iterator.set((T)a[j]);
        }
    }// Starts MergeSort
    public <T> void playlistInsertionSort(ArrayList<T> contents, Comparator<? super T> comp){
        T[] a = (T[]) contents.toArray();
        a = (T[]) insertionSort.insertionSort(a,(Comparator)comp);
        ListIterator<T> iterator = contents.listIterator();
         for (int j=0; j<a.length; j++) {
             iterator.next();
             iterator.set((T)a[j]);
        }
    }

    //Sorts playlist based on sortingMethod,playlist
    public static playlist sortPlaylist(playlist p ,String category,int sortingMethod){
        //This will be for merge sort
        if (sortingMethod==1) {
            switch (category) {
                case "name":p.playlistMergeSort(p.getContents(),Comparator.comparing(Song::getName));
                    break;
                case "artist":p.playlistMergeSort(p.getContents(),Comparator.comparing(Song::getArtist));
                    break;
                case "genre":p.playlistMergeSort(p.getContents(),Comparator.comparing(Song::getGenre));
                    break;
                case "year":p.playlistMergeSort(p.getContents(),Comparator.comparing(Song::getYear,Comparator.nullsLast(Comparator.naturalOrder())));
                    break;
                    //Comparator.comparing(MenuList::getParentId, Comparator.nullsLast(Comparator.naturalOrder())))
                case "ranking":p.playlistMergeSort(p.getContents(),Comparator.comparing(Song::getRanking));
                    break;
            }

        }
        //This will be for insertion sort
        else if (sortingMethod==2) {
            switch (category) {
                case "name":p.playlistInsertionSort(p.getContents(),Comparator.comparing(Song::getName));
                    break;
                case "artist":p.playlistInsertionSort(p.getContents(),Comparator.comparing(Song::getArtist));
                    break;
                case "genre":p.playlistInsertionSort(p.getContents(),Comparator.comparing(Song::getGenre));
                    break;
                case "year":p.playlistInsertionSort(p.getContents(),Comparator.comparing(Song::getYear,Comparator.nullsLast(Comparator.naturalOrder())));
                    break;
                case "ranking":p.playlistInsertionSort(p.getContents(),Comparator.comparing(Song::getRanking));
                    break;
            }
        }
        else {
            System.out.println("Please Choose a Sorting Method");
            sortPlaylist(p,category,sortingMethod);
        }


        return p;
    }
    public static ArrayList<Song> getSongs(String textfile){
        ArrayList<Song> playlist = new ArrayList<Song>();
        String line=null;
         try{
         FileReader read = new FileReader(textfile);
         BufferedReader reader = new BufferedReader(read);
         while((line=reader.readLine())!=null){
            String[] songLine =line.split(",");
            Song newSong = new Song(songLine[0],songLine[1],songLine[2],Integer.parseInt(songLine[3].trim()),Integer.parseInt(songLine[4].trim()));
            playlist.add(newSong);
         }
     }
         catch (FileNotFoundException e) {
             System.out.println("We could not locate file");
         }
         catch (IOException e) {
            System.out.println("IOException");
         }
         return playlist;
    }
    public static void displaySongInfo(playlist p){
        for(int i =0;i<p.contents.size();i++){
            System.out.println(p.contents.get(i));
        }
    }
    public static void savePlaylist(playlist p) throws IOException{
        PrintWriter writer = null;
        writer = new PrintWriter(new File("sortedPlaylist.txt"));
        for(int i =0;i<p.contents.size();i++){
            writer.write(p.contents.get(i).toString());
        }
        writer.flush();
        writer.close();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Playlist Sorter");
        System.out.println("You can save your music in a text file called music");
        System.out.println("You can sort by: Artist,Genre,Song Name,Release Year or Rating");
        System.out.println("How would you like your music sorted?");
        System.out.println("Please enter artist,genre,name,year or rating");
        String category=scan.nextLine();
        if (!(category.equals("name")||category.equals("genre")||category.equals("artist")||category.equals("year")||category.equals("rating"))) {
                System.out.println("Please enter one of the choices");
                category=scan.nextLine();
        }
        System.out.println("What method would you like it sorted by: Insertion Sort or Merge Sort ?");
        System.out.println("Enter 1 for MergeSort or 2 for Insertion Sort");
        int choice = 0;
        try {
             choice = scan.nextInt();
        }
        catch (Exception e) {
                System.out.println("Please enter a number 1 or 2");
        }
        playlist jams = new playlist(getSongs("music.txt"),"jams");
        playlist sorted = sortPlaylist(jams,category,choice);
        displaySongInfo(sorted);
        try {
            savePlaylist(sorted);
        }
        catch (IOException e) {
            System.out.println("Something Brokes");
        }
        System.out.println("Your sortedPlaylist has been saved to sortedPlaylist.txt");
    }
}
