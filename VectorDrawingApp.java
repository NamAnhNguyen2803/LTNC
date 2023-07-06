import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class VectorDrawingApp extends JFrame {

    private List<Shape> shapes; // Danh sách các hình vẽ
    private Shape currentShape; // Hình vẽ hiện tại
    private Color currentColor; // Màu sắc hiện tại

    public VectorDrawingApp() {
        shapes = new ArrayList<>();
        currentShape = null;
        currentColor = Color.BLACK;

        setTitle("Ứng dụng vẽ hình vector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                for (Shape shape : shapes) {
                    g2d.setColor(shape.getColor());
                    g2d.draw(shape);
                }
            }
        };
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentShape = new Shape(e.getX(), e.getY(), 0, 0, currentColor); // Sửa lỗi và cung cấp màu sắc hiện tại
                shapes.add(currentShape);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentShape = null;
            }
        });
        drawingPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentShape != null) {
                    int width = e.getX() - currentShape.x; // Sửa lỗi và không cần ép kiểu
                    int height = e.getY() - currentShape.y; // Sửa lỗi và không cần ép kiểu
                    currentShape.setSize(width, height);
                    repaint();
                }
            }
        });

        JButton colorButton = new JButton("Chọn màu");
        colorButton.addActionListener(e -> {
            currentColor = JColorChooser.showDialog(this, "Chọn màu", currentColor);
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(colorButton);

        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VectorDrawingApp app = new VectorDrawingApp();
            app.setVisible(true);
        });
    }
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
