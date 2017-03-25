#ifndef SHIP_H
#define SHIP_H

#include <stdlib.h>
#include "sailor.h"
#include <vector>

class ship
{
	public:
		
		std::vector<sailor*> * _theCrew;	//all sailors on the ship
		
		int _health;	//the state of the ship's wellbeing
		int _food; //in kcals
		int _gold;	//money

		int _xCoord;
		int _yCoord;

		/*GETTERS*/
		std::vector<sailor*> * theCrew();	//return list of crew
		
		int health();	//return health
		int food();	//return food supplies
		int gold();

		/*CONSTRUCTOR*/
		ship(int x, int y);	//constructor for ship
		
		int xCoord();
		int yCoord();

		void setX(int x);
		void setY(int y);
};

#endif
