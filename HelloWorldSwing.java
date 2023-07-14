import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



//Ve hinh
class Draw extends JPanel implements MouseListener, MouseMotionListener {
    private List<Point> points;
    private List<Shape> shapes; // Danh sách các hình vẽ
    private Shape currentShape; // Hình vẽ hiện tại


    Draw() {
        shapes = new ArrayList<>();
        currentShape = null;
        points = new ArrayList<>();
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(HelloWorldSwing.backgroundColor);
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

public void mousePressed(MouseEvent e) {
    if (HelloWorldSwing.isDrawing) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        points.add(new Point(mouseX, mouseY, HelloWorldSwing.currentColor));

    } else if (HelloWorldSwing.isDrawingRectangle) {
         currentShape = new Shape(e.getX(), e.getY(), 5, 5,HelloWorldSwing.currentColor); 
        currentShape.setColor(HelloWorldSwing.currentColor);
        shapes.add(currentShape);
    }
    else if (HelloWorldSwing.isDelete) {
         int mouseX = e.getX();
        int mouseY = e.getY();
        points.add(new Point(mouseX, mouseY, HelloWorldSwing.backgroundColor));
    }
    repaint();
}

    public void mouseReleased(MouseEvent e) {
        if(HelloWorldSwing.isDrawingRectangle){
        currentShape = null;
        }
        // else if{

        // }
    }

 public void mouseDragged(MouseEvent e) {
    if (HelloWorldSwing.isDrawing) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        points.add(new Point(mouseX, mouseY, HelloWorldSwing.currentColor));
        repaint();
    } else if (HelloWorldSwing.isDrawingRectangle && currentShape != null) {
        int width = e.getX() - currentShape.x;
        int height = e.getY() - currentShape.y;
        currentShape.setSize(width, height);
        repaint();
    }else if (HelloWorldSwing.isDelete) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        points.add(new Point(mouseX, mouseY, HelloWorldSwing.backgroundColor));
        repaint();
    }
}

   protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (Point point : points) {
        g2d.setColor(point.getColor());
        g2d.fillOval(point.x, point.y, 10, 10);
    }

    for (Shape shape : shapes) {
        g2d.setColor(shape.getColor());
        g2d.draw(shape);
    }
}

    // Implement other methods of MouseListener and MouseMotionListener
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
}

class Shape extends Rectangle {

    private Color color;

    public Shape(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}





  class Point {
        int x;
        int y;
        Color color;

        Point(int x, int y,Color color) {
            this.x = x;
            this.y = y;
            this.color= color;
        }

        public Color getColor(){
            return color;
        }
        public void setColor (Color color){
            this.color=color;

        }
    }

    class Surface extends JPanel {

    private BufferedImage image;

    public Surface() {
        setPreferredSize(new Dimension(800, 600));
    }

    public void loadImage(File file) {
        try {
            image = ImageIO.read(file);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (image != null) {
            g2d.drawImage(image, 0, 0, null);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
public class HelloWorldSwing {

public static Color currentColor =Color.black;
public static boolean isDrawingRectangle = false;
public static boolean isDelete = false;
public static boolean isDrawing = true;
public static Color backgroundColor ;

    public static void createAndShowGUI() {

        JFrame frame = new JFrame("Hello World Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        // MenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuFiles = new JMenu("Files");
        menuBar.add(menuFiles);

        // Color pick
    

         JButton drawButton = new JButton("Delete");
        drawButton.addActionListener(e -> {
            isDrawing = !isDrawing;
            isDelete = !isDelete;
        });
        menuBar.add(drawButton);

        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> {
            isDrawing = !isDrawing;
            isDrawingRectangle = !isDrawingRectangle;
        });
        menuBar.add(rectangleButton);


        JButton backgroundButton = new JButton("Set Background Color");
        backgroundButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(frame, "Chọn màu", HelloWorldSwing.backgroundColor);
            if (selectedColor != null) {
                HelloWorldSwing.backgroundColor = selectedColor;
                frame.getContentPane().setBackground(HelloWorldSwing.backgroundColor);
            }
        });
        menuBar.add(backgroundButton);
        
        JButton colorButton = new JButton("Set Color");
        colorButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(frame, "Chọn màu", HelloWorldSwing.currentColor);
            if (selectedColor != null) {
                HelloWorldSwing.currentColor = selectedColor;
            }
        });
        menuBar.add(colorButton);
  JMenuItem itemOpen = new JMenuItem("Open");
        menuFiles.add(itemOpen);
        //
       
      
        itemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png", "jpg", "jpeg");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                }
            }
        });



        JMenuItem itemSave = new JMenuItem("Save As", 0);
        menuFiles.add(itemSave);

      itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFileChooser = new JFileChooser();
                int returnValue = saveFileChooser.showSaveDialog(frame);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = saveFileChooser.getSelectedFile().getPath() + ".png";

                    BufferedImage image = new BufferedImage(
                            frame.getContentPane().getWidth(),
                            frame.getContentPane().getHeight(),
                            BufferedImage.TYPE_INT_ARGB);

                    Graphics2D g2d = image.createGraphics();
                    frame.getContentPane().getComponent(0).paint(g2d);
                    g2d.dispose();

                    try {
                        ImageIO.write(image, "PNG", new File(filePath));
                        System.out.println("Lưu đường dẫn: " + filePath);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        
        

        JMenuItem itemSaveAs = new JMenuItem("Save", 0);
        menuFiles.add(itemSaveAs);
        menuFiles.addSeparator();
        JMenuItem itemClose = new JMenuItem("Close", 0);
        menuFiles.add(itemClose);

        // Content
        JPanel draw = new Draw();
        frame.getContentPane().add(draw);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
