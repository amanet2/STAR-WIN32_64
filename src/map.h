#ifndef MAP_H
#define MAP_H

#include "island.h"
#define MAX_DEPTH 3	
//we want a max depth of 3
class map
{
	public:
		island * _head;	//head node for BST

		island * head();
		
		bool containsIsland(int depth,int pos);
		
		map();	//constructor
		
		std::vector<island*>* _islands;
		
		std::vector<island*>* islands();
		
		//Island arraylists
		std::vector<island*>* _depth1islands;	//up to 2
		std::vector<island*>* _depth2islands;	//up to 4
		std::vector<island*>* _depth3islands;	//up to 8
};

/*
		ISLANDS (UP TO 11 FOR A MAP) CAN BE SET WHERE AN 'X' IS
		ISLAND GRID, no mismatches can be allowed for v1 maps to function
					NO [DEPTH 3, POS 14] ISLAND (WON'T FIT ON SCREEN)
					
				  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
		DEPTH   0               X <always here
				1       X <always here   X <always here
				2   X       X       X          X
				3 X   X   X   X   X   X     X     
				  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14

*/

#endif
