package Backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import javax.swing.text.Position;

public class Triangle extends AbstractShapeClass {

    private Point p2, p3;
    Rectangle2D[] points =  new Rectangle2D.Double[4];
    private int x1diff, y1diff, x2diff, y2diff, x3diff, y3diff;
    final int SIZE =8;
    public Triangle(Point position, Point p2, Point p3) {
        super(position);
        this.p2 = p2;
        this.p3 = p3;
         this.setShapeName("Triangle_");
        points[0] = new Rectangle2D.Double(this.getPosition().x,this.getPosition().y,SIZE, SIZE); 
        points[1] = new Rectangle2D.Double(this.getP2().x-4,this.getP2().y-4,SIZE, SIZE);
        points[2] = new Rectangle2D.Double(this.getP3().x,this.getP3().y-4,SIZE, SIZE);
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    @Override
    public void draw(Graphics canvas) {
        Color oldColor = canvas.getColor();
        canvas.setColor(getColor());
        canvas.setColor(super.getFillColor());
        canvas.fillPolygon(new int[]{(int) getPosition().getX(), (int) getP2().getX(), (int) getP3().getX()}, new int[]{(int) getPosition().getY(), (int) getP2().getY(), (int) getP3().getY()}, 3);
        canvas.setColor(super.getColor());
        canvas.drawPolygon(new int[]{(int) getPosition().getX(), (int) getP2().getX(), (int) getP3().getX()}, new int[]{(int) getPosition().getY(), (int) getP2().getY(), (int) getP3().getY()}, 3);
        canvas.setColor(oldColor);

    }
    public void pointPressed(Point point, Graphics g){
             points[0] = new Rectangle2D.Double(this.getPosition().x,this.getPosition().y,SIZE, SIZE); 
             points[1] = new Rectangle2D.Double(this.getP2().x-4,this.getP2().y-4,SIZE, SIZE);
             points[2] = new Rectangle2D.Double(this.getP3().x,this.getP3().y-4,SIZE, SIZE);
             points[3] = null;
             Graphics2D g2 = (Graphics2D) g;
             for (int i = 0; i < points.length; i++) {
              if (points[i]!= null){   
              g2.fill(points[i]);
    }
             }
    }
    @Override
    public void setDraggingPoint(Point point) {
        super.setDraggingPoint(point);                  //point where mousePressed
        x1diff = (int) getPosition().getX() - (int) point.getX();
        y1diff = (int) getPosition().getY() - (int) point.getY();
        x2diff = (int) p2.getX() - (int) point.getX();
        y2diff = (int) p2.getY() - (int) point.getY();
        x3diff = (int) p3.getX() - (int) point.getX();
        y3diff = (int) p3.getY() - (int) point.getY();
        
    }
    int index =-1;
        @Override    
    public boolean readyToMove(Point point) {
      for (int i = 0; i < 3; i++) {
              if (points[i].contains(point.x, point.y)){
                  index = i;
                  return true;
              }              
    }
      return false;
    }
    public void moveRec (Point point){
        if(index !=-1){
        points[index] = new Rectangle2D.Double(point.getX(),point.getY(),SIZE,SIZE);
    }
    }
    
    @Override
    public void Resize(Point point) {
        moveRec(point);
        System.out.println("index is "+ index);
        switch (index) {
            case 0 -> this.setPosition(point);
            case 1 -> setP2(point);
            case 2 -> setP3(point);
            default -> {
            }
        }
    }

    
    @Override
    public void moveTo(Point point) {
        this.setPosition(new Point((int) point.getX() + x1diff, (int) point.getY() + y1diff));
        this.setP2(new Point((int) point.getX() + x2diff, (int) point.getY() + y2diff));
        this.setP3(new Point((int) point.getX() + x3diff, (int) point.getY() + y3diff));
    }

    private int area(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (int) Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }

    /* A function to check whether point P(x, y) lies
	inside the triangle formed by A(x1, y1),
	B(x2, y2) and C(x3, y3) */
    @Override
    public boolean contains(Point p) {
        /* Calculate area of triangle ABC */
        int A = area((int) getPosition().getX(), (int) getPosition().getY(), (int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY());

        /* Calculate area of triangle PBC */
        int A1 = area((int) p.getX(), (int) p.getY(), (int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY());

        /* Calculate area of triangle PAC */
        int A2 = area((int) p.getX(), (int) p.getY(), (int) getPosition().getX(), (int) getPosition().getY(), (int) p3.getX(), (int) p3.getY());

        /* Calculate area of triangle PAB */
        double A3 = area((int) p.getX(), (int) p.getY(), (int) getPosition().getX(), (int) getPosition().getY(), (int) p2.getX(), (int) p2.getY());

        /* Check if sum of A1, A2 and A3 is same as A */
        return (A == A1 + A2 + A3);
    }

    

}
