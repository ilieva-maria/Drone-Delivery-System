package com.hackbulgaria.ddsystem;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		RequestHandler handler = new RequestHandler();
		handler.handle();
	}
}
