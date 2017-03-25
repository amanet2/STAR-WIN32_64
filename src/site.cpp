#include "site.h"

site::site(int x, int y){ 
	_type = TREASURE; 
	_visited = 0; 
	_xCoord = x; 
	_yCoord = y;}
site::site(siteType s, int x, int y){ _type = s; _visited = 0; _xCoord = x; _yCoord = y;}

siteType site::type(){ return _type;}
int site::visited(){return _visited;}

int site::xCoord(){return _xCoord;}
int site::yCoord(){return _yCoord;}

void site::setX(int x){_xCoord = x;}
void site::setY(int y){_yCoord = y;}
