package nju.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Field extends JPanel {

    private final int OFFSET = 30;
    private final int SPACE = 100;

    private ArrayList tiles = new ArrayList();
    private Player player;

    private ArrayList<Player> players = new ArrayList<Player>();  
    private ArrayList<Player> players2 = new ArrayList<Player>();
    
    private int w = 0;
    private int h = 0;
    private boolean completed = false;
    private boolean unstart = true;
    private boolean isPlayback = false;
    private PrintWriter out;
    
    private String level =
            		"0.........\n" +
                    ".1....-...\n" +
                    "2......-..\n" +
                    ".3......*.\n" +
                    "4........-\n" +
                    ".5......+.\n" +
                    "6......-..\n" +
                    ".7....-...\n";
//number represents grandpa and huluwa
//- represents hamajing
//+ represents xiezijing
//* represents snakejing
    public Field() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }
    
    public PrintWriter getOut(){
    	return out;
    }
    
    public void setIsPlayback(boolean isPlayback){
    	this.isPlayback = isPlayback;
    }
    
    public void setCompleted(boolean completed){
    	this.completed = completed;
    	if(completed == true)
    		out.close();
    }
    
    public boolean getCompleted(){
    	return completed;
    }
    
    public boolean getIsPlayback(){
    	return isPlayback;
    }
    
    public ArrayList<Player> getPlayers(){
    	return players;
    }
    
    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        int x = OFFSET;
        int y = OFFSET;

        Tile a;

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item == '@') {
//                player = new Player(x, y, this);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }
            else if(item == '0')
            {
            	a = new Tile(x, y);
                tiles.add(a);
                 
            	player = new Grandpa(x, y, this, "yeye.png", "ye", players.size());
            	players.add(player);
            	player = new Grandpa(x, y, this, "yeye.png", "ye", players.size());
            	players2.add(player);
            	
            	x += SPACE;
            }
            else if (item >= '1' && item <= '7'){
            	a = new Tile(x, y);
                tiles.add(a);
            	
            	player = new Huluwa(x, y, this, item + "wa.png", 
            			COLOR.values()[item - '1'], SENIORITY.values()[item - '1'], players.size());  
            	players.add(player);
            	player = new Huluwa(x, y, this, item + "wa.png", 
            			COLOR.values()[item - '1'], SENIORITY.values()[item - '1'], players.size());  
            	players2.add(player);
            	
            	x += SPACE;
            }
            else if(item == '-')
            {
            	a = new Tile(x, y);
                tiles.add(a);
                 
            	player = new Minion(x, y, this, "hamajing.png", "luo", players.size());  
            	players.add(player);
            	player = new Minion(x, y, this, "hamajing.png", "luo", players.size());  
            	players2.add(player);
            	
            	x += SPACE;
            }
            else if(item == '+')
            {
            	a = new Tile(x, y);
                tiles.add(a);
                 
            	player = new Scorpion(x, y, this, "xiezijing.png", "xie", players.size());  
            	players.add(player);
            	player = new Scorpion(x, y, this, "xiezijing.png", "xie", players.size());  
            	players2.add(player);
            	x += SPACE;
            }
            else if(item == '*')
            {
            	a = new Tile(x, y);
                tiles.add(a);
                 
            	player = new Snake(x, y, this, "shejing.png", "she", players.size());
            	players.add(player);
            	player = new Snake(x, y, this, "shejing.png", "she", players.size());
            	players2.add(player);
            	x += SPACE;
            }

            h = y;
        }

/*        for(int i = 0; i < 6 ; i++)
        {
        	 // player = new Player(i * 100 + OFFSET, i * 100 + OFFSET, this);
        	player = new Huluwa(0 + OFFSET,i * 100 +OFFSET, this, "dawa.png", 
        			COLOR.values()[i], SENIORITY.values()[i]);  
        	players.add(player);
        }
  */      
/*        player = new Huluwa(500+ OFFSET,0+OFFSET, this, "shejing.png",
        		COLOR.values()[0], SENIORITY.values()[0]);
  */      
         
        
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(0, 70, 100));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);


//        world.add(player);
        if(this.getIsPlayback()){
        	world.addAll(players2);
        	System.out.println("Here!");
        }
        else{
        	world.addAll(players);
        	System.out.println("There!");
        }

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

           
            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
        
        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player && ((Player) item).getIsLive()) {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            } 
            
            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    
    class Playback implements Runnable{

		public void run() {
			// TODO Auto-generated method stub
			//read from file
        	
        	
        	JFileChooser c = new JFileChooser();
      	  	int rVal = c.showOpenDialog(null);
      	  	if(rVal == JFileChooser.APPROVE_OPTION) {
      	  		if(c.getSelectedFile().getName().equals("record")){
      	  			
          	  		try {
						BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(c.getSelectedFile().getAbsolutePath())));
						String s;
						int line = 0;
						while((s = in.readLine()) != null ){
							
							if(line == players2.size())
							{
								setIsPlayback(true);
							}
							
							String[] as = s.split(" ");
							if(line < players2.size())
							{
								players2.get(Integer.parseInt(as[0])).setIsLive(true);
							}
							if(as.length == 4){
								
								players2.get(Integer.parseInt(as[0])).setX(Integer.parseInt(as[1]));
							
								players2.get(Integer.parseInt(as[0])).setY(Integer.parseInt(as[2]));
								players2.get(Integer.parseInt(as[0])).setImageDir(as[3]);
								
						//	System.out.println(players2.get(Integer.parseInt(as[0])).x() - players.get(Integer.parseInt(as[0])).x());
							}
							else if(as.length == 2){
								players2.get(Integer.parseInt(as[0])).setIsLive(false);
								players2.get(Integer.parseInt(as[0])).setImageDir(as[1]);
							}
							
							if(line >= players2.size()){
								try {
									Thread.sleep(100);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							repaint();
							line++;
						}
          	  		} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
          	  		setIsPlayback(false);
          	  		repaint();
      	  		}
      	        //System.out.println(c.getSelectedFile().getAbsolutePath());
      	    }
      	  	else if(rVal == JFileChooser.CANCEL_OPTION) {
      	  		;
      	    }
		}
    }
    
    
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

        	
        	int key = e.getKeyCode();
        	
            if (completed) {
            	if(key == KeyEvent.VK_L){
                	
                	Playback p = new Playback();
                	new Thread(p).start();
                }
                return;
            }

            if(unstart){
	        	if(key == KeyEvent.VK_L){
	            	
	            	Playback p = new Playback();
	            	new Thread(p).start();
	            }
	            else if (key == KeyEvent.VK_LEFT) {
	
	
	                player.move(-SPACE, 0);
	
	            } else if (key == KeyEvent.VK_RIGHT) {
	
	
	                player.move(SPACE, 0);
	
	            } else if (key == KeyEvent.VK_UP) {
	
	
	                player.move(0, -SPACE);
	
	            } else if (key == KeyEvent.VK_DOWN) {
	
	//            	completed = true;
	                player.move(0, SPACE);
	
	            } else if (key == KeyEvent.VK_S) {
	
	                new Thread(player).start();
	
	            } else if (key == KeyEvent.VK_R) {
	                restartLevel();
	            } else if (key == KeyEvent.VK_SPACE){
	            	unstart = false;
	            	
	            	try {
						out = new PrintWriter(new BufferedWriter(new FileWriter("record")));					
						//out.println("Hello!");					
						//out.println("Bye!");				
						
						for(int i = 0; i < players.size(); i++){
							out.println(players.get(i).getIdNum() + " " + players.get(i).x() + " " + players.get(i).y() + " " + players.get(i).getImageDir());
						}
						
						
						//out.close();					
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	
	            	for(int i = 0; i < players.size(); i++)
	            	{
	            		new Thread(players.get(i)).start();
	            	}
	            	
	            	
	            } 
            }

            repaint();
        }
    }


    public void restartLevel() {

        tiles.clear();
        players.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }

}