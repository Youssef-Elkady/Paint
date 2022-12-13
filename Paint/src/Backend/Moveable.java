
package Backend;

import java.awt.Point;

public interface Moveable {
    public void setDraggingPoint(Point point);
    public Point getDraggingPoint();
    public boolean contains(Point point);
    public void moveTo(Point point);
   
}
