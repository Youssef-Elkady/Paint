package Backend;


import Frontend.VectorDrawingApp;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MyPanel extends JPanel implements DrawingEngine, MouseListener, MouseMotionListener {

    private int SIZE = 20;
    Point pdrag;
    int pos = 0;
    private int Ex, Ey;
    private ArrayList<Shape> shapes;
    private VectorDrawingApp frame1;
    private int currentIndex;
    AbstractShapeClass a, pre;
    boolean ready = false;
    private Point2D[] lastPoints = new Point2D[3];
    Rectangle2D[] points = new Rectangle2D.Double[4];

    public MyPanel(VectorDrawingApp fr) {
        addMouseListener(this);
        addMouseMotionListener(this);
        shapes = new ArrayList<>();
        this.frame1 = fr;
    }

    public JSONArray toJsonArray() {
        JSONArray jArray = new JSONArray();
        for (Shape shape : shapes) {
            AbstractShapeClass object = (AbstractShapeClass) shape;
            jArray.add(object.toJsonobject());
        }
        return jArray;
    }

    public void save(String path) {
        JSONArray jShapes = toJsonArray();
        //String jsonText =  Jsoner.serialize(jShapes); //jShapes.toString(); 
        try {
            try ( FileWriter fileWriter = new FileWriter(new File(path))) {
                fileWriter.write(jShapes.toJSONString());
                fileWriter.flush();
            }
        } catch (IOException ex) {

        }

    }

    public void load(String path) throws FileNotFoundException, IOException {
        shapes.clear();    
        String jsonText = null;
        org.json.simple.JSONArray ja = null;
        JSONParser jParser = new JSONParser();
        try {
            Object obj = jParser.parse(new FileReader(path));
            ja = (JSONArray) obj;

        } catch (Exception e) {

        }
        for (Object object : ja) {
            JSONObject jo = (JSONObject) object;
            AbstractShapeClass shape = AbstractShapeClass.JObjectToShape(jo);
            frame1.addPaneladdCombo(shape, shape.getShapeName());      //shapes.add(shape);

        }
        refresh(null);
        System.out.println("loadinggg");

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //   System.out.println("you clicked the mouse at " + e.getX() + ", " + e.getY());
        Point pointentered = new Point(e.getX(), e.getY());
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (((AbstractShapeClass) shapes.get(i)).contains(pointentered)) {
                a = ((AbstractShapeClass) shapes.get(i));
                //         System.out.println("shape number was selected " + i);
                frame1.setComboBoxIndex(i);
                refresh(null);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //    System.out.println("you pressed the mouse at " + e.getX());
        Ex = e.getX();
        Ey = e.getY();
        pdrag = new Point(e.getX(), e.getY());
        int i = 0;
        for (i = shapes.size() - 1; i >= 0; i--) {
            a = ((AbstractShapeClass) shapes.get(i));
            if (a.contains(new Point(Ex, Ey)) || a.readyToMove(pdrag)) {
                frame1.setComboBoxIndex(i);
                currentIndex = i;
                a.setDraggingPoint(new Point(Ex, Ey));
                pre = a;
                break;
            } else if (a != null) {
                pre = a;
                a = null;
            } else {
                a = null;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        pdrag = new Point(e.getX(), e.getY());
        //  System.out.println("we are dragging");
        if (ready) {
            //  System.out.println("we are dragging in x");
            pre.Resize(pdrag);
            refresh(null);

        } else if (a != null) {
            a.moveTo(pdrag);
            refresh(null);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ready = false;
        //  System.out.println("Release");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLUE, 2));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : shapes) {
            s.draw(g);
            if (s == a) {
                a.pointPressed(pdrag, g);
                ready = a.readyToMove(pdrag);
            }

        }

    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public Shape[] getShapes() {
        return shapes.toArray(Shape[]::new);
    }

    @Override
    public void refresh(Graphics canvas) {
        repaint();
    }
}
