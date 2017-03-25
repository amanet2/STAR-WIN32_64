#include "map.h"

map::map(){
	_head = NULL; 
	_islands = new std::vector<island*>();
	
	_depth1islands = new std::vector<island*>();
	_depth2islands = new std::vector<island*>();
	_depth3islands = new std::vector<island*>();
}	//constructor
island * map::head(){return _head;}	//return head
std::vector<island*>* map::islands(){return _islands;}

bool map::containsIsland(int depth,int pos)
{
	if(depth == 0)
		return true;
	else if(depth == 1 && _depth1islands->size() > 0)
	{
		for(int i = 0; i < _depth1islands->size();i++)
		{
			if(_depth1islands->at(i)->position() == pos) return true;
		}
	}
	else if(depth == 2 && _depth2islands->size() > 0)
	{
		for(int i = 0; i < _depth2islands->size();i++)
		{
			if(_depth2islands->at(i)->position() == pos) return true;
		}
	}
	else if(depth == 3 && _depth3islands->size() > 0)
	{
		for(int i = 0; i < _depth3islands->size();i++)
		{
			if(_depth3islands->at(i)->position() == pos) return true;
		}
	}
	
	
	return false;
}

