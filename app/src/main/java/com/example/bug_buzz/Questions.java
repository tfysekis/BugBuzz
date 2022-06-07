package com.example.bug_buzz;

import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Collections;
        import java.util.List;
        import java.util.Random;

public class Questions {

    public static ArrayList<String> questions = new ArrayList<>();
    public static ArrayList<String> correct = new ArrayList<>();

    public static void initializeQuestions(){
        questions.add("Who created C++?");
        questions.add("Which programming language from the above is NOT object oriented?");
        questions.add("Which sort algorithm has the least complexity");
        questions.add("_______ is the process of finding errors and fixing them within a program.");
        questions.add("Which command will stop an infinite loop?");
        questions.add("Kim has just constructed her first for loop within the Java language.  Which of the following is not a required part of a for loop?");
        questions.add("A loop that never ends is referred to as a(n)_________.");
        questions.add("Which of the following is the correct way to use the standard namespace in C++?");
        questions.add("What does HTML stand for?");
    };

    public static String[][] choices ={
            {"Bjarne Stroustrup", "Elon Musk","Steve Jobs","Bill Gates"},
            {"Java","C++","C","C#"},
            {"bubble sort", "insertion sort","merge sort","Selection Sort"},
            {"Compiling","Executing","Debugging","Scanning"},
            {"Alt - C","Shift - C","Esc", "Ctrl - C"},
            {"Initialization","Condition","Variable","increment"},
            {"While loop","Infinite loop","Recursive loop"," for loop"},
            {"Using namespace std;","Using namespace standard;","Using standard namespace;","Standard namespace used;"},
            {"Hyper Trainer Marking Language","Hyper Text Marketing Language","Hyper Text Markup Language","Hyper Text Markup Leveler"}

    };
    public static void correctAnswers(){
            correct.add("Bjarne Stroustrup");
            correct.add("C");
            correct.add("merge sort");
            correct.add("Debugging");
            correct.add("Ctrl - C");
            correct.add("Variable");
            correct.add("Infinite loop");
            correct.add("Using namespace std;");
            correct.add("Hyper Text Markup Language");
    };

    public static void shuffleEverything(){
        List<String[]> choicesList = Arrays.asList(choices);
        long seed = System.nanoTime();
        Collections.shuffle(questions, new Random(seed));
        Collections.shuffle(correct, new Random(seed));
        Collections.shuffle(choicesList,new Random(seed));
        choices = choicesList.toArray(new String[0][0]);
    }
}