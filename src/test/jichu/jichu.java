public class jichu {

    public static void main(String[] args) {

        String str = new String("aaa");
        String str1 = new String("hello");
        String str2 = new String("hello2");


        str1 = str;
        str2 = str;

        System.out.println(str1==str2);
        System.out.println(str1.equals(str2));

    }
}
