package com.MiniProject;

import java.io.*;
import java.util.Scanner;

public class StudentManagementSystem {
	public static final String FILE_NAME="Student.txt";
	
			
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		int choice;
		System.out.println("Welcome to Student Management System");
		do {
			System.out.println("1. Add Student");
			System.out.println("2. View All Students");
			System.out.println("3. Search Student by ID");
			System.out.println("4. Delete Student by ID");
			System.out.println("5. Exit");
			System.out.println("Choose below Options");
			 choice=Integer.parseInt(sc.nextLine());
			
		switch(choice) {
		case 1:addStudent(); 
			break;
		case 2: viewAllStudents();
			break;
		case 3: searchById();
			break;
		case 4: deleteById();
		
			break;
		case 5:System.out.println("You have choosen Exit");
			break;
		default: System.out.println("Enter Valid Choice");
			break;
		}
		
	}while(choice != 5);
		}

	
	private static void deleteById() throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter ID to delete");
		String id=sc.nextLine().trim();
		
		File file=new File(FILE_NAME);
		File dummy=new File("dummy.txt");
		boolean status=false;
		try(BufferedReader br=new BufferedReader(new FileReader(file));
				BufferedWriter bw=new BufferedWriter(new FileWriter(dummy,false))){
			String line;
			while((line=br.readLine())!=null) {
				if(!line.trim().startsWith(id + ",")) {
					bw.write(line);	
					bw.newLine();
				}else {
					status=true;
				}
			}
		}
			
			if(file.delete()) {
			if(!dummy.renameTo(file)) {
				System.out.println("Error renaming file");
			}
			}else {
				System.out.println("Error deleting original file");
			}
			System.out.println(status ? "Deleted Successfully" : "ID not found");
				}
	
		
		
	private static void searchById() throws IOException {
		File file=new File(FILE_NAME);
		System.out.println("Enter the ID to search:");
		Scanner sc=new Scanner(System.in);
		String id=sc.nextLine();
		BufferedReader br=new BufferedReader(new FileReader(file));
		String line;
		boolean status=false;
		while((line=br.readLine())!=null) {
			String [] s=line.split(",");
			if(id.equals(s[0])) {
				System.out.println("\nid\tname\tage\tgrade");
				System.out.println("-----------------------------");
			    System.out.println(s[0]+"\t" + s[1]+"\t" + s[2]+"\t"+s[3]);
			    System.out.println();
			    status=true;
			    break;
		}
		}
		if(!status) {
			System.err.println("ID is not present");
		}
	}
	
	
	private static void viewAllStudents() throws IOException {
		File file=new File(FILE_NAME);
		BufferedReader br=new BufferedReader(new FileReader(file));
		System.out.println("\nid\tname\tage\tgrade");
		System.out.println("-----------------------------");
		String line;
		while((line=br.readLine())!=null) {
			String [] s=line.split(",");
			System.out.println(s[0]+"\t" + s[1]+"\t" + s[2]+"\t"+s[3]);	
		}
		System.out.println("-------------------------------");
		br.close();	
		
	}


	private static void addStudent() throws IOException {
		File file=new File(FILE_NAME);
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your ID :");
		String id=sc.nextLine();
		System.out.println("Enter Your Name:");
		String name=sc.nextLine();
		System.out.println("Enter Your Age:");
		String age=sc.nextLine();
		System.out.println("Enter Grade: ");
		String grade=sc.nextLine();
		BufferedWriter bw=new BufferedWriter(new FileWriter(FILE_NAME,true));
		bw.write(id+","+name+","+age+","+grade);
		bw.newLine();
		bw.close();
	}

}

