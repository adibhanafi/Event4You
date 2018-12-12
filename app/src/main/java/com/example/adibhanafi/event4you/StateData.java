package com.example.adibhanafi.event4you;

public class StateData {

    public String nameState;

    public StateData(String nameState)
    {
        this.nameState = nameState;
    }

    public StateData(){

    }

    public void setNameState(String nameState){
        this.nameState = nameState;
    }

    public String getNameState(){
        return nameState;
    }
}
