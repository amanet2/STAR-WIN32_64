#ifndef MENUCONTROL_H
#define MENUCONTROL_H

#include "gameControl.h"
#include <iostream>
#include <string>
#include <stdlib.h>

class menuControl	//for menus not seen during actual gameplay
{
	public:
		int _inMenu;
		int _choice;
		std::string _strChoice;

		menuControl();

	void mainMenu();
	void newGameMenu();
};

#endif
