/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author taver
 */
public interface Resizable {
    public void Resize(Point point);
    public void pointPressed (Point point, Graphics g);
    public void setPressedPoint (Point point);
    public Point getPressedPoint ();
    public boolean readyToMove(Point point);
    public void moveRec (Point point);
    
}
