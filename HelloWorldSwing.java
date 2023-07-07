import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

// class DrawRec extend JPanel implement MouseListener, MouseMotionListener{
//     private List<Point> points;

//     DrawRec(){
//         points =new ArrayList<>();
//         addMouseListener(this);
//         addMouseMotionListener(this);
//     }

//     public void mouseDragged(MouseEvent e){
         
//     }
// }

class Draw extends JPanel implements MouseListener, MouseMotionListener {
    private List<Point> points;
    private boolean isDrawing;

    Draw() {
        points = new ArrayList<>();
        isDrawing = false;
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.WHITE);
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

    public void mousePressed(MouseEvent e) {
        isDrawing = true;
        int mouseX = e.getX();
        int mouseY = e.getY();
        points.add(new Point(mouseX, mouseY));
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
        isDrawing = false;
    }

    public void mouseDragged(MouseEvent e) {
        if (isDrawing) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            points.add(new Point(mouseX, mouseY));
            repaint();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        for (Point point : points) {
            g2d.fillOval(point.x, point.y, 10, 10);
        }
    }

    // Implement other methods of MouseListener and MouseMotionListener
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    private class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

public class HelloWorldSwing {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Hello World Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        // MenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menuFiles = new JMenu("Files");
        menuBar.add(menuFiles);
        JMenuItem itemOpen = new JMenuItem("Open", KeyEvent.VK_0);
        menuFiles.add(itemOpen);

        itemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open what?");
            }
        });

        JMenuItem itemSave = new JMenuItem("Save", 0);
        menuFiles.add(itemSave);

        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFileChooser = new JFileChooser();
                int returnValue = saveFileChooser.showSaveDialog(frame);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = saveFileChooser.getSelectedFile().getPath() + ".png";

                    BufferedImage image = new BufferedImage(frame.getContentPane().getWidth(),
                            frame.getContentPane().getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = image.createGraphics();
                    frame.getContentPane().getComponent(0).paint(g2d);
                    g2d.dispose();

                    try {
                        ImageIO.write(image, "PNG", new File(filePath));
                        System.out.println("Lưu tệp tin vào đường dẫn: " + filePath);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JMenuItem itemSaveAs = new JMenuItem("Save as", 0);
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
