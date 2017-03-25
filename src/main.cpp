#include "menuControl.h"
#include <stdio.h>
#include <stdlib.h>
#include <iostream>


using namespace std;

int main()
{
	int running = 1;	//the program running?

	menuControl * menuController = new menuControl();	
	//object to control all the menus

		menuController->mainMenu();	
		//have the user pick something from the main menu

	return 0;
}
