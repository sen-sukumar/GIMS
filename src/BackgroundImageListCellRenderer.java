
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class BackgroundImageListCellRenderer extends DefaultListCellRenderer {
    int row = -1;
    private JList list;
    private final Image image;
    private JScrollPane scrollPane;

    public BackgroundImageListCellRenderer(JScrollPane scrollPane, Image image) {
        this.image = image;
        this.scrollPane = scrollPane;

        // must use SIMPLE_SCROLL_MODE to force scrollpane to redraw the whole JList,
        // otherwise scrollpane will use 'cached' images
        scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        // we will paint our own background
        setOpaque(false);
    }

    public Component getListCellRendererComponent(JList list, Object value, int row, boolean isSelected, boolean cellHasFocus) {
        this.list = list;
        this.row = row;
        return super.getListCellRendererComponent(list, value, row, isSelected, cellHasFocus);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // compute the offset of the image we want to draw
        Rectangle cellBounds = list.getCellBounds(row, row);
        Point p = cellBounds.getLocation();
        Point viewPosition = scrollPane.getViewport().getViewPosition();
        p.x -= viewPosition.x;
        p.y -= viewPosition.y;

        // draw the image from the offset computed above
        AffineTransform transform = AffineTransform.getTranslateInstance(-p.x, -p.y);
        g2d.drawImage(image, transform, null);

        // let the original renderer do its work
        super.paintComponent(g);
    }
    public static void main(String args[]) {
        JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Image img = Toolkit.getDefaultToolkit().getImage("c://gims//bkg1.jpg");
        MediaTracker tracker = new MediaTracker(f);
    tracker.addImage(img, 0);
    try {
        tracker.waitForAll();
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }
    String[] items = {"item1"};
     JList list = new JList(items);
    list.setFont(new Font("Arial", Font.BOLD, 36));
    JScrollPane sp = new JScrollPane(list);
    BackgroundImageListCellRenderer renderer = new BackgroundImageListCellRenderer(sp, img);
    renderer.setHorizontalAlignment(JLabel.CENTER);
    list.setCellRenderer(renderer);
    f.add(sp, BorderLayout.CENTER);
    f.setSize(200, 200);
    f.setVisible(true);
}

}
