package xtrordinary.games.enb;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//Copyright Stefan Iliev 2014
//Use only if prohibited or bought.
//You may not disturb this to other people or companies unless the author allows you to.

public class Object2D {
    public float X;
    public float Y;
    public float width;
    public float height;
    public float rotation = -90;
    public TextureRegion texture;
    public boolean isDrawing;
    public boolean toDispose;
    public float radius,radiusSqr,increasedRadius;
    public int directionX = 1;
    public int directionY = 1;
    public float onCreateX;
    public float onCreateY;
    public int posInObjManager;
    public int objectShape = 0;  // 0 - Rectangle, 1 - Circle, 2 - Other multi vertices shape.
    public float speedX,spdXdecay;
    public float speedY,spdYdecay;

    public static int CIRCLE = 1,RECTANGLE = 0, MULTIPLE_VERTICES = 2;


    public Object2D ( ) {
        this.X = 0;
        this.Y = 0;
        this.onCreateX = 0;
        this.onCreateY = 0;
        this.rotation = -90;
        this.isDrawing = true;
    }

    public Object2D (float X, float Y) {
        this.X = X;
        this.Y = Y;
        this.onCreateX = X;
        this.onCreateY = Y;
        this.isDrawing = true;
    }
    public Object2D (float X,float Y,float width,float height) {
        this.X = X;
        this.Y = Y;
        this.onCreateX = X;
        this.onCreateY = Y;
        this.width = width;
        this.height = height;
        this.isDrawing = true;
    }
    public Object2D copyObject(Object2D copy) {
        this.texture = copy.texture;
        this.X = copy.X;
        this.Y = copy.Y;
        this.width = copy.width;
        this.height = copy.height;
        this.radius = copy.radius;
        this.radiusSqr = copy.radiusSqr;
        this.increasedRadius = copy.increasedRadius;
        this.objectShape = copy.objectShape;
        reset();
        return this;
    }
    public void copyMovement(Object2D copy) {
        copy.speedX = this.speedX;
        copy.speedY = this.speedY;
        copy.width = this.width;
        copy.height = this.height;
        copy.directionX = this.directionX;
        copy.directionY = this.directionY;
    }

    public void setShape(int shapeNumber) {
        this.objectShape = shapeNumber; //Used for collision detection.
    }

    public void setTexture(String pathToTexture) {
        this.texture = new TextureRegion(new Texture(pathToTexture));
    }
    public void setTexture(Texture texture) {
        this.texture = new TextureRegion(texture);
    }
    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public void updateDimensions() {
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.radiusSqr = this.radius*this.radius;
    }
    public void updateDimensions(float w,float h) {
        this.width = w;
        this.height =h;
    }

    public TextureRegion getTexture() {
        return this.texture;
    }
    public void dispose() {
        if(this.texture != null) this.texture.getTexture().dispose();
        this.texture = null;
    }

    public void draw(SpriteBatch batch) {
        if (isDrawing) {
            batch.draw(this.texture,this.X,this.Y,this.width,this.height);
        }
    }

    public void draw(SpriteBatch batch, boolean vertical) {
        if (isDrawing) {
            batch.draw(this.texture,this.X,this.Y,this.width,this.height);
            if (!vertical) batch.draw(this.texture,this.X+this.width,this.Y,this.width,this.height);
            else batch.draw(this.texture,this.X,this.Y + this.height,this.width,this.height);
        }
    }
    public void draw(SpriteBatch batch,float X,float Y) {
        if (isDrawing) {
            batch.draw(this.texture,this.X,this.Y,this.width,this.height);
        }
    }

    public void drawAndRotate(SpriteBatch batch) {
        if (isDrawing) {
            batch.draw(this.texture,this.X,this.Y,this.width/2.0f,this.height/2.0f,this.width,this.height,1f,1f,this.rotation,false);
        }
    }

    public void toggleDrawing(boolean toDraw) {
        this.isDrawing = toDraw;
    }
    public boolean checkForIntersect(float X,float Y,float width,float height) {
        if ((this.X - width < X) && (this.X + this.width +width > X+width)) {
            if ((this.Y-height < Y) && (this.Y + this.height + height> Y + height)) {
                return true;
            }  //This function will check for rectangle intersection.
        }
        return false;
    }
    public boolean checkForIntersect2(float X,float Y,float width,float height) {
        if ((this.X  < X + width) && (this.X + this.width  > X)) {
            if ((this.Y < Y + height) && (this.Y + this.height > Y)) {
                return true;
            }  //This function will check for rectangle intersection. (overlap)
        }
        return false;
    }
    public boolean checkForIntersect2(Object2D rectangle) {
        if ((this.X  < rectangle.X + rectangle.width) && (this.X + this.width  > rectangle.X)) {
            if ((this.Y < rectangle.Y + rectangle.height) && (this.Y + this.height > rectangle.Y)) {
                return true;
            }  //This function will check for rectangle intersection. (overlap)
        }
        return false;
    }
    public boolean checkForIntersect(float X, float Y) {
        if ((this.X < X) && (this.X + this.width > X)) {
            if ((this.Y < Y) && (this.Y + this.height > Y)) {
                return true;
            }  //This function will check for point intersection.
        }
        return false;
    }

    public boolean isLineInCircle(float X,float Y, float X1, float Y1) {


        return false;
    }

    public void onCollision() {

    }

    public void onTouch() {

    } //4 > 2
    // 4 > 2
    public void onTouch(float X,float Y) {

    }

    public void reset() {
        this.X = this.onCreateX;
        this.Y = this.onCreateY;
        this.directionX = 1;
        this.directionY = 1;
    }

    public void updateReset( ) {
        this.onCreateX = this.X;
        this.onCreateY = this.Y;
    }

    public boolean checkCircleForCollision(float X,float Y) {
        return MathHelper.GetDistanceFromPointNoSqrt(this.X + (this.width/2), this.Y + (this.height/2), X, Y) < this.radiusSqr;
    }

    public boolean checkCircleForCollision(float X,float Y,boolean radiusIncrease) {  //Uses the increased radius. Used in checking for touch overlap.
        return MathHelper.GetDistanceFromPointNoSqrt(this.X + (this.width/2), this.Y + (this.height/2), X, Y) < this.increasedRadius;// Read increaseRadius()
    }
    public void increaseRadius(int radiusIncrease) {
        this.increasedRadius = (this.radius + radiusIncrease); //Increases the radius for touch checking purpose.
        this.increasedRadius *= this.increasedRadius;		  // The increased radius is good for moving objects-
    }														 //  that are hard to press.

    public boolean checkCircleCircleIntersect(Object2D object) { //Checks for overlap between two circle objects. TODO:Fix if used with cirle and non - circle.
        return MathHelper.GetDistanceFromPoint(this.X+(this.width/2),this.Y+(this.height/2), object.X+object.width/2, object.Y+object.height/2) < this.radius + object.radius;
    }

    public void setX(float X) {
        this.X = X;
    }
    public void setY(float Y) {
        this.Y = Y;
    }
    public void setPosition(float X,float Y) {
        this.setX(X);
        this.setY(Y);
    }
    public void setPosition(Object2D object) {
        this.setX(object.X);
        this.setY(object.Y);
    }
    public int getWidth() {
        return this.texture.getRegionWidth();
    }
    public int getHeight() {
        return this.texture.getRegionHeight();
    }

    public void action(float deltaTime) {

    }
}
