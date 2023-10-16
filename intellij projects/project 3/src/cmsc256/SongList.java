/*
  @Jordan Dube
 * Project 3,  SongList.java
 * CMSC 256 Section 002
 * Semester 1, 2020
 * 09/26/2020
 */

package cmsc256;

import bridges.connect.Bridges;

import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** This file is to provide a Main method that connects to the Bridges API
 *     and a different method to search and return the songs by a given artist.
 *     The methods sort and organize the Lists to make searching more efficient.
 */

public class SongList {
  public static List<Song> songList;
  public static List<Song> songArrayList = new ArrayList<>();
  public static LList<Song> lList = new LList<>();
  
  //Default Constructor for the SongList Class
  public SongList() {
    songList = null;
    songArrayList = null;
    lList = null;
  }
  //Main method that holds the Bridges Object and creates the LList object
  public static void main(String[] args) {
    //create the Bridges object
    Bridges bridges = new Bridges(4, "YOUR_USER_ID", "YOUR_API_KEY");
    // set title
    bridges.setTitle("Accessing Song Data");
    // create data source object
    DataSource ds = bridges.getDataSource();
    //add all the Song objects to a list
    try {
      songList = ds.getSongData();
    } catch (Exception e) {
      System.out.println("Unable to connect to BRIDGES");
    }
    //adds all objects in the songData List to the ArrayList n
    songArrayList.addAll(songList);
    //sorts the ArrayList by alphabetical order using the SongComparator Class as a comparator
    songArrayList.sort(new SongComparator());
    //adds all songs in the sorted ArrayList to the LList object created by the LList Class
    for (Song f : songArrayList) {
      lList.append(f);
    }
    
    //System.out.println(getSongsByArtist("Queen"));
  }
  /**
   * Returns a String containing the song titles and album titles of each song
   *     in the data Source with the given artist.
   *
   * @param artist A String that is compared to the artist field of each Song object.
   *
   * @return songsByGivenArtist A String containing the song titles and album titles concatenated
   *     with the artist of the songs composed by the passed in artist parameter.
   */
  public static String getSongsByArtist(String artist) {
    //creates a scanner to receive the user input if they fail to provide an artist's name
    Scanner sc = new Scanner("System.in");
    //catches an empty input and asks the user to correct the input
    if (artist.equals("")) {
      System.out.println("Please enter an artist name. ");
      artist = sc.next();
    }
    //creates a different ArrayList to catch only the songs composed by the given artist
    List<Song> result = new ArrayList<>();
    //creates a String to be returned by the method containing the required components
    String songsByGivenArtist = "";
    //moves the cursor to the start of the linked list
    lList.moveToStart();
    //adds the Song objects with the requested author to the ArrayList to be sorted by Album Title
    for (int i = 0; i < songArrayList.size(); i++) {
      //compares the given artist to the artist of each song in the LList object
      if (lList.getValue().getArtist().equalsIgnoreCase(artist)) {
        result.add(lList.getValue());
      }
      //moves the cursor 1 position to the right
      lList.next();
    }
    //sorts the ArrayList by Album Title
    result.sort(new SongComparator());
    //adds the Titles, Artist, and Album Titles of the correct songs to the String object with the correct formatting
    for (Song f : result) {
      songsByGivenArtist += ("Title: " + f.getSongTitle() + " Artist: " + f.getArtist() + " Album: "
          + f.getAlbumTitle() + "\n");
    }
    //returns a message if there are no songs by the given artist in the dataSource
    if (songsByGivenArtist.equals("")) {
      return ("No songs by given artist.");
    }
    //returns the correctly formatted String
    return songsByGivenArtist;
  }
}