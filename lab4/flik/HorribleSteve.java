package flik;

public class HorribleSteve {
    public static void main(String [] args) throws Exception {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                throw new Exception(
                        String.format("i:%d not same as j:%d ??", i, j));
            }
        }
        Integer x = 128;
        System.out.println(x.hashCode());
        Integer y = 128;
        System.out.println(y.hashCode());
        if (x.equals(y)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
        System.out.println("i is " + i);
    }
}
