package br.com.ironimedina.thread;

public class Logger {

	private StringBuilder contents = new StringBuilder();
	
	public void log(String msg) {
		contents.append(System.currentTimeMillis());
		contents.append(": ");
		contents.append(Thread.currentThread().getName());
		contents.append(msg);
		contents.append("\n");
	}
	
	public String getContent() {
		return contents.toString();
	}
	
}
