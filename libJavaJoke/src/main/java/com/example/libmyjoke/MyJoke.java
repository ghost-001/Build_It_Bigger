package com.example.libmyjoke;

import java.util.Random;


public class MyJoke {
    public String getJoke() {
        final int min = 0;
        final int max = 10;
        final int randomIndex = new Random().nextInt(max - min) + min;

        switch (randomIndex) {
            case 0:
                return "You can't just use one language for all platforms." + "\n" + "JavaScript: that's where you're wrong kiddo";
            case 1:
                return "Android: where ProgressBars go around in circles and Spinners do not spin";
            case 2:
                return "A programmer had a problem." + "\n" + "He decided to use Java." + "\n" + "He now has a ProblemFactory";
            case 3:
                return "Programmer" + "\n" + "A person who fixed a problem that you don't know you have, in a way you don't understand.";
            case 4:
                return "What's the Object-oriented way to become wealthy ?" + "\n" + "Inheritance";
            case 5:
                return "Why did the programmer quit his job ?" + "\n" + "Because he didn't get arrays. (a raise )";

            case 6:
                return "0 is false and 1 is true, right?" + "\n" + "1";
            case 7:
                return "C++ to C : You have no class.";
            case 8:
                return "Why do Java programmers have to wear glasses" + "\n" + "Because they don't C#";
            case 9:
                return "HTML is a programming language !!";

            default:
                return "You can't just use one language for all platforms." + "\n" + "JavaScript: that's where you're wrong kiddo";
        }


    }
}

