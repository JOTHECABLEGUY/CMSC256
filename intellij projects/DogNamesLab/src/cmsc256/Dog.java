package cmsc256;
public class Dog {
  private String dogName;
  private String count;

  Dog(String name, String c) {
    this.dogName = name.toUpperCase();
    this.count = c;
  }
  public String getName(){
    return this.dogName;
  }
  public String getCount(){
    return this.count;
  }

}
