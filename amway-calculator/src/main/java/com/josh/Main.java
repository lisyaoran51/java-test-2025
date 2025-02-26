package com.josh;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {

		var history = new DoubleLinkedList<Float>();
		Node<Float> node = null;

		while (true) {
			var s = new Scanner(System.in);
			System.out.println("請輸入第一個數字 如接續上個答案則輸入'.'，復原輸入undo，重做輸入redo");
			var num1 = 0f;
			var res = 0f;
			var input1 = s.next();
			switch (input1) {
				case "undo":
					if (node == null || node.prev == null) {
						System.out.println("沒有上個答案");
						continue;
					}
					node = node.prev;
					System.out.println("回復上個答案" + node.data);
					continue;
				case "redo":
					if (node == null || node.next == null) {
						System.out.println("沒有下個答案");
						continue;
					}
					node = node.next;
					System.out.println("回復下個答案" + node.data);
					continue;
				case ".":
					if (node == null) {
						num1 = 0;
					} else {
						num1 = node.data;
					}
					System.out.println("請輸入運算符號(+,-,*,/)");
					break;
				default:
					try {
						num1 = Float.parseFloat(input1);
					} catch (NumberFormatException e) {
						System.out.println("輸入錯誤");
						continue;
					}
					System.out.println("第一個數字為" + num1 + ", 請輸入運算符號(+,-,*,/)");
			}
			var operator = s.next();
			var matches = Pattern.matches("^[+\\-*/]$",operator);
			if (!matches) {
				System.out.println("輸入錯誤");
				continue;
			}
			System.out.println("運算符號為" + operator + ", 請輸入第二個數字");
			var num2 =s.nextFloat();
			System.out.println("第二個數字為" + num2);
			switch (operator) {
				case "+":
					res = num1+num2;
					break;
				case "-":
					res = num1-num2;
					break;
				case "*":
					res = num1*num2;
					break;
				case "/":
					if (num2 == 0) {
						System.out.println("不可除");
						continue;
					}
					res = num1/num2;
					break;
			}
			System.out.println("答案為" + res);

			if (history.head == null) {
				node = new Node<>(res);
				history.head = node;
			} else {
				node.next = new Node<>(res);
				node.next.prev = node;
				node = node.next;
			}

		}
	}
}