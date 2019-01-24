package br.com.ironimedina.dostuff;
import java.util.ArrayList;
import java.util.List;

public class HelloWorld implements Pancake {

     public static void main(String []args){
        List<String> x = new ArrayList<String>();
        x.add("3"); x.add("7"); x.add("5");
        List<String> y = new HelloWorld().doStuff(x);
        y.add("1");
        System.out.println(x);
     }
     
     List<String> doStuff(List<String> s) {
         s.add("9");
         return s;
     }
}

interface Pancake {
	List<String> doStuff(List<String> s);
}