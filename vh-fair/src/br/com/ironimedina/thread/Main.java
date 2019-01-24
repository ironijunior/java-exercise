package br.com.ironimedina.thread;

public class Main {

	public static void main(String args[]) {
		RunnableDemo R1 = new RunnableDemo("Thread-1");
		R1.start();

		RunnableDemo R2 = new RunnableDemo("Thread-2");
		R2.start();
		
		System.out.println(new Logger().getContent());
		Double d = new Double("1234567890123456789012345");
	}
}
