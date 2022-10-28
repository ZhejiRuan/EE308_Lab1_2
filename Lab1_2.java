package Lab1_2;

import java.io.BufferedReader;   
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab1_2 {
	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);

	    //Read C file from file address
	    System.out.println("Please input the path of the code file");
	    //String fileName = "D:\\Lab\\lab1_2\\sample.c";
	    String fileName = scanner.nextLine();

	  //Read the file
	    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
	    String Cfilepath = "";
	    String line = bufferedReader.readLine();
	    while (line != null) {
	        Cfilepath += line;
	        line = bufferedReader.readLine();
	    }
	    
	    //Completion Level: You can enter 4
	    System.out.println("Please input the completion level (from low to high as 1, 2, 3, 4 )");
	    int level = scanner.nextInt();

	    //Judge requirements
	    switch (level) {
	        case 1:
	        	findKey(Cfilepath);
	        	break;
	        case 2:
	            findKey(Cfilepath);
	            findSwitchAndCase(Cfilepath);
	            break;
	        case 3:
	             findKey(Cfilepath);
	             findSwitchAndCase(Cfilepath);
	             processElse(Cfilepath,3);
	        case 4:
	             findKey(Cfilepath);
	             findSwitchAndCase(Cfilepath);
	             processElse(Cfilepath,4);
	             break;
	     }
	  }
	  //level 1
	  public static void findKey(String s) {
	        String keywords = "char,short,int,long,signed,unsigned,float,double,struct,union,enum,void,"
	                + "for,do,while,break,continue,if,else,goto,switch,case,default,return,"
	                + "auto,extern,register,static,typedef,const,sizeof,volatile";
	        Pattern p1 = Pattern.compile(",");
	        String[] cKeyword = p1.split(keywords);
	        int totalNum = 0;
	        for (String oneKey : cKeyword) {
	            Pattern p = Pattern.compile(oneKey + "[^a-z]");
	            Matcher matcher = p.matcher(s);
	            while (matcher.find()) {
	                totalNum++;
	            }
	        }
	        System.out.println("total num: " + totalNum);
	    }
	    
	    public static void findSwitchAndCase(String s) {
	        Pattern p1 = Pattern.compile("switch");
	        String[] switchs = p1.split(s);
	        int switchNum = switchs.length - 1;
	        System.out.println("switch num: " + switchNum);

	        System.out.print("case num:");
	        for (int i = 1; i < switchs.length; i++) {
	            Pattern p2 = Pattern.compile("case");
	            String[] cases = p2.split(switchs[i]);
	            int caseNum = cases.length - 1;
	            System.out.print(" " + caseNum);
	        }
	        System.out.println("");
	    }


	public static void processElse(String code,int level) {
		int ifelNum = 0;
        	int esifNum = 0;
        	boolean lock = true;
        	Stack stack = new Stack();
        	Pattern pattern = Pattern.compile("else if|if|else");
        	Matcher matcher = pattern.matcher(code);
        	while (matcher.find()) {
            		String temp = matcher.group();
            		if ("if".equals(temp)) {
                		stack.push(temp);
            		} else if ("else if".equals(temp)) {
                		stack.push(temp);
            		} else if ("else".equals(temp)) {
                		if ("if".equals(stack.peek())) {
                	    	stack.pop();
                	    	ifelNum++;
                		} else {
                	   		while (!"if".equals(stack.peek())) {
                        			stack.pop();
                        			if (lock == true) {
                        	    		esifNum++;
                        	    		lock = false;
                        			}
                    			}
                    			stack.pop();
                    			lock = true;
                		}
            		}	
        	}
        	if (level == 3) {
        		System.out.println("if-else num: " + ifelNum);
        	} else if (level == 4) {
        		System.out.println("if-else num: " + ifelNum);
            		System.out.println("if-elseif-else: " + esifNum);
        	} else {
        	}
    	}
}
