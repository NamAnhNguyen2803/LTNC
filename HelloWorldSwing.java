import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;


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

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (Point point : points) {
            g.fillOval(point.x, point.y, 10, 10);
        }
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
        frame.setSize(1080, 720);
        frame.setVisible(true);

        //test
        frame.setBackground(Color.red);


        //MenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menuFiles= new JMenu("Files");
        menuBar.add(menuFiles);
        JMenuItem itemOpen = new JMenuItem("Open",KeyEvent.VK_0);
        menuFiles.add(itemOpen);

        itemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Open what?");
            }
        });

  

        JMenuItem itemSave= new JMenuItem("Save", 0);
        menuFiles.add(itemSave);
        JMenuItem itemSaveas=new JMenuItem("Save as", 0);
        menuFiles.add(itemSaveas);
        menuFiles.addSeparator();
        JMenuItem itemClose= new JMenuItem("Close", 0);
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
