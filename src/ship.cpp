#include "ship.h"

/*GETTERS*/
std::vector<sailor*> * ship::theCrew(){ return _theCrew;}
//getters
int ship::health(){ return _health;}
int ship::food(){ return _food;}
int ship::gold(){return _gold;}
int ship::xCoord(){return _xCoord;}
int ship::yCoord(){return _yCoord;}

ship::ship(int x,int y)	//constructor
{
	_theCrew = new std::vector<sailor*>();
	_health = 100;
	_gold = 1000;
	_food = 50000;

	_xCoord = x;
	_yCoord = y;
}

void ship::setX(int x)
{
	_xCoord = x;
}

void ship::setY(int y)
{
	_yCoord = y;
}
