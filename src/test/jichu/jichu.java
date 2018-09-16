import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class jichu {
        static class A{
            static{
                System.out.print("1");
            }
            public A(){
                System.out.print("2");
            }
        }
        static class B extends A{
            static{
                System.out.print("a");
            }
            public B(){
                System.out.print("b");
            }
        }
        //何问起 hovertree.com
        public static class Hello{
            public static void main(String[] args){
                A ab = new B();
                ab = new B();
            }

        }

    }

