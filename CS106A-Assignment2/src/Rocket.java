// Problem 4

import acm.program.*;

public class Rocket extends ConsoleProgram {
	public static final int SIZE = 5;
	public void run() {
		println("CS 106A Rocket\n(size " + SIZE + ")");
		printHead();
		printBody();
		printHead();
	}
	public void printBody() {
		printEdge();
		// up tri
		for (int i = 0; i < SIZE; i+= 1) {
			print("|");
			for (int j = 0; j < SIZE-1-i; j += 1) {
				print(".");
			}
			for (int j = 0; j < i+1; j += 1) {
				print("/\\");
			}
			for (int j = 0; j < SIZE-1-i; j += 1) {
				print(".");
			}
			println("|");
		}

		// down tri
		for (int i = SIZE-1; i >= 0; i-= 1) {
			print("|");
			for (int j = 0; j < SIZE-1-i; j += 1) {
				print(".");
			}
			for (int j = 0; j < i+1; j += 1) {
				print("\\/");
			}
			for (int j = 0; j < SIZE-1-i; j += 1) {
				print(".");
			}
			println("|");
		}

		printEdge();
	}

	public void printEdge() {
		print("+");
		for (int i = 0; i < 2*SIZE; i += 1) {
			print("=");
		}
		println("+");
	}
	public void printHead() {
		for (int i = 0; i < SIZE; i += 1) {
			// print space
			for (int j = 0; j < SIZE-i; j += 1) {
				print(" ");
			}
			// print  /
			for (int j = 0; j < i+1; j += 1) {
				print("/");
			}
			// print \
			for (int j = 0; j < i+1; j += 1) {
				print("\\");
			}
			println();
		}
	}
}
