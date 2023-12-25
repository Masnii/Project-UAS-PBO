import greenfoot.*; 

public class Lantai extends Actor
{
    public static int plg=0; 
    public void act() 
    {
        int px=getX()-MyWorld.KECEPATAN;
        if(px<-0.5*getImage().getWidth()){
            px=plg;
        }
        setLocation(px,getY());
    }    
    
    public void act(int deltaX) {
        int px = getX() - deltaX;
        if(px < -0.5 * getImage().getWidth()){
            px = plg;
        }
        setLocation(px, getY());
    }
}
