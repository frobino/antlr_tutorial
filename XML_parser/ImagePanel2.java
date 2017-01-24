import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.JPanel;

class ImagePanel2 extends JPanel
{
    //BufferedImage image;
	Component comp;
    double scale;
  
    public ImagePanel2(Component comp)
    {
    	//loadImage();
    	this.comp=comp;
    	
    	scale = 1.0;
        setBackground(Color.black);
    }
  
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
                
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth();
        int h = getHeight();
        int imageWidth = comp.getWidth();
        int imageHeight = comp.getHeight();
        double x = (w - scale * imageWidth)/2;
        double y = (h - scale * imageHeight)/2;
        
        AffineTransform at = AffineTransform.getTranslateInstance(x,y);
        
        at.scale(scale, scale);
        /*
        g2.drawRenderedImage(image, at);
        */
        
    }
  
    /**
     * For the scroll pane.
     */
    public Dimension getPreferredSize()
    {
        int w = (int)(scale * comp.getWidth());
        int h = (int)(scale * comp.getHeight());
        return new Dimension(w, h);
    }
  
    public void setScale(double s)
    {
        scale = s;
        revalidate();      // update the scroll pane
        repaint();
    }
  

}
  
