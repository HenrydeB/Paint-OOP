package model.interfaces;

import java.util.List;

public interface ISingletonLists {
     void addToList(IShape shape, List<IShape> target);
     void addSetToList(List<IShape> set, List<IShape> target);
     void clearList(List<IShape> list);

}
