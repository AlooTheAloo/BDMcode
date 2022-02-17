package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    String[] text = readFromText("File.bdm");
        System.out.println(convertToText(text));
    }

    public static String convertToText(String[] text){
        String retval = "";
        char[] boiteDeMouchoir = "boitedemouchoir".toCharArray();

        boolean space = false;
        boolean newLine = false;
        boolean maj = false;
        for(int i = 0; i < text.length; i++){
            if(text[i].equals("")){
                if(!space){
                    space = true;
                    continue;
                }
                else{
                    if(newLine){
                        System.out.println("Too many newlines at line " + i);
                        return "";
                    }
                    else{
                        newLine = true;
                        continue;
                    }
                }

            }


            for(int j = 0; j < text[i].length(); j++){
                if(text[i].charAt(j) != boiteDeMouchoir[j % boiteDeMouchoir.length]){
                    if(text[i].toLowerCase().charAt(j) != boiteDeMouchoir[j % boiteDeMouchoir.length]){
                        System.out.println("Unexpected character at line " + i + " at character " + j);
                        return "";
                    }
                    else maj = true;


                }

            }
            char toAdd = 0;
            if(!maj)
             toAdd = ((char) (text[i].length()) + "").toLowerCase().charAt(0);
            else{
                toAdd = ((char) (text[i].length()) + "").charAt(0);
                maj = false;
            }
            if(space){

                if(newLine){
                    space = false;
                    newLine = false;
                    retval += "\n";
                }
                else{
                    space = false;
                    retval += " ";
                }

            }
            retval += "" + toAdd;


        }
        return retval;
    }


    public static String[] readFromText(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        String retval = "";
        while(scanner.hasNextLine()){
            retval += scanner.nextLine() + "\n";
        }
        scanner.close();
        return retval.split("\n");
    }

}
