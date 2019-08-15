import 'package:flutter/material.dart';
import './I8N.dart';
import './pop.dart';
import './main.dart';
class MyMenu_Data extends StatelessWidget{
  @override
  Widget build(BuildContext context){
    return List(
      static List<question> QuestionList = <question>[
    //new question(lang.check,'diaper',<String>[I8N.of(context).na,I8N.of(context).clean,I8N.of(context).dirty],<String>['na','clean','dirty'],0),
    new question(I8N.of(context).check,'diaper',<String>['N/A','Clean','Dirty'],<String>['na','clean','dirty'],0),
    new question('Mistake','mistake',<String>['N/A','Wee','Poo','Both'],<String>['na','wee','poo','both'],0),
    new question('Toilet','toilet',<String>['N/A','Nothing','Wee','Poo','Both'],<String>['na','nothing','wee','poo','both'],0),
    new question('Poo','poo',<String>['N/A','Few','Normal','Much'],<String>['na','few','normal','much'],0),
    new question('Consistency','poo_consistency',<String>['Soft','Hard','Rot','Dilute'],<String>['soft','hard','rot','dilute'],0),
    new question('Color','poo_color',<String>['true','false'],<String>['true','false'],2),
    new question('Yellow','poo_color_yellow',<String>['true','false'],<String>['true','false'],1),
    new question('Brown','poo_color_brown',<String>['true','false'],<String>['true','false'],1),
    new question('Black','poo_color_black',<String>['true','false'],<String>['true','false'],1),
    new question('Blood','poo_consist_blood',<String>['true','false'],<String>['true','false'],1),
    new question('Goo','poo_consist_goo',<String>['true','false'],<String>['true','false'],1),
  ];
    );
  }
}











static List<question> QuestionList = <question>[
    //new question(lang.check,'diaper',<String>[I8N.of(context).na,I8N.of(context).clean,I8N.of(context).dirty],<String>['na','clean','dirty'],0),
    new question(I8N.of(context).check,'diaper',<String>['N/A','Clean','Dirty'],<String>['na','clean','dirty'],0),
    new question('Mistake','mistake',<String>['N/A','Wee','Poo','Both'],<String>['na','wee','poo','both'],0),
    new question('Toilet','toilet',<String>['N/A','Nothing','Wee','Poo','Both'],<String>['na','nothing','wee','poo','both'],0),
    new question('Poo','poo',<String>['N/A','Few','Normal','Much'],<String>['na','few','normal','much'],0),
    new question('Consistency','poo_consistency',<String>['Soft','Hard','Rot','Dilute'],<String>['soft','hard','rot','dilute'],0),
    new question('Color','poo_color',<String>['true','false'],<String>['true','false'],2),
    new question('Yellow','poo_color_yellow',<String>['true','false'],<String>['true','false'],1),
    new question('Brown','poo_color_brown',<String>['true','false'],<String>['true','false'],1),
    new question('Black','poo_color_black',<String>['true','false'],<String>['true','false'],1),
    new question('Blood','poo_consist_blood',<String>['true','false'],<String>['true','false'],1),
    new question('Goo','poo_consist_goo',<String>['true','false'],<String>['true','false'],1),
  ];