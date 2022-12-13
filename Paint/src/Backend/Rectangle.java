package Backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import static java.lang.Math.abs;

public class Rectangle extends AbstractShapeClass {

    private double width, height;
    private int xdiff, ydiff;
    final int SIZE = 8;
    Rectangle2D[] points =  new Rectangle2D.Double[4];

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Rectangle(Point position, double width, double height) {
        super(position);
        this.width = width;
        this.height = height;
         this.setShapeName("Rectangle_");
        points[0] = new Rectangle2D.Double(this.getPosition().x,this.getPosition().y,SIZE, SIZE); 
        points[1] = new Rectangle2D.Double(this.getPosition().x,this.getPosition().y +this.getHeight(),SIZE, SIZE);
        points[2] = new Rectangle2D.Double(this.getPosition().x-8 + this.getWidth(),this.getPosition().y,SIZE, SIZE);
        points[3] = new Rectangle2D.Double(this.getPosition().x + this.getWidth(),this.getPosition().y + this.getHeight(),SIZE, SIZE);
    }

    @Override
    public void moveTo(Point point) {
        this.setPosition(new Point((int) point.getX() + xdiff, (int) point.getY() + ydiff));
    }
    
     public void pointPressed(Point point, Graphics g){
             points[0] = new Rectangle2D.Double(this.getPosition().x,this.getPosition().y,SIZE, SIZE); 
             points[1] = new Rectangle2D.Double(this.getPosition().x,this.getPosition().y +this.getHeight(),SIZE, SIZE);
             points[2] = new Rectangle2D.Double(this.getPosition().x + this.getWidth(),this.getPosition().y,SIZE, SIZE);
             points[3] = new Rectangle2D.Double(this.getPosition().x + this.getWidth(),this.getPosition().y + this.getHeight(),SIZE, SIZE);
             Graphics2D g2 = (Graphics2D) g;
             for (int i = 0; i < points.length; i++) {
              if (points[i]!= null){   
              g2.fill(points[i]);
    }
             }
    }
    @Override
    public void setDraggingPoint(Point point) {
        super.setDraggingPoint(point);
        xdiff = (int) getPosition().getX() - (int) point.getX();
        ydiff = (int) getPosition().getY() - (int) point.getY();
    }

    @Override
    public boolean contains(Point point) {
        if ((int) point.getX() > (int) getPosition().getX() && (int) point.getX() < ((int) getPosition().getX() + width)
                && (int) point.getY() > (int) getPosition().getY() && (int) point.getY() < ((int) getPosition().getY() + height)) {
            return true;
        }
        return false;
    }
    int index =-1;
        @Override
    public boolean readyToMove(Point point) {
      for (int i = 0; i < points.length; i++) {
              if (points[i].contains(point.x, point.y)){
                  index =i;
                  return true;
              }              
    }
      return false;
    }

    @Override
    public void draw(java.awt.Graphics canvas) {
        Color oldColor = canvas.getColor();
        canvas.setColor(super.getFillColor());
        canvas.fillRect((int) this.getPosition().getX(), (int) this.getPosition().getY(), (int) this.getWidth(), (int) this.getHeight());
        canvas.setColor(super.getColor());
        canvas.drawRect((int) this.getPosition().getX(), (int) this.getPosition().getY(), (int) this.getWidth(), (int) this.getHeight());
        canvas.setColor(oldColor);
    }

    public void moveRec (Point point){
        if(index !=-1){
        points[index] = new Rectangle2D.Double(point.getX(),point.getY(),SIZE,SIZE);
    }
    }
   @Override
    public void Resize(Point point) {
       if (index ==0) // top left corner
       {
           width = (int) width + (int) getPosition().x - point.x;
           height = (int) height + (int) getPosition().y - point.y;
           if(width>0 && height >0){
            setPosition(point);
           }else setPosition(fixPoints(point));
       }
       else if (index ==1) // bottom left corner
       {
           width = (int) width + (int) getPosition().x - point.x;
           height = point.y - (int) getPosition().y;
           if(width>0 && height >0){
            setPosition(new Point (point.x, getPosition().y));
           }else setPosition(fixPoints(point));
       }
       
       else if (index ==2)//top right corner
       {
           width =  (int)point.x - getPosition().x;
           height = (int) height + (int) getPosition().y - point.y;
           if (width>0 && height >0){
           setPosition(new Point (getPosition().x,point.y));
       } else {
               setPosition(fixPoints(point));              
           }
       }
       else if (index==3)// bottom right corner
       {
           width =  (int)point.x - getPosition().x;
           height = point.y - (int) getPosition().y;
            if (width>0 && height >0){
           setPosition(new Point (getPosition().x,getPosition().y));
       } else {
               setPosition(fixPoints(point));              
           }
       }
    }
    public Point fixPoints(Point point){
        Point newPoint = point;
        int x0= (int)points[0].getX(), x2 = (int)points[2].getX();
        int x1=x0, x3 = x2;
        int y0 =(int)points[0].getY(), y1 = (int)points[1].getY();
        int y2 = y0, y3 =y1;
        if (x0>x2 && y0< y1 && index ==0 ) {
            newPoint.x = x2; newPoint.y = y0;
            
        } else if (x0 < x2 && y0 > y1 && index ==0) {
           newPoint.x = x0; newPoint.y = y1;
        } else if (x0 < x2 && y0 > y2) {
            newPoint.x = x0; newPoint.y = y1;
        } else if (x0 > x2 && index ==2) {
            newPoint.x = x2; newPoint.y = y0;
        }else {
            width = abs(width);
            height = abs(height);
            return getPosition();
        
        }width = abs(width);
        height = abs(height);
        return newPoint;
    }
}
