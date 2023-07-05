package model;

import model.interfaces.IShape;

public class CreateShape {


    public CreateShape (){
    }
    public static IShape run(String type, Point start){
                return new Shape(type, start);
        }
}

