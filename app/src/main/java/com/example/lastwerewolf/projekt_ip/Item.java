package com.example.lastwerewolf.projekt_ip;

public class Item {


        String answer1;
        int question1;

        public Item(String answer1, int question1){
            this.question1= question1;
            this.answer1=answer1;

        }

    public String getName() {
        return answer1;
    }
    public int getImage() { return question1;}

}

