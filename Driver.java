public class Driver {
    public static void main(String [] args) {
   // System.out.println("smol bupper");
    Polynomial p = new Polynomial(new double[]{6,-2,5,3}, new int[]{0,1,3,11});
    Polynomial g = new Polynomial(new double[]{1,-1}, new int[]{2, 0});
    Polynomial one = new Polynomial(new double[]{1,2,4}, new int[]{1,2,4});
    Polynomial guh = p.add(g);
  //  System.out.println(g.evaluate(0));
   // System.out.println(guh.evaluate(0));
    Polynomial two = one.multiply(one);
   // two.PrintStuff();
    Polynomial pg = p.multiply(g);
  //  pg.PrintStuff();
    Polynomial text = new Polynomial("polytext.txt");
    text.saveToFile("polyout.txt");

    //text.PrintStuff();
    }
}