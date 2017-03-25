#ifndef GAMECONTROL_H
#define GAMECONTROL_H

#include "map.h"
#include "ship.h"
#include "island.h"
#include "menuControl.h"
#include "question.h"
#include <ctime>
#include <stdlib.h>
#include <stdio.h>
#include <string>
#include <fstream>
#include <iostream>
#include <string.h>
#include <sstream>
#include <vector>
#include <iterator>
#include <algorithm>

class gameControl
{
	public:
		gameControl();
		gameControl(map * m, ship * s);

		std::vector<question*>* _questions; 

		int _inLoop;
		int _randomNum;

		//game control should have these variables
		string _strChoice;
		map * _theMap;
		ship * _playerShip;
		island * _currentIsland;

		map * theMap();	//return _theMap
		ship * playerShip();//return _playerShip
		island * currentIsland();//return current island

		void initializeMap();	//initializers
		void initializeShip();
		void initializeQuestions();

		
		void welcomeScreen();	//welcome screen of game
		void victoryScreen();
		void homeScreen();	//home screen of game
		void investigateScreen();	//investigate island
		void moveToIsland(island * moveTo);	//move to island
		void enemySpottedScreen();
		void stormScreen();
		void deathScreen();
		void repairScreen();
		void traderScreen();
		void bigMapScreen();
		void addNewQuestionScreen();

		question * selectRandomQuestion();	//pick random question form ../data/question.txt
		int askQuestionScreen();	//ask a random question

		void repairShip(int cost);
		void buySupplies(int cost);

		void rewardGold(int mod);
		void addFood(int mod);
		void useFood(int mod);
		void useGold(int mod);
		void damageShip(int mod);
		void unlockIsland();
		void printSites(island * isle);
		void investigateSite(site * s);
		void addNewQuestion(string propmt,string ans,string op1,string op2, string op3);
		void addNewIsland(int depth,int position,int number);
		
		vector<question*>* questions();

};

#endif
