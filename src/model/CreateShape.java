package model;

import model.interfaces.IShape;

public class CreateShape {

    static Point start;
    static Point end;

    public CreateShape (Point s, Point e){
        start = s;
        end = e;
    }
    public static IShape run(String type){
                return new Shape(start, end, type);
        }
}

