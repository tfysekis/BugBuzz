package com.example.bug_buzz;

public class Questions {

    private int answerID;


    public static String questions[]={
            "Who created C++?",
            "Which programming language from the above is NOT object oriented?",
            "Which sort algorithm has the least complexity"
    };
    public static String choices[][]={
            {"Bjarne Stroustrup", "Elon Musk","Steve Jobs","Bill Gates"},
            {"Java","C++","C","C#"},
            {"bubble sort", "insertion sort","merge sort","Selection Sort"}
    };
    public static String correctAnswers[]={
            "Bjarne Stroustrup",
            "C",
            "merge sort"
    };

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getChoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getChoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getChoice3(int a){
        String choice = choices[a][2];
        return choice;
    }

    public String getChoice4(int a){
        String choice = choices[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = correctAnswers[a];
        return answer;
    }
}
