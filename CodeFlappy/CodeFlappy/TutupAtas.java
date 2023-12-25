import greenfoot.*; 

public class TutupAtas extends Actor
{
    private int jeda=0,trans=0,posx=0,posy=0,persen=0,arah=1,mode=0;
    private GreenfootImage img;
    
    public TutupAtas()
    {
        
    }
    
    public TutupAtas(int m)
    {
        mode=m;
    }
    
    public void setMode(int m)
    {
        mode=m;
        fadein(mode);
    }
    
    public void addedToWorld(World latar)
    {
        posx=getX();
        posy=getY();
        fadein(0);
    }
    
    public void fadein(int a)
    {
        img=new GreenfootImage("gameover.png");
        if(mode==1)img=new GreenfootImage("youwin.png");
        trans=(int)(255.0*a/100);
        img.setTransparency(trans);
        setImage(img);
    }
    
    public void fadein(int a, GreenfootImage image)
    {
    img = image;
    mode = a;
    trans = (int) (255.0 * a / 100);
    img.setTransparency(trans);
    setImage(img);
    }
    
    public void act() 
    {
        if(((MyWorld)getWorld()).KONDISI!=2 && ((MyWorld)getWorld()).KONDISI!=5)return;
        if(jeda>0)jeda--;
        else{
            int d=30;
            if(arah>0){
                persen+=d;   
                if(persen>100){
                    persen=100;                    
                }
                if(persen==100){
                    arah=-1;
                }
            }else if(arah<0 && ((MyWorld)getWorld()).KONDISI!=5){
                persen-=d;   
                if(persen<0){
                    persen=0;
                } 
            }               
            int py=posy-(int)(20.0*(1.0-1.0*persen/100));
            fadein(persen);
            setLocation(getX(),py);
            jeda=2;
        }
    }    
}
