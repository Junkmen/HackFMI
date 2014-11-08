package xtrordinary.games.enb;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Object2DManager {
	protected ArrayList<Object2D> objects = new ArrayList<Object2D>(25);
	protected ArrayList<Object2D> objectsSwap = new ArrayList<Object2D>();
	protected ArrayList<Object2D> objectsCopy = new ArrayList<Object2D>(25);
	
	private Object2D objectContainer;
	private Object2D objectContainer2;

	private int checkTo = 0;
	
	public boolean isPhysicActive;
	private boolean isObjectTouched = false;
	private float floatContainer,floatContainer2;
	
	public ArrayList<ArrayList<Integer>> collisionCheck = new ArrayList<ArrayList<Integer>>();
	protected int counter;
	
	public Object2DManager() {
		isPhysicActive = true;
	}
	
	public void copyObjects() {
		for (counter = 0; counter < objects.size(); counter++) {
			objectsCopy.add(objects.get(counter));
		}
	}
	
	public Object2D getObject(int i) {
		return objects.get(i);
	}
	public void disposeMainObjects() {
		for (counter = objectsCopy.size()-1; counter < objects.size();counter++) {
			objects.get(counter).dispose();
		}
		objects.clear();
	}
	
	public void swapObjects() {
		objects.clear();
		for (counter = 0; counter < objectsCopy.size(); counter ++) {
			objectContainer = objectsCopy.get(counter);
			objects.add(objectContainer);
			objectContainer.reset();
		}
	}
	
	public int addObject(Object2D object) {
		objects.add(object);
		return object.posInObjManager = objects.size() - 1;
	}
	
	public int addObject(Object2D object,boolean isSafe) {
		return this.addObject(object);
	}
	
	public void disposeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).dispose();
		}
		objects.clear();
		for (int i = 0; i < objectsCopy.size(); i++) {
			objectsCopy.get(i).dispose();
		}
		objectsCopy.clear();
		for (int i = 0; i < objectsSwap.size(); i++) {
			objectsSwap.get(i).dispose();
		}
		objectsSwap.clear();
		if (objectContainer != null) objectContainer.dispose();
		if (objectContainer2 != null) objectContainer2.dispose();

	}
	
	public void dynamicDispose() {
		
	}
	public void activateObject(int index) {
		objects.get(index).isDrawing = true; //Most objects physics are draw driven.
	}										//and won't update unless the object is drawn.
										   //so the draw activates: draw,touch,physics.
	public void drawObjects(SpriteBatch batch) {
		for (counter = 0; counter < objects.size( ); counter++ ) {
			objects.get(counter).drawAndRotate(batch); //Draws objects and rotates them.
		}											  //Objects may override their drawAndRotate 
	}												 //In case their images aren't power of two to prevent 
													//rotation bugs.
	public void drawObjectsWithoutRotation (SpriteBatch batch) {
		for (counter = 0; counter < objects.size( ); counter++ ) {
			objects.get(counter).draw(batch); //Calls the basic object draw. Handled and not rotated.
		}									 //The basic draw includes scale to height width. 
	}
	
	public boolean updateObjects(float deltaTime) {
		if (isPhysicActive) { 
			for (counter = 0; counter < objects.size( ); counter++ ) {
				objects.get(counter).action(deltaTime); //Updates the physics of the objects
			}
			return true;
		} else {
			return false;
		}
    }
	
	public void addCollision(Object2D firstObject,Object2D secondObject ) {
		collisionCheck.add(new ArrayList<Integer>());                                  //Creates new collision relationship between two objects.
		collisionCheck.get(collisionCheck.size()-1).add(firstObject.posInObjManager);  //Stores the object position in main array.
		collisionCheck.get(collisionCheck.size()-1).add(secondObject.posInObjManager);//And uses it wisely.
	}
	public boolean detectCollision() {
		for (counter = 0; counter < collisionCheck.size()-1; counter++) {
			objectContainer = objects.get(collisionCheck.get(counter).get(0));
			//if(objects.get(collisionCheck.get(counter).get).size() == 2);
			objectContainer2 = objects.get(collisionCheck.get(counter).get(1));
			if(Math.abs(objectContainer.Y - objectContainer2.Y) < 126) {
				if(objectContainer.objectShape == Object2D.CIRCLE) {
					if(objectContainer.checkCircleCircleIntersect(objectContainer2)) {
						objectContainer.onCollision();
						objectContainer.onCollision();
						return true;
					}
				} else if(objectContainer.objectShape == Object2D.RECTANGLE) {
					if(objectContainer.checkForIntersect2(objectContainer2)) {
						objectContainer.onCollision();
						objectContainer.onCollision();
						return true;
					}
				} else {
			
				}
			}
		}
		return false;
	}
	
	
	public boolean checkForTouchedObject(float X,float Y) {
		isObjectTouched = false;
		
		counter = objects.size()-1; //NullPointer evasion :}
		objectContainer2 = objects.get(counter);
		floatContainer = MathHelper.GetDistanceFromPointNoSqrt(objectContainer2.X +objectContainer2.width/2, objectContainer2.Y + objectContainer2.height/2, X, Y);
		
		for ( ; counter > checkTo; counter--) {
			objectContainer = objects.get(counter); //Get the object being checked. CPU optimization.
			if (objectContainer.isDrawing)         //User can't press something that isn't drawing.
				if (objectContainer.radius != 0) {    //Every object except buttons uses circle collision cuz of the finger touch.
					if (objectContainer.checkCircleForCollision(X, Y,true)) {
						//objectContainer.onTouch();
						isObjectTouched = true; //Marks that there was a touched object.
						if((floatContainer2 = MathHelper.GetDistanceFromPointNoSqrt(objectContainer.X +objectContainer.width/2, objectContainer.Y + objectContainer.height/2, X, Y)) < floatContainer) {
							floatContainer = floatContainer2;   //This section of the method checks if the
							objectContainer2 = objectContainer;//object is closer than the other touched.
						}									  //Able to call the onTouch() precisely on the
					} 										 //object intended to be touched by the user.
				} else {
					if (objectContainer.checkForIntersect2(X, Y,10,10)) {
						isObjectTouched = true; //Marks that there was a touched object.
						if((floatContainer2 = MathHelper.GetDistanceFromPointNoSqrt(objectContainer.X +objectContainer.width/2, objectContainer.Y + objectContainer.height/2, X, Y)) < floatContainer) {
							floatContainer = floatContainer2;   //This section of the method checks if the
							objectContainer2 = objectContainer;//object is closer than the other touched.
						}				
					}
				}
				
		}
		if (isObjectTouched) {
			objectContainer2.onTouch();
		}
		return isObjectTouched;
	}
	
	
	
	public void setTouchableObjects(int i) {
		checkTo = i; //In case that there is background. (It will consume all the touches)
	}
	
	public Object2D findLowestObject() {
		objectContainer = objects.get(1);
		for (counter = 2; counter < objects.size(); counter ++) {
			objectContainer2 = objects.get(counter);
			if (objectContainer2.Y < objectContainer.Y) {
				objectContainer = objectContainer2;
			}
		}
		return objectContainer;
	}
	
	public void function() {
		//Placeholder for user programming.
	}
	public static int ALL = -1; //Codes for the touchable objects.
	public static int WITH_BACKGROUND = 0; //Skips backgrounds.
}
