/**
 * @Jordan Dube
 * Project 3,  SongComparator.java
 * CMSC 256 Section 002
 * Semester 1, 2020
 * 09/26/2020
 *
 * This file is to provide a comparator to be used by the SongList Class
 *     to sort the entries in the LList by alphabetical order both by Song
 *     Title and by Album Title
 */

package cmsc256;

import bridges.data_src_dependent.Song;
import java.util.Comparator;

public class SongComparator implements Comparator<Song> {
  //variable created to keep track of how many times the method has been implemented
  int i = 0;
  
  @Override
  /**
   * method that compares 2 songs based on either Artist name or Album Title; Overrides the Comparator
   *     method of the Comparator Interface.
   *
   * @param o1 A Song object to be compared to another Song object
   * @param o2 A Song object to be compared to another Song object
   *
   * @returns an int that relates the fields being compared based on lexicographical position
   */
  public int compare(Song o1, Song o2) {
    //if it is being implemented for the first time in the SongList Class,
    //    returns a comparator that sorts based on Artist name, and on the
    //    second implementation, returns a comparator that sorts based on Album Title.
    if (i % 2 == 0) {
      return o1.getArtist().compareToIgnoreCase(o2.getArtist());
    } else
      return o1.getAlbumTitle().compareToIgnoreCase(o2.getAlbumTitle());
  }
}