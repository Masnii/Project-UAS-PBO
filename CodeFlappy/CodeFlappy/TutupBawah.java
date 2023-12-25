import greenfoot.*; 

public class TutupBawah extends Actor
{
    private int jeda=0,trans=0,posx=0,posy=0,persen=0,arah=1;
    private GreenfootImage img;
    
    public void addedToWorld(World latar)
    {
        posx=getX();
        posy=getY();
        fadein(0);
    }
    
    public void fadein(int a)
    {
        img=new GreenfootImage(300,100);
        img.setColor(new Color(222,215,152));
        img.fill();
        GreenfootImage image=new GreenfootImage("SCORE : "+((MyWorld)getWorld()).mscore,28,new Color(213,64,32),null);
        img.drawImage(image,30,15);
        image=new GreenfootImage("HIGHSCORE : "+((MyWorld)getWorld()).HIGHSCORE ,28,new Color(213,64,32),null);
        img.drawImage(image,30,45);
        trans=(int)(255.0*a/100);
        img.setTransparency(trans);
        setImage(img);
    }
    
    public void fadeout(int a)
    {
        img=new GreenfootImage(300,100);
        img.setColor(new Color(222,215,152));
        img.fill();
        img.setColor(Color.WHITE);
        GreenfootImage image=new GreenfootImage("SCORE : "+((MyWorld)getWorld()).mscore,28,new Color(213,64,32),null);
        img.drawImage(image,30,15);
        image=new GreenfootImage("HIGHSCORE : "+((MyWorld)getWorld()).HIGHSCORE ,28,new Color(213,64,32),null);
        img.drawImage(image,30,45);
        trans=(int)(255.0*a/100);
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
                    if(((MyWorld)getWorld()).KONDISI==2)((MyWorld)getWorld()).KONDISI=4;
                }
                int py=posy+(int)(20.0*(1.0-1.0*persen/100));
                fadein(persen);
                setLocation(getX(),py);
            }else if(arah<0 && ((MyWorld)getWorld()).KONDISI!=5){
                persen-=d;   
                if(persen<0){
                    persen=0;
                }                
                int py=posy+(int)(20.0*(1.0-1.0*persen/100));
                fadeout(persen);
                setLocation(getX(),py);
                if(persen==0){
                    Greenfoot.setWorld(new MyWorld());
                }
            }
            jeda=2;
        }
    }    
}
