package cmsc256;

/**
 * Jordan Dube
 * 09/13/2020
 * CMSC256 - 002
 * This class represents an implementation of the WackyStringInterface, and it overrides the methods found in said interface.
 *
 **/




public class RamString implements WackyStringInterface{
  private String currentString; //initializes instance variable as a String to be modified in later methods
  
  /**
   * default no parameter constructor for a RamString Object
   * sets the field currentString to "Go Rams! Go CS@VCU!"
   */
  public RamString() {
    currentString =("Go Rams! Go CS@VCU!");
  }
  
  /**
   * parameterized constructor for a RamString Object
   * @param string takes a String to be passed to the currentString field, and throws an IllegalArgumentException if the parameter is null
   */
  public RamString(String string) {
    if (string == null)
      throw new IllegalArgumentException();
    else
      this.currentString = string;
  }
  
  /**
   * Setter method to modify the currentString field
   * @param string takes a String and if it is not null, passes it to the currentString field
   *               if the passed string is null, throws an IllegalArgumentException
   */
  @Override
  public void setWackyString(String string) {
    if (string==null)
      throw new IllegalArgumentException();
    else
      this.currentString = string;
  }
  
  /**
   * getter method to access the currentString field
   * @return the String value stored in the currentString field
   */
  @Override
  public String getWackyString() {
    return currentString;
  }
  
  /**
   * initializes a StringBuilder Object onto which every third character in the String stored in the currentString field is appended
   * @return a String representation of the StringBuilder Object that contains a String of every third character found in the currentString field
   *
   * note: the method treats the first character(index 0) as position 1
   * */
  @Override
  public String getEveryThirdCharacter() {
    StringBuilder wacky = new StringBuilder();
    int i = 2;
    while(i< currentString.length()){
      wacky.append(currentString.charAt(i));
      i+=3;
      
    }
    return wacky.toString();
  }
  
  /**
   * Returns a string that consists of all and only the characters
   * in either the odd positions (i.e., first, third, fifth, and so on)
   * or in the even positions (i.e., second, fourth, sixth, and so on)
   * current string, in the same order and with the same case as in
   * the current string. The first character in the string is
   * considered to be in Position 1.
   * @param evenOrOdd a String to determine if odd or even characters are to be returned.
   * @return String made of characters in odd or even positions in the current string
   * @throws  IllegalArgumentException if parameter is other than "odd" or "even"
   */
  @Override
  public String getEvenOrOddCharacters(String evenOrOdd) throws IllegalArgumentException {
    StringBuilder wacky = new StringBuilder();
    if(evenOrOdd.equals("even")) {
      for (int i = 0; i< currentString.length(); i++){
        if((i+1)%2==0)
          wacky.append(currentString.charAt(i));
      }
    }
    else if(evenOrOdd.equals("odd")) {
      for (int i = 0; i < currentString.length(); i++) {
        if ((i + 1) % 2 == 1)
          wacky.append(currentString.charAt(i));
        
      }
    }
    else
      throw new IllegalArgumentException("Not 'even' or 'odd'");
    return wacky.toString();
  }
  
  /**
   * Returns the number of characters that are digits in the current string
   * 	 if two (and no more than two) of the same digit appear side by side.
   * @return an int Number of double-digits in the current string
   */
  @Override
  public int countDoubleDigits() {
    int count = 0;
    for (int i = 0; i < currentString.length() - 1; i++) {
      if (Character.isDigit(currentString.charAt(i)) && (currentString.charAt(i + 1) == currentString.charAt(i))) {
        try {
          if (currentString.charAt(i - 1) != currentString.charAt(i) && currentString.charAt(i + 2) != currentString.charAt(i)) {
            count++;
          }
        } catch (IndexOutOfBoundsException e) {
          try {
            if (currentString.charAt(i - 1) != currentString.charAt(i))
              count++;
          } catch (IndexOutOfBoundsException r) {
            if (currentString.charAt(i + 2) != currentString.charAt(i)) {
              count++;
            }
          }
          
        }
        
      }
    }
    return count;
  }
  
  /**
   * Returns true if the current string contains one or more characters
   * 	 before an '@' character, followed by either "vcu.edu" or "mymail.vcu.edu"
   *For example, RodneyTheRam@vcu.edu a valid vcu email address also
   * 	 and RamFan@gmail.com is not.
   * @return true if current string is formatted as valid VCU email address
   * 	        Returns false otherwise.
   */
  @Override
  public boolean isValidVCUEmail() {
    if(currentString.indexOf('@')==0)
      return false;
    else return (currentString.contains("@vcu.edu")) || (currentString.contains("@mymail.vcu.edu"));
    
  }
  
  /**
   * Extracts a phone number (with area code) from this String and returns it
   * 	 in a standard format with the area code in parenthesis followed by a
   * 	 then a dash separating the third and fourth digits "(000) 000-0000"
   * 	 space if this string does not contain exactly 10 digits, returns the
   * 	 message "This WackyString is not a phone number."
   * 	 Note: any characters that are not digits should not be included
   * 	   in the returned string.
   * 	   For example: "Office phone: 804-828-7135" --> "(804) 828-7135"
   * @return String containing the formatted phone number
   */
  @Override
  public String standardizePhoneNumber() {
    StringBuilder wacky = new StringBuilder();
    for (int i = 0; i< currentString.length(); i++) {
      if (Character.isDigit(currentString.charAt(i)))
        wacky.append(currentString.charAt(i));
    }
    if(wacky.length()!=10)
      return("This WackyString is not a phone number.");
    else
      return ("(" + wacky.substring(0, 3) + ") " + wacky.substring(3, 6) + "-" + wacky.substring(6, 10));
      
    
  }
  
  /**
   * Replace all occurrences of a single zero (0) with the string "Go Rams"
   *     in the current string,
   *     and all occurrences of a double zero (00) with the string "CS@VCU"
   */
  @Override
  public void ramifyString() {
    StringBuilder wacky = new StringBuilder("");
  
    for(int i = 0; i < currentString.length(); i++) {
      try{
        if(currentString.substring(i, i + 1).equals("0") && !(currentString.substring(i, i + 2).equals("00")) ) {
          wacky.append(currentString.substring(0, i) + "Go Rams" + currentString.substring((i + 1), currentString.length()));
        }
        else if(currentString.substring(i, i + 2).equals("00")&& !(currentString.charAt(i+3) =='0'))
          wacky.append(currentString.substring(0, i) + "CS@VCU" + currentString.substring((i + 2), currentString.length()));
      }
      catch(IndexOutOfBoundsException e){
        wacky.append(currentString.charAt(i));
      }
    
    }
  
    
  
  }
  
  /**
   * Replace the _individual_ digits in the current string, between
   * 	 startPosition and endPosition (included), with the corresponding
   * 	 Roman numeral symbol(s). The first character in the string is
   * 	 considered to be in Position 1. Digits are converted individually,
   * 	 even if contiguous, and digit "0" is not converted (e.g., 460 is
   * 	 converted to IVVI0).
   *
   * @param startPosition  Position of the first character to consider
   * @param endPosition    Position of the last character to consider
   * @throws MyIndexOutOfBoundsException
   *               If either "startPosition" or "endPosition" are out of
   * 	             bounds (i.e., either less than 1 or greater than the
   * 	             length of the string)
   * @throws IllegalArgumentException
   *               If "startPosition" > "endPosition" (but both are
   * 	             within bounds)
   */
  @Override
  public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws MyIndexOutOfBoundsException, IllegalArgumentException{
    StringBuilder wacky = new StringBuilder();
    
    String[] numerals = {"0","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    if(startPosition < 1 || endPosition > currentString.length())
      throw new MyIndexOutOfBoundsException();
    if(startPosition > endPosition)
      throw new IllegalArgumentException();
    
    for(int i = startPosition-1; i < endPosition; i++){
      
      if(currentString.charAt(i)=='1')
        wacky.append(numerals[1]);
      else if(currentString.charAt(i)=='2')
        wacky.append(numerals[2]);
      else if(currentString.charAt(i)=='3')
        wacky.append(numerals[3]);
      else if(currentString.charAt(i)=='4')
        wacky.append(numerals[4]);
      else if(currentString.charAt(i)=='5')
        wacky.append(numerals[5]);
      else if(currentString.charAt(i)=='6')
        wacky.append(numerals[6]);
      else if(currentString.charAt(i)=='7')
        wacky.append(numerals[7]);
      else if(currentString.charAt(i)=='8')
        wacky.append(numerals[8]);
      else if(currentString.charAt(i)=='9')
        wacky.append(numerals[9]);
      else
        wacky.append(currentString.charAt(i));
    }
    currentString = wacky.toString();
  
  
  }
}

