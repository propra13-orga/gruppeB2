
public class AnimationManager
{
	private int anim;
	
    public AnimationManager() 
    {
    	anim = 0;
    }
    
    public void animate(int posX, int posY, String [] pics, int delay, int step)
    {
    	for(int i = 0; i < pics.length; i++)
    	{
    		if((anim + i * delay) < (i+1) * delay)
    		{
    			StdDraw.picture(posX, posY, pics[i]);
    			anim++;
    			break;
    		}
    	}
    }
}
