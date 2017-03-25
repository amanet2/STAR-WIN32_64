#ifndef SITE_H
#define SITE_H

#include "siteEngine.h"

class site
{
	public:

	siteType _type;	//what type of site this is, a trader, inn, enemy, treasure...
	int _visited;

	int _xCoord;
	int _yCoord;

	int xCoord();
	int yCoord();
	
	void setX(int x);
	void setY(int y);

	/*GETTERS*/
	siteType type();
	int visited();

	site(int x, int y);
	site(siteType s, int x, int y);

	
};

#endif
