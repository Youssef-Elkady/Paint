package Backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import static java.lang.Math.abs;

public class LineSegment extends AbstractShapeClass {
    private int x1diff,y1diff,x2diff,y2diff;
    private Point p2;
    Rectangle2D[] points =  new Rectangle2D.Double[4];
    final int SIZE = 8;

    public LineSegment(Point p1, Point p2) {
        super(p1);
        this.p2=p2;
        this.setShapeName("Line_");
         points[0] = new Rectangle2D.Double(this.getPosition().x,this.getPosition().y,SIZE, SIZE); 
         points[1] = new Rectangle2D.Double(this.getP2().x,this.getP2().y,SIZE, SIZE);
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    @Override
    public void setDraggingPoint(Point point) {
        super.setDraggingPoint(point); 
        x1diff = (int) getPosition().getX() - (int) point.getX();
        y1diff = (int) getPosition().getY() - (int) point.getY();
        x2diff=(int)getP2().getX()-(int)point.getX();
        y2diff=(int)getP2().getY()-(int)point.getY();
    }

    @Override
    public void moveTo(Point point) {
        this.setPosition(new Point((int) point.getX() + x1diff, (int) point.getY() + y1diff));
         this.setP2(new Point((int) point.getX() + x2diff, (int) point.getY() + y2diff));
    }
    
      @Override
    public boolean contains(Point point){
       
        int dxc = (int)point.getX() - (int)getPosition().getX();
        int dyc = (int)point.getY() - (int)getPosition().getY();
        int dxl = (int) p2.getX() - (int)getPosition().getX();
        int dyl = (int) p2.getY() - (int)getPosition().getY();
        int cross = dxc * dyl - dyc * dxl;
        if(cross !=0){
            return false;
        }
        if (dxl >0){
         if((int)getPosition().getX() <= (int)point.getX() && (int)point.getX()  <= (int) p2.getX()){
             return true;
         }
         // check vertical line
        }else if(dxl==0){
            if(dyl>0){
                return (int)getPosition().getY() <= (int)point.getY() && (int)point.getY()  <= (int) p2.getY();
            }else{ 
                return (int)getPosition().getY() >= (int)point.getY() && (int)point.getY()  >= (int) p2.getY();
            }        
        }else if ( (int)p2.getX() <= (int)point.getX() && (int)point.getX() <= (int) getPosition().getX()){
            return true;
        }
            return false;
    }

    @Override
    public void draw(java.awt.Graphics canvas) {     
    Color oldColor = canvas.getColor();    
    canvas.setColor(super.getFillColor());
    canvas.setColor(super.getColor());
    canvas.drawLine((int)getPosition().getX(),(int)getPosition().getY(), (int)getP2().getX(), (int)getP2().getY());
    canvas.setColor(oldColor);
    }

    @Override
    public void Resize(Point point) {
        moveRec(point);
        System.out.println("index is "+ index);
        switch (index) {
            case 0 -> setPosition(point);
            case 1 -> setP2(point);
           // case 2 -> setP3(point);
            default -> {
            }
        }
    }
     

    
    @Override
    public void pointPressed(Point point, Graphics g) {
             points[0] = new Rectangle2D.Double(this.getPosition().x,this.getPosition().y-4,SIZE, SIZE); 
             points[1] = new Rectangle2D.Double(this.getP2().x,this.getP2().y-4,SIZE, SIZE);
             points[2] = null;
             points[3] = null;
             Graphics2D g2 = (Graphics2D) g;
             for (int i = 0; i < points.length; i++) {
              if (points[i]!= null){   
              g2.fill(points[i]);
    }
    }
    }

    @Override
    public void moveRec(Point point) {
         if(index !=-1){
        points[index] = new Rectangle2D.Double(point.getX(),point.getY(),SIZE,SIZE);
    }
    }
  @Override
    public boolean readyToMove(Point point) {
      for (int i = 0; i < 2; i++) {
              if (points[i].contains(point.x, point.y)){
                  index =i;
                  return true;
              }              
    }
      return false;
    }
//    @Override
//    public void Resize(Point point) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean readyToMove(Point point) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

}
