package com.universe.exploration.spaceship;

public class PointerGuidanceSystem implements IGuidanceSystem {
	/**
	 * Returns angle to which spaceship should point at.
	 * @param mx X clicked
	 * @param my Y clicked
	 * @param cpx spaceship position X
	 * @param cpy spaceship position Y
	 * @return double angle Note angle ranges from -180 to 180. 
	 */
	public double getAngle(int mx, int my, int cpx, int cpy)
	{
		double angle = 0;
		
		// Pointer position
		int pointClickedX = mx;
		int pointedClickedY = my;
		
		// Spaceship position
		int currentPositionX = cpx;
		int currentPositionY = cpy;
		
		int differenceX = currentPositionX - pointClickedX;
		int differenceY = currentPositionY - pointedClickedY;

		angle = (double)Math.atan2(differenceY, differenceX) * 180 / Math.PI;
		
		/*X += Math.cos(angle * Math.PI/180) * Speed;
		Y += Math.sin(angle * Math.PI/180) * Speed;*/
		return -1 * angle;
	}
}
