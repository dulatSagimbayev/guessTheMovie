package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file =new File("movies.txt");
        Scanner scanner=new Scanner(file);
        //the number of films
        int films=0;
        ArrayList<String> movies=new ArrayList<String>();
        while(scanner.hasNextLine()){
            String line=scanner.nextLine();
            movies.add(line);
            films+=line.split(" " + " ").length;
        }

        String movieToGuess=movies.get(getRandomNumber(films));

        char [] boxOfSymbols=getSymbols(movieToGuess);
        for(int i=0;i<boxOfSymbols.length;i++){
            System.out.print(boxOfSymbols[i]+" ");
        }
        int j=10;
        ArrayList<Character> attempts = new ArrayList<Character>();
        while(j>0){
            int counter=0;
            System.out.println("");
            System.out.println("Please try your symbol");
            char myChoice=guess();
            int Integer=movieToGuess.indexOf(myChoice);
            //System.out.println(Integer);
            ArrayList<Integer> indexes=giveIndexes(movieToGuess,myChoice);

            if(Integer==-1){
                System.out.println("No! " +(j-1)+" attempts left");
                j--;
                attempts.add(myChoice);
            }

            else {
                for (int i = 0; i < indexes.size(); i++) {
                    boxOfSymbols[indexes.get(i)] = myChoice;

                }

            }
            for (int numberOfSymbols = 0; numberOfSymbols < boxOfSymbols.length; numberOfSymbols++) {
                if (boxOfSymbols[numberOfSymbols] == '_') {
                    counter = 10;
                }
            }
            for(int i=0;i<boxOfSymbols.length;i++){
                System.out.print(boxOfSymbols[i]+" ");

            }
            System.out.print("Wrong attempts: ");
            for(int a=0;a<attempts.size();a++){
                System.out.print(attempts.get(a)+" ");
            }
            if(counter!=10){
                System.out.println("");
                System.out.println("Congratulations, you win !!!");
                break;
            }

        }

    }
    public static int getRandomNumber(int someNumbers){
        //creating a random double number from 0-1
        double rand=Math.random();
        //give some bounds
        rand=rand*someNumbers;
        //convert the double to the int
        int randomInt=(int) rand;
        return randomInt;
    }

    //array of symbols "_"
    public static char[] getSymbols(String someString){
        char [] symbols=new char[someString.length()];
        for(int i=0;i<symbols.length;i++){
            if(someString.charAt(i)==' '){
                symbols[i]=' ';
            }
            else
                symbols[i]='_';
        }
        return symbols;

    }


    //to input your symbol
    public static char guess(){
        Scanner lineScan=new Scanner(System.in);
        char yourSymbol=lineScan.next().charAt(0);
        return yourSymbol;
    }


    //catch indexes of symbols, you guessed
    public static ArrayList<Integer> giveIndexes(String movie, char yourSymbol){
        ArrayList<Integer> indexes=new ArrayList<Integer>();
        for(int i=0;i<movie.length();i++){
            if(movie.charAt(i)==yourSymbol){
                indexes.add(i);
            }
        }
        return indexes;
    }

}
