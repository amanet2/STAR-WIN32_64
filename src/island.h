#ifndef ISLAND_H
#define ISLAND_H

#include "site.h"
#include <ctime>
#include <stdlib.h>
#include <vector>

using namespace std;

class island
{
	public:
		island(int depth,int position, int number);	

		int _depth;	//depth in the bst
		int _position;	//position for big map display (1-8 will tell java where to draw)
		int _number; //number is out of 11 and used to match a set of images with a particular island
		
		int _locked;	

		//siteAlignment _alignment;
		vector<site*> *_interactives;	//a site is something like a merchant, inn, pirate clan, etc.

		/*GETTERS*/
		//siteAlignment alignment();
		vector<site*> *interactives();

		/*SETTERS*/
		//void setAlignment(siteAlignment a);
		island* _rightNeighbor;	//for the graph
		island* _leftNeighbor;
		island* _parentIsland;

		island* rightNeighbor();	//getters
		island* leftNeighbor();
		island* parentIsland();
		int locked();
		int position();
		int depth();
		int number();
		
};

#endif
