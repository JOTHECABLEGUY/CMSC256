package cmsc256;

import org.junit.Test;

import static org.junit.Assert.*;

public class RamStringTest {
  
  
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testSetWackyString() {
    RamString c = new RamString();
    c.setWackyString("fro");
    assertEquals("fro", c.getWackyString());
  
    c.setWackyString(null);
    
    c.setWackyString("");
    assertEquals("", c.getWackyString());
  
  }
  
  @Test
  public void testGetEveryThirdCharacter() {
    RamString s = new RamString();
    s.setWackyString("Rodney The Ram");
    assertEquals("dyhR", s.getEveryThirdCharacter());
    
    s.setWackyString("HEy{][");
    assertEquals("y[", s.getEveryThirdCharacter());
    
    s.setWackyString("H-------------------1");
    assertEquals("------1", s.getEveryThirdCharacter());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetEvenOrOddCharacters() {
    RamString v = new RamString();
    v.setWackyString("odd");
    assertEquals("od", v.getEvenOrOddCharacters(v.getWackyString()));
    
    v.setWackyString("even");
    assertEquals("vn", v.getEvenOrOddCharacters(v.getWackyString()));
    
    v.setWackyString("huh");
    v.getEvenOrOddCharacters(v.getWackyString());
    
  }
  
  @Test
  public void testCountDoubleDigits() {
    RamString b = new RamString();
    b.setWackyString("222222222");
    assertEquals(0, b.countDoubleDigits());
    
    b.setWackyString("22 22 22");
    assertEquals(3, b.countDoubleDigits());
    
    b.setWackyString("222 2 2223 43 5");
    assertEquals(0, b.countDoubleDigits());
  }
  
  @Test
  public void testIsValidVCUEmail() {
    RamString n = new RamString();
    n.setWackyString("not a valid email");
    assertTrue(!n.isValidVCUEmail());
    
    n.setWackyString("RodneyRam@mymail.vcu.edu");
    assertTrue(n.isValidVCUEmail());
    
    n.setWackyString("friday@vcu.edu");
    assertTrue(n.isValidVCUEmail());
    
    n.setWackyString("hellovcu.edu");
    assertTrue(!n.isValidVCUEmail());
  }
  
  @Test
  public void testStandardizePhoneNumber() {
    RamString x = new RamString();
    x.setWackyString("2222222222");
    assertEquals("(222) 222-2222", x.standardizePhoneNumber());
  
    x.setWackyString("2d2d2d2d2d2d2d2d2d2d");
    assertEquals("(222) 222-2222", x.standardizePhoneNumber());
  
    x.setWackyString("22222222222");
    assertEquals("This WackyString is not a phone number." , x.standardizePhoneNumber());
  
    x.setWackyString("22222222");
    assertEquals("This WackyString is not a phone number.", x.standardizePhoneNumber());
  }
  
  @Test
  public void testRamifyString() {
    RamString z = new RamString();
    z.setWackyString("0");
    z.ramifyString();
    assertEquals("Go Rams", z.getWackyString());
  
    z.setWackyString("00");
    z.ramifyString();
    assertEquals("CS@VCU", z.getWackyString());
    
    z.setWackyString("000");
    z.ramifyString();
    assertEquals("000", z.getWackyString());
  
    z.setWackyString("0 00 000");
    z.ramifyString();
    assertEquals("Go Rams CS@VCU 000", z.getWackyString());
  }
  
  @Test
  public void testConvertDigitsToRomanNumeralsInSubstring() {
    RamString a = new RamString();
    a.setWackyString("gr34t");
    a.convertDigitsToRomanNumeralsInSubstring(1, 4);
    assertEquals("grIIIIV", a.getWackyString());
  
    try{
      a.setWackyString("gr34t");
      a.convertDigitsToRomanNumeralsInSubstring(4, 1);
      fail();
    }
    catch(Exception e){
      assert(e instanceof IllegalArgumentException);
    }
    
  
    try{
      a.setWackyString("gr34t");
      a.convertDigitsToRomanNumeralsInSubstring(0, 4);
      fail();
    }
    catch(Exception e){
      assert(e instanceof MyIndexOutOfBoundsException);
    }
  
    a.setWackyString("gr3 [4]t");
    a.convertDigitsToRomanNumeralsInSubstring(1, 6);
    assertEquals("grIII [IV", a.getWackyString());
    
    a.setWackyString("5");
    a.convertDigitsToRomanNumeralsInSubstring(1,1);
    assertEquals("V", a.getWackyString());
  
  
  }
}