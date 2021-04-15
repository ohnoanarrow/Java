import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Stack;
import java.util.Queue;
import java.util.Iterator;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Beginnings{
    static PrintWriter outFile;
    static Scanner inFile;

    public static void main(String[] args) throws IOException{
            String pattern = "(\".*?\")";
            Pattern r = Pattern.compile(pattern);
            String varDec = "if \\((.*?)\\)";
            Pattern v = Pattern.compile(varDec);

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the Java file you wish to convert: ");
        String javaCode = scan.next();
        outFile = new PrintWriter(new FileWriter("Output.cpp"));
        File file = new File(javaCode);
        Scanner fileScan = new Scanner(file);
        Scanner fileScan2 = new Scanner(file);
        Scanner fileScan3 = new Scanner(file);

        while (fileScan2.hasNextLine()){
            String printer = fileScan2.findInLine(".print");

            if(printer == null){

            }else {
                outFile.println("#include <iostream>");
                outFile.println("using namespace std;");
                break;
            }
            fileScan2.nextLine();
        }
        Matcher m, n;
        String printer, mainMethod, ifState, line;

        while (fileScan.hasNextLine())
        {
            mainMethod = fileScan.findInLine("void main");
            printer = fileScan.findInLine(".print");
            ifState = fileScan.findInLine("if ");
            //line = fileScan.nextLine();

            if(mainMethod == null){

            }else if (mainMethod.equals("void main")) {
                outFile.println("int main () {");
            } //if mainMethod

            if (ifState == null){
                n = v.matcher(fileScan.nextLine());
            } else if (ifState.equals("if ")) {
                n = v.matcher(fileScan.nextLine());
                if (n.find()){
                    outFile.println(n.group());
                    break;
                }//m find
            }

            if (printer == null){
                m = r.matcher(fileScan.nextLine());
            } else if (printer.equals(".print")) {
                m = r.matcher(fileScan.nextLine());
                if (m.find()){
                    outFile.println("cout << " + m.group() + ";");
                    break;
                }//m find
                fileScan.nextLine();
            }//if printer
        }
        /* while (fileScan.hasNextLine())
        {
            vDec = v.matcher(fileScan.nextLine());
            ifState = fileScan.findInLine(vDec.group());
                if (ifState == null){

                }else {

                    if (vDec.find()){
                        outFile.println(vDec.group());
                    }//v find
                }//if ifState
        }

*/
//regex expression is not working again, although I checked it was correct
//When above is not commented out, HelloWorld doesnt worke
        outFile.println("}");
        outFile.close();
    }
} // R.I.P Women of Code == null;
