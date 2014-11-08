package xtrordinary.games.enb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TexturePart {
	 
	Texture tex;
	Vector2 position;
	
	 int startX;
	 int startY;
	 
	//Target Dimension of image
	 
	int targetWidth;
	int targetHeight;
	 
	//Src Dimensions of Image
	 
	int srcWidth;
	int srcHeight;
	int srcX;
	int srcY;
	 
	//Ratio of dimension of target and source
	 
	float srcTargetRatioX;
	float srcTargetRatioY;
	 
	//ImagePart variables with values between 0-100 to draw part of image
	 
	float startPercentX;
	float endPercentX;
	float startPercentY;
	float endPercentY;
	 
	int clipWidth;
	int clipHeight;
	 
	int clipSrcWidth;
	int clipSrcHeight;
	
	boolean ReverseY;
	 
	public TexturePart(TextureRegion reg, float x, float y){
	 
	    tex=reg.getTexture();
	    position=new Vector2(x,y);
	    srcX=reg.getRegionX();
	    srcY=reg.getRegionY();
	    srcWidth=reg.getRegionWidth();
	    srcHeight=reg.getRegionHeight();
	    clipSrcWidth=srcWidth;
	    clipSrcHeight=srcHeight;
	    startPercentX=0;
	    startPercentY=0;
	    endPercentX=100;
	    endPercentY=100;
	    SetTargetDimension(srcWidth,srcHeight);
	}
	 
	public void SetTargetDimension(int targetWidth,int targetHeight){
		this.targetWidth = targetWidth;
		this.targetHeight = targetHeight;
		clipWidth = targetWidth;
		clipHeight = targetHeight;
		srcTargetRatioX = (float) targetWidth / (float) srcWidth;
		srcTargetRatioY = (float) targetHeight / (float) srcHeight;
	}
	 
	public void SetStart(float x,float y){
	    startPercentX=x;
	    startPercentY=y;
	    calculateShit();
	    position.y = srcHeight-clipHeight;
	    position.x =position.x - (srcWidth-clipWidth);
	}
	 
	public void SetEnd(float x,float y){
	    endPercentX=x;
	    endPercentY=y;
	    calculateShit();
	}
	
	public void calculateShit() {
			clipSrcWidth=(int)(Math.abs(startPercentX-endPercentX)/100f*srcWidth);
	        clipSrcHeight=(int)(Math.abs(startPercentY-endPercentY)/100f*srcHeight);
	        startX=srcX+(int)((float)startPercentX/100f*(float)srcX);
	        startY=srcY+(int)((float)startPercentY/100f*(float)srcY);
	        clipWidth=(int) (srcTargetRatioX*clipSrcWidth);
	        clipHeight=(int) (srcTargetRatioY*clipSrcHeight);
	}
	 
	public void draw(SpriteBatch sp){
	        sp.draw(tex, position.x, position.y,  targetWidth/2, targetHeight/2,
	      clipWidth, clipHeight,1, 1, 0, startX, startY,
	      clipSrcWidth, clipSrcHeight, ReverseY, ReverseY);
	}
	public void dispose() {
		this.tex.dispose();
	}
}
