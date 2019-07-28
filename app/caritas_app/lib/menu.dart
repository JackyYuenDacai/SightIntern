library menu;
import 'package:flutter/material.dart';
import './I8N.dart';
import './pop.dart';
import './main.dart';

class MyMenu_RFID extends StatelessWidget{
  @override
  Widget build(BuildContext context){
    return ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            UserAccountsDrawerHeader(
              accountName: new Text(StaticList.location),
              currentAccountPicture: new CircleAvatar(
              backgroundImage: AssetImage('images/pic1.jpg'), radius: 35.0,),) ,
            ListTile(title: Text(I8N.of(context).rfid_title),
              leading: new CircleAvatar(child: new Icon(Icons.directions_walk),),
              onTap: () {
               // Navigator.of(context).pushNamed('/RFIDPage');
              },),
            ListTile(title: Text(I8N.of(context).students_title),
              leading: new CircleAvatar(child: new Icon(Icons.person_pin),),
              onTap: () {
                Navigator.of(context).pushNamed('/DataPage');
              },),
            ListTile(title: Text(I8N.of(context).manuel_title),
              leading: new CircleAvatar(child: new Icon(Icons.edit),),
              onTap: () {
                Navigator.of(context).pushNamed('/ManPage');
              },),
          ],
    );
  }
}

class MyMenu_Data extends StatelessWidget{
  @override
  Widget build(BuildContext context){
    return ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            UserAccountsDrawerHeader(
              accountName: new Text(StaticList.location),
              currentAccountPicture: new CircleAvatar(
                backgroundImage: AssetImage('images/pic1.jpg'), radius: 35.0,),) ,
            ListTile(title: Text(I8N.of(context).rfid_title),
              leading: new CircleAvatar(child: new Icon(Icons.directions_walk),),
              onTap: () {
                Navigator.of(context).pushNamed('/RFIDPage');
              },),
            ListTile(title: Text(I8N.of(context).students_title),
              leading: new CircleAvatar(child: new Icon(Icons.person_pin),),
              onTap: () {
                //Navigator.of(context).pushNamed('/DataPage');
              },),
            ListTile(title: Text(I8N.of(context).manuel_title),
              leading: new CircleAvatar(child: new Icon(Icons.edit),),
              onTap: () {
                //Navigator.pop(context);
                Navigator.of(context).pushNamed('/ManPage');
              },),
          ],
        );
  }
}

class MyMenu_Manual extends StatelessWidget{
  @override
  Widget build(BuildContext context){
    return ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            UserAccountsDrawerHeader(
              accountName: new Text(StaticList.location),
              currentAccountPicture: new CircleAvatar(
              backgroundImage: AssetImage('images/pic1.jpg'), radius: 35.0,),) ,
            ListTile(title: Text(I8N.of(context).rfid_title),
              leading: new CircleAvatar(child: new Icon(Icons.directions_walk),),
              onTap: () {
                Navigator.of(context).pushNamed('/RFIDPage');
              },),
            ListTile(title: Text(I8N.of(context).students_title),
              leading: new CircleAvatar(child: new Icon(Icons.person_pin),),
              onTap: () {
                Navigator.of(context).pushNamed('/DataPage');
              },),
            ListTile(title: Text(I8N.of(context).manuel_title),
              leading: new CircleAvatar(child: new Icon(Icons.edit),),
              onTap: () {
                //Navigator.of(context).pushNamed('/ManPage');
              },),
          ],
        );
  }
}