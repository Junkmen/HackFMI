package xtrordinary.games.enb;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MathHelper {
	private static float x,y,z;
	public static float GetDistanceFromPoint(float CenterX,float CenterY, float X, float Y) {
		  X = X - CenterX;
		  Y = CenterY - Y;
		  X = X*X + Y*Y;
		  X = (float) Math.sqrt(X);
		  return X;
	} /*
		Calculates the distance between two points in the system.
		*/
	public static float GetDistanceFromPointNoSqrt(float CenterX,float CenterY, float X, float Y) {
		  X = X - CenterX;
		  Y = CenterY - Y;
		  X = X*X + Y*Y;
		  return X;
	} /* SAME AS ABOVE WITHOUT SQRT*/
	
	public static Vector2 AfterRotationXY(Vector2 cord,float radians) {
		x = cord.x* MathUtils.cos(radians) - cord.y* MathUtils.sin(radians);
		y = cord.x* MathUtils.sin(radians) + cord.y* MathUtils.cos(radians);
		cord.x = x;
		cord.y = y;
		return cord;
	} /*
		THIS FUNCTION WORKS WITH RADIANS ! MATHUTILS.DEGREES_TO_RADIANS !!!
	  */
	public static float CalculateXPerY(float X,float Y, float X1, float Y1) {
		
		X = X1 - X;
		Y = Math.abs(Y - Y1);
		if (Y != 0 ) 
			X = X/Y;
		else return -1; // Objects are on the same Y
		return X;
	}
}
