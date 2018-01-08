package nju.java;

import java.util.Random;

public class Minion extends Player implements Creature {
    private Position position;
    private String id;

    public Minion(int x, int y, Field field, String imageDir, String id, int idNum) {
    	super(x, y, field, imageDir, false, idNum);
        this.id = id;
    }
 
    public void report() {
        System.out.print(id + " ");
    }

 
    public void setPosition(Position position) {
        this.position = position;
        if(position != null) {
            position.setHolder(this);
        }
    }

  
    public Position getPosition() {
        return position;
    }

	@Override
	public synchronized void run() {
		
		while (!Thread.interrupted()) {
            Random rand = new Random();
            if(this.getIsLive()&& !this.getField().getCompleted()){
	            int index = -1, xdist = 10000, ydist = 10000;
	            for(int i = 0; i < this.getField().getPlayers().size();i++){
	            	if(this.getField().getPlayers().get(i).getIsGood() == true && this.getField().getPlayers().get(i).getIsLive() == true){
	            		if(Math.abs(this.getField().getPlayers().get(i).x() - this.x()) <= xdist){
	            			if(Math.abs(this.getField().getPlayers().get(i).x() - this.x()) == xdist){
	            				if(Math.abs(this.getField().getPlayers().get(i).y() - this.y()) < ydist){
	                				index = i;
	                				xdist = Math.abs(this.getField().getPlayers().get(i).x() - this.x());
	                				ydist = Math.abs(this.getField().getPlayers().get(i).y() - this.y());
	                			}
	            			}
	            			else{
	            				index = i;
	            				xdist = Math.abs(this.getField().getPlayers().get(i).x() - this.x());
	            				ydist = Math.abs(this.getField().getPlayers().get(i).y() - this.y());
	            			}
	            		}
	            	}
	            		
	            }
	            
	            if(index == -1){
	            	this.getField().setCompleted(true);
	            }else if(xdist == 100){
	            	if(ydist == 0){
	            		int randt = rand.nextInt(1);
	            		if(randt == 0){	            		
	            			this.setIsLive(false);
	            			this.setImageDir("over2.png");
	            			this.getField().getOut().println(this.getIdNum() + " " + "over2.png");
	            		}
	            		else{
	            			this.getField().getPlayers().get(index).setIsLive(false);
	            			this.setImageDir("over1.png");
	            			this.getField().getOut().println(this.getField().getPlayers().get(index).getIdNum() + " " + "over1.png");
	            		}
	            	}
	            	else{
	            		int t = 100 * (((this.getField().getPlayers().get(index).y() - this.y()) > 0) ? 1 : (-1));
	            		boolean flag = false;
	            		for(int i = 0; i < this.getField().getPlayers().size();i++){
	            			if(this.getField().getPlayers().get(i).x() == x() && this.getField().getPlayers().get(i).y() == y() + t 
	            					&& this.getField().getPlayers().get(i).getIsLive() == true)
	            				flag = true;
	            		}
	            		if(!flag)
	            			this.move(0, t);
	            	}
	            }
	            else{
	            	if(ydist == 0){
	            		boolean flag = false;
	            		for(int i = 0; i < this.getField().getPlayers().size();i++){
	            			if(this.getField().getPlayers().get(i).x() == x() - 100 && this.getField().getPlayers().get(i).y() == y()
	            					&& this.getField().getPlayers().get(i).getIsLive() == true)
	            				flag = true;
	            		}
	            		if(!flag)
	            			this.move(-100, 0);
	            	}
	            	else{
	            		int randt = rand.nextInt(1);
	            		if(randt == 0){	
	            			boolean flag = false;
		            		for(int i = 0; i < this.getField().getPlayers().size();i++){
		            			if(this.getField().getPlayers().get(i).x() == x() - 100 && this.getField().getPlayers().get(i).y() == y()
		            					&& this.getField().getPlayers().get(i).getIsLive() == true)
		            				flag = true;
		            		}
		            		if(!flag)
		            			this.move(-100, 0);
	            		}
	            		else{
	            			int t = 100 * (((this.getField().getPlayers().get(index).y() - this.y()) > 0) ? 1 : (-1));
		            		boolean flag = false;
		            		for(int i = 0; i < this.getField().getPlayers().size();i++){
		            			if(this.getField().getPlayers().get(i).x() == x() && this.getField().getPlayers().get(i).y() == y() + t
		            					&& this.getField().getPlayers().get(i).getIsLive() == true)
		            				flag = true;
		            		}
		            		if(!flag)
		            			this.move(0, t);
	            		}
	            	}
	            }
                      
//            this.move(100, 0);
            }
            
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.getField().repaint();

            } catch (Exception e) {

            }
        }

	}
   
}
