package Backend;

//import com.github.cliftonlabs.json_simple.JsonObject;
import java.awt.Color;
import java.awt.Point;
import org.json.simple.JSONObject;
//import org.json.simple.JSONObject;

public abstract class AbstractShapeClass implements Shape, Moveable, Resizable {

    int index = -1;
    private Point position, draggingPoint, pressedPoint;
    private Color c = Color.BLUE; //border colour
    private Color fillColor = Color.BLUE;
    private String ShapeName;

    public String getShapeName() {
        return ShapeName;
    }

    public void setShapeName(String ShapeName) {
        this.ShapeName = ShapeName;
    }

    public AbstractShapeClass(Point position) {
        this.position = position;
    }

    @Override
    public void setDraggingPoint(Point point) {            //overridden in all subclasses to get difference
        this.draggingPoint = point;
    }

    @Override
    public Point getDraggingPoint() {
        return this.draggingPoint;
    }

    @Override
    public abstract boolean contains(Point point);

    @Override
    public void moveTo(Point point) {           //overridden in all subclasses 

    }

    public void resetIndex() {
        index = -1;
    }

    @Override
    public void setPosition(java.awt.Point position) {
        this.position = position;
    }

    public java.awt.Point getPosition() {
        return this.position;
    }

    /* colorize */
    public void setColor(java.awt.Color color) {
        this.c = color;
    }

    public java.awt.Color getColor() {
        return this.c;
    }

    public void setFillColor(java.awt.Color color) {
        this.fillColor = color;
    }

    public java.awt.Color getFillColor() {
        return fillColor;

    }

    public Point sub(Point p1, Point p2) {
        return new Point(p1.x - p2.x, p1.y - p2.y);
    }

    @Override
    public void setPressedPoint(Point point) {
        this.pressedPoint = point;
    }

    @Override
    public Point getPressedPoint() {
        return this.pressedPoint;
    }

    private String extractColor(String Col) {

        return Col.substring(15, Col.length() - 1);
    }

    public JSONObject toJsonobject() //for saving 
    {
        JSONObject jobj = new JSONObject();
        JSONObject pointJson = new JSONObject();
        pointJson.put("X", position.x + "");
        pointJson.put("Y", position.y + "");
        // jobj.put("position", getPosition());
        jobj.put("position", pointJson);
        if (this.c == null) {
            this.c = Color.BLACK;
        }
        jobj.put("borderColor", extractColor(c.toString()));
        if (this.fillColor == null) {
            this.fillColor = Color.BLACK;
        }
        jobj.put("fillColor", extractColor(fillColor.toString()));

        if (this instanceof Oval oval) {

            jobj.put("hRadius", oval.gethRadius());
            jobj.put("vRadius", oval.getvRadius());
            jobj.put("ShapeName", oval.getShapeName());
        } else if (this instanceof LineSegment line) {
            JSONObject pointJson2 = new JSONObject();
            pointJson2.put("X", line.getP2().x + "");
            pointJson2.put("Y", line.getP2().y + "");
            jobj.put("p2", pointJson2);
            jobj.put("ShapeName", line.getShapeName());
        } else if (this instanceof Rectangle rect) {
            jobj.put("height", rect.getHeight());
            jobj.put("width", rect.getWidth());
            jobj.put("ShapeName", rect.getShapeName());
        } else if (this instanceof Triangle tri) {
            JSONObject pointJson2 = new JSONObject();
            pointJson2.put("X", tri.getP2().x + "");
            pointJson2.put("Y", tri.getP2().y + "");
            jobj.put("p2", pointJson2);
            JSONObject pointJson3 = new JSONObject();
            pointJson3.put("X", tri.getP3().x + "");
            pointJson3.put("Y", tri.getP3().y + "");
            jobj.put("p2", pointJson2);
            jobj.put("p3", pointJson3);
            jobj.put("ShapeName", tri.getShapeName());
        }
        return jobj;

    }

    public static int[] RGBvaluess(String c) {
        String[] phase1 = c.split(",");
        int[] rgb=new int[3];
        String[] phase2;
        for (int i = 0; i < 3; i++) {
            phase2 = phase1[i].split("=");
            rgb[i] = Integer.parseInt(phase2[1]);
            System.out.println(rgb[i]);
        }
        return rgb;
    }

    public static AbstractShapeClass JObjectToShape(JSONObject jo) //for loading
    {
        JSONObject point1 = (JSONObject) jo.get("position");
        Point pos = new Point( Integer. parseInt((String) point1.get("X")) ,Integer. parseInt((String) point1.get("Y")));
        
        String coll = (String) jo.get("borderColor");
        int[] cols;
        cols = RGBvaluess(coll);
        Color col = new Color(cols[0], cols[1], cols[2]);
        
        String fcoll = (String) jo.get("fillColor");
        int[] fillcols;
        fillcols = RGBvaluess(fcoll);
        Color fCol = new Color(fillcols[0], fillcols[1], fillcols[2]);

        String name = (String) jo.get("ShapeName");
        if (name.equals("Oval_")) {
            double VR = (double) jo.get("vRadius");
            double HR = (double) jo.get("hRadius");
            Oval o = new Oval(pos, HR, VR);
            o.setColor(col);
            o.setFillColor(fCol);
            return o;
        } else if (name.equals("Line_")) {
            JSONObject point2 = (JSONObject) jo.get("p2");
            Point end = new Point( Integer. parseInt((String) point2.get("X")) ,Integer. parseInt((String) point2.get("Y")));
            LineSegment L = new LineSegment(pos, end);
            L.setColor(col);
            return L;
        } else if (name.equals("Rectangle_")) {
            double w = (double) jo.get("width");
            double h = (double) jo.get("height");
            Rectangle r = new Rectangle(pos, w, h);
            r.setColor(col);
            r.setFillColor(fCol);
            return r;
        } else if (name.equals("Triangle_")) {
            JSONObject point2 = (JSONObject) jo.get("p2");
            Point p2 = new Point( Integer. parseInt((String) point2.get("X")) ,Integer. parseInt((String) point2.get("Y")));
            JSONObject point3 = (JSONObject) jo.get("p3");
            Point p3 = new Point( Integer.parseInt((String) point3.get("X")) ,Integer. parseInt((String) point3.get("Y")));
            Triangle t = new Triangle(pos, p2, p3);
            t.setColor(col);
            t.setFillColor(fCol);
            return t;
        }
        return null;
    }

    @Override
    public abstract void draw(java.awt.Graphics canvas);
}
