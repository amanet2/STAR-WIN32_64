#ifndef QUESTION_H
#define QUESTION_H

#include "questionEngine.h"
#include <string>

using namespace std;

class question
{
	public:
		questionType _type;
		string _questionText;
		string _questionAnswer;

		string _option1;	//the incorrect options
		string _option2;
		string _option3;
		

		int answerQuestion(string s);	//test string with question's defined answer return 0 for incorrect 1 for correct
		
		/*GETTER*/
		questionType type();
		string questionText();
		string questionAnswer();

		/*CONSTRUCTORS*/
		question();
		question(questionType qT,string q, string ans, string op1, string op2, string op3);
		~question();		
};

#endif
