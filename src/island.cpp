#include "island.h"

using namespace std;

island::island(int depth,int pos,int num)	//constructor
{
	_interactives = new vector<site*>();
	//list of visitable sites
	srand(time(NULL));
	int rando = rand()%6 + 2;
	//random number of sites
	int ind;
	for(ind = 0; ind < rando; ind++)
	{
		_interactives->push_back(new site(0,0));
	}	//fill list with random number of sites

	_interactives->push_back(new site(TRADER,0,0));
	_interactives->push_back(new site(MAP,0,0));

	_rightNeighbor = NULL;	//to be set in diff method
	_leftNeighbor = NULL;
	_locked = 1;
	_position=pos;
	_depth = depth;
	_number = num;
}

/*GETTERS*/
//siteAlignment island::alignment(){return _alignment;}
vector<site*> *island::interactives(){ return _interactives;}


island* island::rightNeighbor(){return _rightNeighbor;}
island* island::leftNeighbor(){return _leftNeighbor;}
island* island::parentIsland(){return _parentIsland;}
int island::locked(){return _locked;}
int island::position(){return _position;}
int island::depth(){return _depth;}
int island::number(){return _number;}
/*SETTERS*/
//void island::setAlignment(siteAlignment a){_alignment = a;}

