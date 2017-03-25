#ifndef SAILOR_H
#define SAILOR_H

class sailor
{	//define what it means to be a sailor
	public:
		int _alive;	//1 for alive 0 for dead

		char * _birthday;	//like "April 10, 1723" or something
		char * _name;	//like "John Silverboot"
		char * _duty;	//the captain, doctor, etc.  

		/*GETTERS*/
		int alive();
		char * birthday();
		char * name();
		char * duty();
};

#endif
