package nju.java;


import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    private Field field;
    private String imageDir;
    private boolean isGood;
    private boolean isLive;
    private int idNum;
    
    public Player(int x, int y, Field field, String imageDir, boolean isGood, int idNum) {
        super(x, y);

        this.field = field;
        this.imageDir = imageDir;
        this.isGood = isGood;
        this.isLive = true;
        this.idNum = idNum;
        
        URL loc = this.getClass().getClassLoader().getResource(imageDir);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
    
    public int getIdNum(){
    	return idNum;
    }
    
    public void setImageDir(String imageDir){
    	 this.imageDir = imageDir;
    	 URL loc = this.getClass().getClassLoader().getResource(imageDir);
         ImageIcon iia = new ImageIcon(loc);
         Image image = iia.getImage();
         this.setImage(image);
    }
     
    public String getImageDir(){
    	return imageDir;
    }
    
    public boolean getIsLive(){
    	return isLive;
    }
    
    public void setIsLive(boolean isLive)
    {
    	this.isLive = isLive;
    }
    
    public boolean getIsGood(){
    	return isGood;
    }
    
    public Field getField(){
    	return field;
    }
    
    public synchronized void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
   
        this.field.getOut().println(this.getIdNum() + " " + this.x() + " " + this.y() + " " + imageDir);
    }

    public synchronized void run() {
    	while (!Thread.interrupted()) {
            Random rand = new Random();

            this.move(rand.nextInt(10), rand.nextInt(10));
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}