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
    };

    public static String[][] choices ={
            {"Bjarne Stroustrup", "Elon Musk","Steve Jobs","Bill Gates"},
            {"Java","C++","C","C#"},
            {"bubble sort", "insertion sort","merge sort","Selection Sort"}
    };
    public static void correctAnswers(){
            correct.add("Bjarne Stroustrup");
            correct.add("C");
            correct.add("merge sort");
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